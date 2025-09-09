package org.accountinglib.data;

public record Employee(
        String number,
        String name) {

    @Override
    public String number() {
        return number;
    }

    @Override
    public String name() {
        return name;
    }

}
