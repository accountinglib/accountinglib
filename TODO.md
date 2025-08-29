# TODO — accountinglib

## Core (v0.1)
- [ ] Finalize base package (e.g., `org.accountinglib.*`)
- [ ] Implement `Account`, `JournalEntry`, `Posting`, `Ledger`
- [ ] Double-entry validation (sum debits == credits)
- [ ] `LedgerService` (post entry, trial balance)

## Formats & I/O
- [ ] SAF-T: minimal exporter
- [ ] SAF-T: XSD validation test with sample file
- [ ] UBL: pick library (e.g., ph-ubl) + adapter for Invoice export
- [ ] PDF: `PdfRenderer` interface + OpenPDF implementation

## Examples & Docs
- [ ] `examples/HelloLedger.java` (post one entry)
- [ ] `examples/ExportSaft.java` (write SAF-T + validate)
- [ ] README: quick start (Maven + 10-line example)
- [ ] README: roadmap + badges

## Quality & CI
- [ ] JUnit 5 tests (core math, validation, exporters)
- [ ] GitHub Actions: Java 21 build + tests
- [ ] Optional: CodeQL analysis
- [ ] Formatter/lint (Spotless or Checkstyle)
- [ ] License headers plugin

## Versioning & Release
- [ ] Set `0.1.0-SNAPSHOT`
- [ ] Tag `v0.1.0` when first slice works
- [ ] Prep Maven Central metadata (add `distributionManagement` later)

## Nice-to-have (later)
- [ ] Currency rates service + FX gain/loss (agio/disagio)
- [ ] VAT helpers (EU) / sales tax (US)
- [ ] SPI for custom exporters/importers
