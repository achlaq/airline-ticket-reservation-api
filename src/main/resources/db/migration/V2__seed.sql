CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO flight (id, flight_no, origin, dest, dep_time, arr_time, price, seats_total, seats_sold, created_by, created_date)
SELECT gen_random_uuid(), 'GA123', 'CGK', 'DPS', TIMESTAMP '2025-11-07 07:00:00', TIMESTAMP '2025-11-07 09:45:00',
       1200000.00, 180, 0, 'seed', now()
    WHERE NOT EXISTS (
  SELECT 1 FROM flight WHERE flight_no = 'GA123' AND dep_time = TIMESTAMP '2025-11-07 07:00:00'
);

WITH f AS (
    SELECT id AS flight_id, price FROM flight
    WHERE flight_no='GA123' AND dep_time = TIMESTAMP '2025-11-07 07:00:00'
    LIMIT 1
),
     b AS (
         -- Insert booking
         INSERT INTO booking (id, pnr_code, status, contact_name, contact_email, contact_phone, total_amount,
                              created_by, created_date)
         SELECT gen_random_uuid(), 'ABC123', 'HOLD', 'Achlaq', 'achlaq@example.com', '+62812xxxx',
                (SELECT price FROM f) * 1,
                'seed', now()
         WHERE NOT EXISTS (SELECT 1 FROM booking WHERE pnr_code='ABC123')
         RETURNING id
     ),
     bf AS (
         -- Link booking ke flight
         INSERT INTO booking_flight (id, booking_id, flight_id, price_snapshot, created_by, created_date)
         SELECT gen_random_uuid(), b.id, f.flight_id, f.price, 'seed', now()
         FROM b, f
         WHERE NOT EXISTS (
             SELECT 1 FROM booking_flight
             WHERE booking_id = b.id AND flight_id = f.flight_id
         )
         RETURNING booking_id, flight_id
     )
    -- Insert passenger
    INSERT INTO passenger (id, booking_id, full_name, pax_type, birth_date, doc_no, created_by, created_date)
    SELECT gen_random_uuid(), b.id, 'Achlaq Al Arif', 'ADT', DATE '1998-07-12', 'KTP123', 'seed', now()
    FROM b
    WHERE NOT EXISTS (
        SELECT 1 FROM passenger WHERE booking_id = b.id AND full_name = 'Achlaq Al Arif'
    );

-- Update seat
UPDATE flight f
SET seats_sold = seats_sold + 1
WHERE EXISTS (
    SELECT 1 FROM booking b
    JOIN booking_flight bf ON bf.booking_id = b.id
    WHERE b.pnr_code = 'ABC123'
      AND bf.flight_id = f.id
)
  AND NOT EXISTS (
    SELECT 1
    FROM booking b
    JOIN booking_flight bf ON bf.booking_id = b.id
    WHERE b.pnr_code = 'ABC123'
      AND bf.flight_id = f.id
      AND f.seats_sold >= 1
);