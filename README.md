# The Accounting Library

The Accounting Library (accountinglib) is an open source library for accounting in Java.
The goal is to provide an open, standards-based accounting toolkit that makes financial data verifiable,
transparent, and trustworthy across systems.
Intended for use in accounting systems to ensure that accounting is performed in 
a standard, universal way according to accounting best practices and accounting standards.  

AccountingLib / AccountingApp are not replacements for commercial ERP systems like SAP for huge conpanies. Still the goal of AccountingLib is to be a useful toolkit which can be used by commercial ERP systems as a library and support tool.

## AccountingLib Features
[AccountingLib](accountinglib) is a Java library specializing in accounting.  
* Double entry accounting system core.
* Accounting domain objects (Voucher, Invoice, Posting, OrderLines).
* Supplier Invoices (incoming) and Invoices (outgoing).
* SAF-T Standard Audit File for Tax https://en.wikipedia.org/wiki/SAF-T
* Validation of SAF-T files
* PDF document generation with OpenPDF  https://github.com/LibrePDF/OpenPDF/ or Pdfbox https://pdfbox.apache.org/
* UBL (Universal Business Language) object support https://github.com/phax/ph-ubl
* BigDecimal for number operations. Possibly using an adapter.
* Agio and disagio calculations for currency transactions.
* Currency rate conversions.
* VAT rate calculations (EU) and sales tax calculations (USA).
* API integration support (swagger)
* SQLite database
* Validation, generation and manipulation of domains specific to Norway and Norwegian citizens.

## AccountingApp Features
[AccountingApp](accountingapp/README.md) is a Swing GUI app for managing accounting ledger and inspecting SAF-T files.  
* Java Swing GUI for creating and updating accounting data and reports.
* View Ledger, Accounts, Vouchers, SAF-T import
* Validation and display of SAF-T files

## Status
[![](https://jitpack.io/v/accountinglib/accountinglib.svg)](https://jitpack.io/#accountinglib/accountinglib)
![CI](https://github.com/accountinglib/accountinglib/actions/workflows/maven.yml/badge.svg)
[![License (LGPL version 2.1)](https://img.shields.io/badge/license-GNU%20LGPL%20version%202.1-blue.svg?style=flat-square)](http://opensource.org/licenses/LGPL-2.1)
[![CodeQL](https://github.com/accountinglib/accountinglib/actions/workflows/codeql.yml/badge.svg)](https://github.com/accountinglib/accountinglib/actions/workflows/codeql.yml)

## Open source software 
Accountinglib is open source software licensed under the GNU Lesser General Public License (LGPL), designed to promote openness, security, and trust in accounting systems. By making the code fully transparent and community-driven, it allows anyone to inspect, verify, and contribute improvements, ensuring that financial logic is reliable and not hidden behind closed, proprietary solutions. This openness strengthens security through peer review and builds long-term trust for customers and developers alike.

## Security 
Security and trust in using accountinglib is very important. See Security.md.

## Use accountinglib as Maven dependency

accountinglib can be used as a Maven dependency. It is built and distributed using Jitpack.   

1. Add Maven dependency to the pom.xml file:  
```xml
    <dependency>
      <groupId>com.github.accountinglib</groupId>
      <artifactId>accountinglib</artifactId>
      <version>0.9.6</version>
    </dependency>
```

2. Add repository to the pom.xml file:  
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

## Requirements and Dependencies
Accountinglib requires Java 21.

* hibernate-validator
* sqlite-jdbc
* ph-ubl21
* openpdf
* slf4j-api
* commons-csv
* junit
* jakarta.xml.bind-api
* jakarta.activation-api

## Why?

Accountinglib is a standards-focused, open source toolkit for accounting for the Java programming language: a double-entry core with built-in SAF-T export and UBL invoice support. 
It implements a validated ledger system, with SAF-T format, and basic UBL invoice example.

Accountinglib gives companies the freedom to own and maintain their accounting ledgers themselves, since the software is open source and the accounting data can be stored in a preferred database.

AccountingLib/ AccountingApp is not a replacement for a commercial ERP system such as SAP. Still the goal for Accountinglib is to be a useful toolkit which can be used as a library in a commercial accounting system.

## Background and ideas

The idea is that the Accounting Library (accountinglib) should be used as the core accounting library in an accounting system,
to perform financial operations according to accounting best practices. 

The Accounting Library (accountinglib) should focus on implementing the most common accounting operations for objects
such as Invoices and Vouchers, and exchange data in standardized format such as SAF-T and UBL.

## Todo
See [Todo.md](TODO.md).


## License 
GNU Lesser General Public License. See [LICENSE](LICENSE).

## Idea approval:
*Det høres ut som en strålende idé :star2:  Det blir spennende å se hva du får til :thumbsup:*

