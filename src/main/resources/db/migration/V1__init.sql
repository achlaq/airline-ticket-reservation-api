CREATE TABLE flight (
    version       BIGINT NOT NULL DEFAULT 0,
    id            UUID PRIMARY KEY,
    flight_no     VARCHAR(10) NOT NULL,
    origin        VARCHAR(3)     NOT NULL,
    dest          VARCHAR(3)     NOT NULL,
    dep_time      TIMESTAMP   NOT NULL,
    arr_time      TIMESTAMP   NOT NULL,
    price         NUMERIC(12,2) NOT NULL,
    seats_total   INT NOT NULL,
    seats_sold    INT NOT NULL DEFAULT 0,
    created_by    VARCHAR(50),
    created_date  TIMESTAMP NOT NULL DEFAULT now(),
    updated_by    VARCHAR(50),
    updated_date  TIMESTAMP,
    deleted_by    VARCHAR(50),
    deleted_date  TIMESTAMP
);
CREATE INDEX idx_flight_search ON flight(origin, dest, dep_time);

CREATE TABLE booking (
    version        BIGINT NOT NULL DEFAULT 0,
    id             UUID PRIMARY KEY,
    pnr_code       VARCHAR(8) UNIQUE NOT NULL,
    status         VARCHAR(12) NOT NULL,
    contact_name   VARCHAR(80),
    contact_email  VARCHAR(120),
    contact_phone  VARCHAR(30),
    total_amount   NUMERIC(12,2) NOT NULL DEFAULT 0,
    created_by     VARCHAR(50),
    created_date   TIMESTAMP NOT NULL DEFAULT now(),
    updated_by     VARCHAR(50),
    updated_date   TIMESTAMP,
    deleted_by     VARCHAR(50),
    deleted_date   TIMESTAMP
);

CREATE TABLE booking_flight (
    version        BIGINT NOT NULL DEFAULT 0,
    id             UUID PRIMARY KEY,
    booking_id     UUID NOT NULL REFERENCES booking(id),
    flight_id      UUID NOT NULL REFERENCES flight(id),
    price_snapshot NUMERIC(12,2) NOT NULL,
    created_by     VARCHAR(50),
    created_date   TIMESTAMP NOT NULL DEFAULT now(),
    updated_by     VARCHAR(50),
    updated_date   TIMESTAMP,
    deleted_by     VARCHAR(50),
    deleted_date   TIMESTAMP,
    UNIQUE(booking_id, flight_id)
);

CREATE TABLE passenger (
    version       BIGINT NOT NULL DEFAULT 0,
    id            UUID PRIMARY KEY,
    booking_id    UUID NOT NULL REFERENCES booking(id),
    full_name     VARCHAR(80) NOT NULL,
    pax_type      VARCHAR(4)  NOT NULL,
    birth_date    DATE,
    doc_no        VARCHAR(30),
    created_by    VARCHAR(50),
    created_date  TIMESTAMP NOT NULL DEFAULT now(),
    updated_by    VARCHAR(50),
    updated_date  TIMESTAMP,
    deleted_by    VARCHAR(50),
    deleted_date  TIMESTAMP
);
CREATE INDEX idx_passenger_booking ON passenger(booking_id);


