# TODO — accountinglib

- [ ] Validation of SAF-T files. Swing GUI?
- [ ] Improve new company dialog
- [ ] Improve new voucher dialog
- [ ] storage to DB.
- [ ] SAF-T export.
- [ ] New invoice
- [ ] Hibernate? SQLite?

# International 
- Factur-x for .fr
- Xrechnung for .de

## Core (v0.1)
- [ ] Finalize base package (e.g., `org.accountinglib.*`)
- [ ] Implement `Account`, `JournalEntry`, `Posting`, `Ledger`
- [ ] Double-entry validation (sum debits == credits)
- [ ] `LedgerService` (post entry, trial balance)
- [ ] Add some SAF-T files for initial ledger example data.
- [ ] create some unit tests
- [ ] decide on domain object mapping method


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

## Nice-to-have (later)
- [ ] Currency rates service + FX gain/loss (agio/disagio)
- [ ] VAT helpers (EU) / sales tax (US)
- [ ] SPI for custom exporters/importers
- [ ] Publish to Maven central

- Jakarta EE and Spring support

Use cases:
- SAF-T export
