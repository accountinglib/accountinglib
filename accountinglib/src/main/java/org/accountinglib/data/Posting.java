package org.accountinglib.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Posting(
        Long id,
        Account account,
        LocalDate date,
        Currency currency,
        BigDecimal creditAmount,
        BigDecimal debitAmount,
        String description,
        Employee employee,
        Project project
) {
}
