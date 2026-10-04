# M1 HTTP Service

A small HTTP service built with Java and Spring Boot.

## What it does

This service provides two HTTP endpoints:

- `GET /` — returns a simple service message.
- `GET /healthz` — returns `OK` and is used as the service health check.

The service does not use a database.

## How to run

The service uses the `PORT` environment variable.

Run the service with:

```bash
PORT=5555 ./scripts/run.sh
```

If `PORT` is not specified, the service uses port `8080`.

## Endpoints

### GET /

Returns a simple message confirming that the service is running.

Example:

```bash
curl http://localhost:5555/
```

Response:

```text
M1 HTTP Service
```

### GET /healthz

Returns `OK` when the service is healthy.

Example:

```bash
curl http://localhost:5555/healthz
```

Response:

```text
OK
```

The `/healthz` endpoint does not use a database.

## How to test

Run the test script:

```bash
./scripts/test.sh
```

A successful test prints:

```text
TESTS: 3/3
```

The test script exits with code `0` when all tests pass.

The tests check:

1. `GET /` returns HTTP 200.
2. `GET /healthz` returns HTTP 200.
3. `GET /healthz` returns `OK`.

## Port

The port is controlled by the `PORT` environment variable.

Example:

```bash
PORT=5555 ./scripts/run.sh
```

The default port is:

```text
8080
```

## Technology

- Java 21
- Spring Boot 3.5.6
- Maven
- HTTP

## Project status

This project implements the INF 345 Week 3 HTTP service contract.
