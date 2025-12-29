# SeleniumHQ API Automation - 
Created at 27, dec, 2025
by Phuong Nam Vu

following the request; my repo provided about:

API automation testing project for SeleniumHQ organization using REST Assured and TestNG. This project tests GitHub API endpoints to retrieve and validate repository information.

## 📁 Project Structure

```
seleniumhq-api-automation/
├── pom.xml                          # Maven project configuration
├── README.md                         # Project documentation
├── .gitignore                        # Git ignore rules
├── Dockerfile                        # Docker configuration
├── .dockerignore                     # Docker ignore rules
│
└── src/
    ├── main/
    │   └── java/
    │       ├── api/
    │       │   ├── BaseApiClient.java        # Base API client with common HTTP methods
    │       │   └── GitHubApiClient.java      # GitHub API specific implementation
    │       │
    │       ├── models/
    │       │   └── Repository.java            # Repository data model
    │       │
    │       ├── constants/
    │       │   └── ApiEndpoints.java         # API endpoint constants
    │       │
    │       └── utils/
    │           └── ConfigReader.java         # Configuration file reader
    │
    └── test/
        ├── java/
        │   └── tests/
        │       └── SeleniumHQTest.java       # TestNG test cases
        │
        └── resources/
            ├── config.properties             # Test configuration
            └── testng.xml                    # TestNG suite configuration
```

### 📋 Component Description

- **BaseApiClient**: Abstract base class providing common HTTP methods (GET, POST, etc.) and response validation
- **GitHubApiClient**: Extends BaseApiClient, implements GitHub API specific methods
- **Repository**: Model class representing GitHub repository data (name, stars, issues, URL, language)
- **ApiEndpoints**: Constants for GitHub API endpoints
- **ConfigReader**: Utility to read configuration from `config.properties`
- **SeleniumHQTest**: TestNG test suite with API test cases

## 🚀 Setup Instructions

### Prerequisites

- **Java 11** or higher
- **Maven 3.6+** or higher
- **Docker** (optional, for containerized execution)
- Internet connection (to access GitHub API)

### Local Setup

1. **Clone the repository** (if applicable):
   ```bash
   git clone <repository-url>
   cd seleniumhq-api-automation
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

3. **Verify setup**:
   ```bash
   mvn clean compile
   ```

### Configuration

Edit `src/test/resources/config.properties` to customize API settings:

```properties
# API Configuration
api.base.uri=https://api.github.com

# GitHub API Configuration
github.organization=seleniumhq
github.token=                    

# Test Configuration
test.timeout=30000
```

> **Note**:

## 🧪 How to Run API Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn test -Dtest=SeleniumHQTest
```

### Run Specific Test Method

```bash
mvn test -Dtest=SeleniumHQTest#testGetSeleniumRepository
```

### Run Tests with TestNG Suite

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

### Run Tests in Parallel

Tests are configured to run in parallel (2 threads) via `testng.xml`. To modify:

Edit `src/test/resources/testng.xml`:
```xml
<suite name="SeleniumHQ API Test Suite" parallel="methods" thread-count="2">
```

### Test Reports

After running tests, view reports:
- **HTML Report**: `target/surefire-reports/index.html`
- **XML Report**: `target/surefire-reports/TEST-TestSuite.xml`
- **TestNG Report**: `target/surefire-reports/SeleniumHQ API Test Suite/`

## 🖥️ How to Run Tests

```bash
# Exam
mvn test -Dtest=UITestSuite
```

## 🐳 Running with Docker

### Prerequisites

- Docker installed and running

### Build Docker Image

```bash
docker build -t seleniumhq-api-automation .
```

### Run Tests in Docker Container

#### Run All Tests

```bash
docker run --rm seleniumhq-api-automation
```

#### Run Specific Test Class

```bash
docker run --rm seleniumhq-api-automation mvn test -Dtest=SeleniumHQTest
```

#### Run Specific Test Method

```bash
docker run --rm seleniumhq-api-automation mvn test -Dtest=SeleniumHQTest#testGetHighestRatedRepository
```

#### Run Tests with Custom Configuration

```bash
# Mount custom config file
docker run --rm \
  -v $(pwd)/src/test/resources/config.properties:/app/src/test/resources/config.properties \
  seleniumhq-api-automation
```

#### Run Tests and View Reports

```bash
# Run tests and copy reports to host
docker run --rm \
  -v $(pwd)/target:/app/target \
  seleniumhq-api-automation

# View reports
open target/surefire-reports/index.html
```

#### Interactive Docker Session

```bash
# Start container interactively
docker run -it --rm seleniumhq-api-automation /bin/bash

# Inside container, run:
mvn test
mvn test -Dtest=SeleniumHQTest
```

#### Build and Run in One Command

```bash
docker build -t seleniumhq-api-automation . && docker run --rm seleniumhq-api-automation
```

#### Run with Environment Variables

```bash
docker run --rm \
  -e GITHUB_TOKEN=your_token_here \
  seleniumhq-api-automation
```

### Docker Compose (Optional)

Create `docker-compose.yml` for easier management:

```yaml
version: '3.8'
services:
  api-tests:
    build: .
    volumes:
      - ./target:/app/target
    command: mvn test
```

Run with:
```bash
docker-compose up
```

## 📦 Dependencies

- **REST Assured 5.4.0** - API testing framework
- **TestNG 7.8.0** - Testing framework
- **Jackson 2.16.0** - JSON processing
- **SLF4J Simple 2.0.9** - Logging

## 🧩 Test Cases

The project includes the following test cases:

1. **testGetSeleniumHQRepositories**: Retrieves all repositories from SeleniumHQ organization
2. **testGetSeleniumRepository**: Gets detailed information about the "selenium" repository
3. **testRepositoryHasValidData**: Validates data integrity of all repositories
4. **testGetHighestRatedRepository**: Finds and validates the repository with the highest star count

## 📊 Test Results

After running tests, you'll see output like:

```
=== Highest Rated Repository ===
Name: selenium
Stars: 25000
URL: https://github.com/seleniumhq/selenium
Language: Java
Open Issues: 150
```

## 🔍 API Endpoints Tested

- `GET /orgs/{org}/repos` - List organization repositories
- `GET /repos/{owner}/{repo}` - Get repository details

## 📝 Notes

- GitHub API has rate limits: 60 requests/hour (unauthenticated), 5000/hour (authenticated)
- Tests require internet connection
- All tests run in parallel by default (2 threads)
- Test reports are generated in `target/surefire-reports/`

## 🐛 Troubleshooting

### Common Issues

1. **Rate Limit Exceeded**: Add GitHub token to `config.properties`
2. **Network Error**: Check internet connection
3. **Compilation Error**: Ensure Java 11+ is installed
4. **Docker Build Fails**: Check Docker daemon is running

### Debug Mode

Run tests with debug logging:
```bash
mvn test -X
```

## 🎥 Demo Video

A demo video showing : [https://drive.google.com/drive/folders/19gTL6gcDh4TpWeS-DVShrgnVGY8rUZD_?usp=sharing]

## 📄 License

This project is for educational/testing purposes.
