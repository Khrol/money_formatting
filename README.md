# Money Formatting application and tests

Application consists of two parts:
1. Backend (scala + akka-http)
2. Frontend (React.js)

## Test Types

### Backend Unit Tests

`./backend/src/test/scala/com/khroliz/FormatterSpec.scala`

### Backend Integration Tests

`./backend/src/test/scala/com/khroliz/RoutesSpec.scala`

### Frontend Unit Tests

`./frontend/src/components/Result/Result.test.jsx`

### Frontend Integration Tests

`./frontend/src/components/FormattingForm/FormattingForm.test.jsx`

`./frontend/src/App.test.jsx`

### End to End Selenium Tests

`./end_to_end_tests/src/test/scala/com/khroliz/EndToEndSpec.scala`

### Property-based Test (ScalaCheck)

`./backend/src/test/scala/com/khroliz/FormatterSpecification.scala`

### Mutation Tests

Sbt plugin in `./backend/project/plugins.sbt` and `./backend/stryker4s.conf` config file.

## How to Run Tests

### Backend (Unit + Integration + Property-based)

```bash
backend $ sbt test
```

### Mutation Tests

```bash
backend $ sbt stryker
```

### Frontend (Unit + Integration)

```bash
frontend $ CI=true npm test
```

### End to End Tests

```bash
end_to_end_test $ ./run.sh
```
