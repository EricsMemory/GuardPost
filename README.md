# 🛡️ GuardPost

**GuardPost** is a modular, developer-friendly email validation API built with Java and Spring Boot. It provides fast, clean, and structured email validation for use in web apps, mobile platforms, SaaS tools, and internal systems.

GuardPost is under active development. Phase 1 MVP is functional and live locally.
---

## Current Features (MVP)

### Syntax Validation
- Validates email structure using industry-standard regular expressions
- Returns JSON with:
  - The original email
  - Disposable email flag (true / false)
  - Validity flag (true / false)
  - Human-readable message
 
### Disposable Email Detection
- Detects and blocks throwaway email domains using the [disposable-email-domains](https://github.com/disposable-email-domains/disposable-email-domains) repository for a full list of disposable email domains.
- Efficient lookup via hashed set comparison
- Configurable blocklist file (must be populated using the domain list from the linked GitHub repository)

### MX Record Lookup
- Queuries DNS system to check for valid MX records
- Returns boolean indicating whether the domain can receive emails or not
- Validation process catches emails with valid syntax but invalid domains

### Project Structure (MVC)
<pre>
org.eric.guardpost.guardpostapi
├── controller
│   └── EmailValidationController.java
├── model
│   └── EmailValidationResult.java
├── service
│   ├── EmailValidationService.java
│   └── DisposableEmailService.java
    └── MxLookupService.java
├── util
│   └── EmailUtils.java
└── GuardPostApplication.java
</pre>

### 🔧 Example API Usage

**Request:**
GET /validate?email=test@gmail.com

**Response:**
{
  "email": "test@gmail.com",
  "message": "Email syntax is valid.",
  "hasMxRecord": true,
  "disposable": false,
  "valid": true
}

**Planned Features**
	•	“Did You Mean?” Suggestions – Typo detection for common domain misspellings
	•	Bulk Validation – Validate arrays of emails in a single request
 
**Built With**
	•	Java 21
	•	Spring Boot
	•	Maven
