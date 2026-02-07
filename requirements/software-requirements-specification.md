# Software Requirements Specification (SRS)

Project Name: Australian Toilet Finder
Version:_ 0.1
_Status:_ Draft
_Last Updated:_ 2026-02-07
_Owner:_

---

## 0. Document Control

### 0.1 Revision History


| Version |       Date | Author | Notes         |
| ------- | ---------: | ------ | ------------- |
| 0.1     | 2026-02-07 |        | Initial draft |

### 0.2 Reviewers / Approvers

- Reviewer(s):
- Approver(s):

### 0.3 How Changes Are Made

- Change proposal process:
- Approval required for:

---

## 1. Summary

### 1.1 Purpose

This SRS covers the software requirements for the Australian Toilet Finder. Its purpose is to drive the software design for the web application.

### 1.2 Product Overview


### 1.3 Goals / Success Metrics

- G1:
- Metric(s):

### 1.4 Non-Goals / Out of Scope

- NG1:
- NG2:

---

## 2. Stakeholders & Users

### 2.1 Stakeholders

- Stakeholder:
  - Interest / impact:

### 2.2 User Roles

- **Visitor**:
- **Registered User**:
- **Admin**:

### 2.3 User Needs (brief)

- Role → key needs

---

## 3. Scope & Context

### 3.1 System Context

What’s inside the system vs external systems/services.

### 3.2 Assumptions

- A1:
- A2:

### 3.3 Dependencies

- External services/APIs:
- Data sources:
- Email/SMS provider (if any):
- Map provider (if any):

### 3.4 Constraints

- Technical constraints:
- Legal/compliance:
- Time/budget:

---

## 4. Use Case Model (Core Functional Requirements)

> Conventions:
>
> - Use Case IDs: `UC-###`
> - Priority: Must / Should / Could
> - Each use case should have testable outcomes.

### 4.1 Use Case Index


| ID     | Name | Primary Actor | Priority |
| ------ | ---- | ------------- | -------- |
| UC-001 |      |               | Must     |
| UC-002 |      |               | Should   |

---

### 4.2 Use Case Template (copy per use case)

#### UC-001: use

- **Primary Actor:** (e.g., Visitor / Registered User / Admin)
- **Stakeholders & Interests:** (who cares and why)
- **Goal:** (what the actor wants to achieve)
- **Scope:** Web app
- **Level:** User-goal
- **Priority:** Must / Should / Could
- **Frequency:** (e.g., daily, weekly, seasonal)
- **Preconditions:**
  - P1:
- **Minimal Guarantees (always true even on failure):**
  - G1:
- **Success Guarantees (postconditions on success):**
  - S1:
- **Trigger:**
  - T1: (what starts the use case)

**Main Success Scenario (Basic Flow)**

**Alternate Flows**

- **A1:
  <name>**
  - Condition:
  - Steps:
- **A2: <name>**
  - Condition:
  - Steps:

**Exception Flows (Failures / Errors)**

- **E1: <name>**
  - Condition:
  - System behavior:
- **E2: <name>**
  - Condition:
  - System behavior:

**Business Rules**

- BR-?:

**Special Requirements (NFR-related)**

- Performance:
- Security:
- Audit/logging:
- Accessibility:

**Notes / Open Questions**

- Q1:

---

## 5. Data & Content Requirements

### 5.1 Key Data Entities (high-level)

List core entities and what they represent.

### 5.2 Validation Rules

- VR1:
- VR2:

### 5.3 Data Lifecycle

- Creation:
- Updates:
- Deletion:
- Retention:

### 5.4 Privacy / Data Classification

- Public:
- Internal:
- Sensitive:

---

## 6. Non-Functional Requirements (NFRs)

> Use NFR IDs: `NFR-###` (keep them measurable where possible)

### 6.1 Performance

- NFR-001:
- NFR-002:

### 6.2 Availability & Reliability

- NFR-010:

### 6.3 Security

- NFR-020: Authentication
- NFR-021: Authorization
- NFR-022: Data protection (in transit/at rest)
- NFR-023: Logging/auditing

### 6.4 Usability & Accessibility

- NFR-030:

### 6.5 Maintainability & Supportability

- NFR-040: Observability (logs/metrics/traces)
- NFR-041: Error handling standards

### 6.6 Compatibility

- NFR-050: Supported browsers/devices

---

## 7. UI Requirements (Web App)

### 7.1 Key Screens / Pages

For each page:

- Name:
- Purpose:
- Primary actions:
- Inputs/outputs:
- States (empty/loading/error):
- Validation messages:

### 7.2 Navigation & Information Architecture

- Main navigation:
- Auth-gated sections:

### 7.3 Accessibility Requirements

- Minimum standard (e.g., WCAG level if applicable)

---

## 8. Interfaces & Integrations

### 8.1 External Integrations

- Integration name:
  - Purpose:
  - Data exchanged:
  - Failure behavior:

### 8.2 API Endpoints (if applicable)

- Endpoint list (high-level only)
- Auth approach
- Rate limits / throttling expectations

---

## 9. Operational Requirements

### 9.1 Environments

- Dev:
- Test:
- Prod:

### 9.2 Configuration

- Required config values (no secrets in repo)
- Feature flags (if any)

### 9.3 Logging, Monitoring, Alerting

- What is logged
- Alert conditions

### 9.4 Deployment & Rollback

- Deployment approach:
- Rollback expectations:

---

## 10. Acceptance & Verification

### 10.1 Definition of Done

- DoD item:
- DoD item:

### 10.2 Test Strategy (high-level)

- Unit:
- Integration:
- End-to-end:

### 10.3 Traceability (optional)

Map use cases → tests.

---

## 11. Risks & Open Issues

### 11.1 Risks


| Risk | Impact | Likelihood | Mitigation |
| ---- | ------ | ---------- | ---------- |
|      |        |            |            |

### 11.2 Open Questions / TBD

- TBD-1:
- TBD-2:

---

## Appendix A: Glossary

- Term: definition

## Appendix B: Diagrams (optional)

- System context diagram
- User flow diagrams
- Data model sketch</name></name></name></name>
