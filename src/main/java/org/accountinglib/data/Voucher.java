package org.accountinglib.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Voucher {

    private Long id;

    private Map<Long, Posting> postings = new HashMap<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Posting> getPostings() {
        return postings;
    }

    public void setPostings(Map<Long, Posting> postings) {
        this.postings = postings;
    }
}


