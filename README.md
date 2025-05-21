# 🛡️ GuardPost

**GuardPost** is a modular, developer-friendly email validation API built with Java and Spring Boot. It provides fast, clean, and structured email validation for use in web apps, mobile platforms, SaaS tools, and internal systems.

GuardPost is under active development. Phase 1 MVP is functional and live locally.
Further features will be tracked and built in modular stages on the GitHub project board.
---

## Current Features (MVP)

### Syntax Validation
- Validates email structure using industry-standard regular expressions
- Returns JSON with:
  - The original email
  - Validity flag (true / false)
  - Human-readable message

### Project Structure (MVC)
org.eric.guardpost.guardpostapi
├── controller
│   └── EmailValidationController.java
├── model
│   └── EmailValidationResult.java
├── service
│   └── EmailValidationService.java
└── GuardPostApplication.java

### 🔧 Example API Usage

**Request:**
GET /validate?email=test@gmail.com

**Response:**
{
  "email": "test@gmail.com",
  "valid": true,
  "message": "Email syntax is valid."
}

**Planned Features**
	•	Domain Validation – DNS + MX record verification
	•	Disposable Email Detection – Flag throwaway email services
	•	“Did You Mean?” Suggestions – Typo detection for common domain misspellings
	•	Bulk Validation – Validate arrays of emails in a single request
 
**Built With**
	•	Java 21
	•	Spring Boot
	•	Maven
