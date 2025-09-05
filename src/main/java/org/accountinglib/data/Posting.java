package org.accountinglib.data;

import java.math.BigDecimal;

public record Posting(
        Account account,
        Currency currency,
        BigDecimal amount,
        String description
) {
}
