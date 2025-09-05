package org.accountinglib.data;

public record Account(
        String number,
        String name,
        Currency currency) {

    @Override
    public String number() {
        return number;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Currency currency() {
        return currency;
    }
}
