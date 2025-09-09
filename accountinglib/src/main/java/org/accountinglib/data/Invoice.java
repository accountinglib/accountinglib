package org.accountinglib.data;

import java.util.HashMap;
import java.util.Map;

public class Invoice {

    private boolean isSupplierInvoice;
    private String invoiceNumber;

    private Map<Long, OrderLine> orderlines = new HashMap<>();

}
