# Diagnostic Catalog

A REST service for structured diagnostic knowledge. Each entry records an observed
**symptom**, its likely **cause** and the **remedy**, linked to the affected
**component** and tagged for retrieval — the structure of the *Symptom / Possible
Cause / Corrective Action* tables found in service manuals.

The goal is search that works on meaning rather than on keywords: a query for
*"motor won't start when it's cold"* should find an entry titled *"Unit does not
power on after cold storage"*, which shares no keyword with it.

All sample data in this repository is fictional.

## Status

Under development. The commit history shows what currently works.

## License

Apache License 2.0 — see [LICENSE](LICENSE).
