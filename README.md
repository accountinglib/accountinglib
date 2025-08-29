# The Accounting Library

The Accounting Library (accountinglib) is an open source library for accounting in Java.
The goal is to provide an open, standards-based accounting toolkit that makes financial data verifiable,
transparent, and trustworthy across systems.
Intended for use in accounting systems to ensure that accounting is performed in 
a standard, universal way according to accounting best practices and accounting standards.

## Features
* Double entry accounting system core.
* Accounting domain objects (Voucher, Invoice, Posting, OrderLines).
* Supplier Invoices (incoming) and Invoices (outgoing).
* SAF-T Standard Audit File for Tax https://en.wikipedia.org/wiki/SAF-T
* PDF document generation with OpenPDF  https://github.com/LibrePDF/OpenPDF/ or Pdfbox https://pdfbox.apache.org/
* UBL (Universal Business Language) object support https://github.com/phax/ph-ubl
* BigDecimal for number operations. Possibly using an adapter.
* Agio and disagio calculations for currency transactions.
* Currency rate conversions.
* VAT rate calculations (EU) and sales tax calculations (USA).

## Requirements
Accountinglib requires Java 21.

## Background and ideas

The idea is that the Accounting Library (accountinglib) should be used as the core accounting library in an accounting system,
to perform financial operations according to accounting best practices. 

The Accounting Library (accountinglib) should focus on implementing the most common accounting operations for objects
such as Invoices and Vouchers, and exchange data in standardized format such as SAF-T and UBL.

## Todo
See [Todo.md](TODO.md).


## License 
GNU Lesser General Public License.

## Status
![CI](https://github.com/accountinglib/accountinglib/actions/workflows/maven.yml/badge.svg)
[![License (LGPL version 2.1)](https://img.shields.io/badge/license-GNU%20LGPL%20version%202.1-blue.svg?style=flat-square)](http://opensource.org/licenses/LGPL-2.1)
