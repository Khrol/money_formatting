#!/usr/bin/env bash
set -e

# Not a very nice cleanup but it works...
function cleanup {
  pkill -f com.khroliz.HttpServer
  pkill -f frontend
  pkill -f chromedriver
}
trap cleanup EXIT


cd ../frontend
BROWSER=none npm start &
cd ../backend
sbt "runMain com.khroliz.HttpServer" &

cd ../end_to_end_tests
sbt test
