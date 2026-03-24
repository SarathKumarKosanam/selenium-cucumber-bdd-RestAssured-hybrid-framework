# Selenium Cucumber BDD Framework


![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green?logo=selenium)
![Cucumber](https://img.shields.io/badge/Cucumber-7.x-brightgreen?logo=cucumber)
![RestAssured](https://img.shields.io/badge/RestAssured-5.x-blue)
![Maven](https://img.shields.io/badge/Maven-3.x-red?logo=apachemaven)

A production-grade **Hybrid Test Automation Framework** built with industry-standard tools and practices. Covers UI automation using Selenium + Cucumber BDD and API testing using RestAssured with TestNG.

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 17 | Core language |
| Selenium WebDriver 4 | UI browser automation |
| Cucumber 7 | BDD layer with Gherkin scenarios |
| RestAssured 5 | API testing |
| TestNG | Test runner |
| Apache POI | Excel-based test data management |
| ExtentReports + Cucumber Adapter | Auto-generated HTML reports |
| dotenv-java | Local secrets management |
| GitHub Actions | CI/CD pipeline |
| WebDriverManager | Automatic browser driver management |

---

## Framework Architecture
```
selenium-cucumber-bdd-framework/
├── src/main/java/com/sdet/
│   ├── pages/              # Page Object Model classes
│   ├── utils/              # DriverManager, ConfigReader, ExcelDataReader
│   └── constants/          # App-wide constants
├── src/test/java/com/sdet/
│   ├── hooks/              # Cucumber Before/After hooks
│   ├── runner/             # TestRunner with CucumberOptions
│   ├── stepdefs/           # Step definitions for UI features
│   └── api/                # RestAssured API tests (TestNG)
├── src/test/resources/
│   ├── features/           # Gherkin feature files
│   ├── testdata/           # Excel test data (TestData.xlsx)
│   ├── config.properties   # Non-sensitive configuration
│   └── extent.properties   # Extent report configuration
├── .github/workflows/      # GitHub Actions CI/CD
├── .env.example            # Secret keys template
└── reports/                # Generated test reports (gitignored)
```

---

## Key Features

- **Page Object Model** — clean separation of page actions and test logic
- **BDD with Cucumber 7** — human-readable Gherkin scenarios for UI tests
- **Hybrid approach** — Cucumber for UI, plain TestNG for API (industry standard)
- **Excel Data-Driven Testing** — Apache POI reads test data from `.xlsx` files
- **Secrets Management** — dotenv locally, GitHub Secrets in CI/CD pipeline
- **Auto Screenshot on Failure** — captured and embedded in Cucumber report
- **Dual Reporting** — ExtentReports (dark theme) + Cucumber HTML report auto-generated
- **ThreadLocal WebDriver** — supports parallel test execution
- **Headless Execution** — runs in CI without a display

---

## Test Coverage

### UI Tests (Cucumber BDD)

| Feature | Scenarios |
|---------|-----------|
| Login | Valid login, locked user, wrong credentials, empty fields, data-driven invalid users |
| Inventory | Product count, add to cart, multiple items, sort by price |
| Checkout | Full order flow (Excel data), empty first name, last name, zip validations |

### API Tests (RestAssured + TestNG)

| Endpoint | Test |
|----------|------|
| GET /users | Returns user list with valid data |
| GET /users/{id} | Returns correct single user |
| POST /users | Creates user, returns 201 with id |
| PUT /users/{id} | Updates user details |
| DELETE /users/{id} | Returns 204 |
| GET /users/9999 | Returns 404 for invalid id |

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Chrome browser installed

---

## Setup & Run

### 1. Clone the repository
```bash
git clone https://github.com/SarathKumarKosanam/selenium-cucumber-bdd-framework.git
cd selenium-cucumber-bdd-framework
```

### 2. Configure secrets locally
```bash
cp .env.example .env
```
Edit `.env` and add your values:
```
APP_USERNAME=standard_user
APP_PASSWORD=secret_sauce
LOCKED_USER=locked_out_user
```

### 3. Run all tests
```bash
mvn clean test
```

### 4. Run only UI tests
```bash
mvn clean test -Dcucumber.filter.tags="not @api"
```

### 5. Run only API tests
```bash
mvn clean test -Dtest=UsersApiTest
```

---

## Test Reports

After running tests, two reports are auto-generated:

| Report | Location |
|--------|---------|
| ExtentReports (dark theme) | `reports/TestReport.html` |
| Cucumber HTML Report | `reports/cucumber-html-report.html` |

Open either file in your browser to view detailed results with screenshots on failure.

---

## CI/CD Pipeline

Tests run automatically on every push to `main` via GitHub Actions:

1. Sets up Java 17
2. Caches Maven dependencies
3. Runs full test suite with secrets injected from GitHub Secrets
4. Uploads test reports as downloadable artifacts (retained 30 days)

### GitHub Secrets required

| Secret | Value |
|--------|-------|
| `APP_USERNAME` | SauceDemo standard username |
| `APP_PASSWORD` | SauceDemo password |
| `LOCKED_USER` | SauceDemo locked out username |

---

## Application Under Test

- **UI** → [SauceDemo](https://www.saucedemo.com) — e-commerce demo application
- **API** → [ReqRes.in](https://reqres.in) — free REST API for testing

---

## Author

**Sarath Kumar Kosanam**
SDET | Automation Engineer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?logo=linkedin)](https://www.linkedin.com/in/sarathkumarkosanam-qaautomationtestengineer/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?logo=github)](https://github.com/SarathKumarKosanam)
