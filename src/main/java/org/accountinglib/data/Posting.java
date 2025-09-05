package org.accountinglib.data;

import java.math.BigDecimal;

public record Posting(
        Long id,
        Account account,
        Currency currency,
        BigDecimal creditAmount,
        BigDecimal debitAmount,
        String description
) {
}
