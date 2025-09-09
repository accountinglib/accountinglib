package org.accountinglib.data;

public record Company(
        String name,
        String number,
        boolean isCustomer,
        boolean isSupplier
) {}
