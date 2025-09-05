package org.accountinglib.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Voucher {

    private Map<Long, Posting> postings = new HashMap<>();

    public Map<Long, Posting> getPostings() {
        return postings;
    }

    public void setPostings(Map<Long, Posting> postings) {
        this.postings = postings;
    }
}


