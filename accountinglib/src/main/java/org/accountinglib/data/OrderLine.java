package org.accountinglib.data;

import java.math.BigDecimal;

public record OrderLine(Currency currency,
                        BigDecimal amount,
                        String description) {



}
