# Labseq Exercise 

This project was built with:

* **Frontend**: Angular (served via Nginx)
* **Backend**: Quarkus (Java)
* **Database**: Redis
* **Deployment**: Docker

---

## Quick Start

### Prerequisites

* Docker
* Docker Compose
* (Optional) WSL for smoother Linux-native development on Windows

---

### Run the Full Stack

From the root of the project:

```bash
docker-compose up --build
```

This will:

* Build and serve the Angular frontend via Nginx at [http://localhost:4200](http://localhost:4200)
* Run the Quarkus backend on [http://localhost:8080](http://localhost:8080)
* Launch Redis on port 6379

---

### Access Points

* Frontend: [http://localhost:4200](http://localhost:4200)
* Backend API: [http://localhost:8080/labseq](http://localhost:8080/labseq)
* Swagger UI: [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)
* Redis (if needed for inspection): `localhost:6379`

---

## Development

### Running without Docker

**Frontend:**

```bash
cd frontend
npm install
ng serve
```

**Backend:**

```bash
cd backend
./mvnw compile quarkus:dev
```

Also needs Redis running locally (`docker run redis` or via Redis Desktop).

---

## ⚙️ Environment Configuration

### Angular

Angular uses the environment file:
* `src/environments/environment.ts`: for local dev (`localhost:8080`)


### Quarkus

`backend/src/main/resources/application.properties`:

```properties
quarkus.redis.hosts=redis://redis:6379
quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:4200
quarkus.swagger-ui.always-include=true
```

---