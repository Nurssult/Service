#!/bin/bash

set -euo pipefail

PORT=18080
export PORT

./scripts/run.sh > /tmp/service.log 2>&1 &
PID=$!

cleanup() {
    kill "$PID" 2>/dev/null || true
}
trap cleanup EXIT

for i in {1..30}; do
    if curl -s "http://localhost:$PORT/healthz" >/dev/null 2>&1; then
        break
    fi
    sleep 1
done

TESTS=0
PASSED=0

# Test 1: GET /
TESTS=$((TESTS + 1))
if [ "$(curl -s -o /dev/null -w "%{http_code}" "http://localhost:$PORT/")" = "200" ]; then
    PASSED=$((PASSED + 1))
fi

# Test 2: GET /healthz
TESTS=$((TESTS + 1))
if [ "$(curl -s -o /dev/null -w "%{http_code}" "http://localhost:$PORT/healthz")" = "200" ]; then
    PASSED=$((PASSED + 1))
fi

# Test 3: /healthz returns OK
TESTS=$((TESTS + 1))
if [ "$(curl -s "http://localhost:$PORT/healthz")" = "OK" ]; then
    PASSED=$((PASSED + 1))
fi

# Test 4: / returns the expected message
TESTS=$((TESTS + 1))
if [ "$(curl -s "http://localhost:$PORT/")" = "M1 HTTP Service" ]; then
    PASSED=$((PASSED + 1))
fi

echo "TESTS: $PASSED/$TESTS"

if [ "$PASSED" -eq "$TESTS" ]; then
    exit 0
else
    exit 1
fi