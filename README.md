# 🚀 airline-ticket-reservation-api

Aplikasi backend menggunakan **Spring Boot** dengan REST API untuk mengelola aplikasi pemesanan tiket maskapai.

---

## 🛠️ Tech Stack
- [Java 17+](https://adoptium.net/)
- [Spring Boot 3.x](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- Database: PostgreSQL, Flyway

---

## 📦 Installation & Running

### 1. Clone repository
```bash
git clone https://github.com/achlaq/airline-ticket-reservation-api.git
cd airline-ticket-reservation-api
```

### 2. Configure Database
```sql
CREATE DATABASE flightdb;
```

```yaml
spring.datasource.url=jdbc:postgresql://localhost:[your-port]/flightdb
spring.datasource.username=[your-username]
spring.datasource.password=[your-password]
```

### 3. Jalankan aplikasi
```bash
./mvnw spring-boot:run
```

👉 Access on http://localhost:8899
