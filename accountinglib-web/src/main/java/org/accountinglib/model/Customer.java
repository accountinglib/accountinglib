package org.accountinglib.model;

import jakarta.persistence.*;

/**
 * The Customer model object, stored in the database.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;


    private MemberCategory memberCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public MemberCategory getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(MemberCategory memberCategory) {
        this.memberCategory = memberCategory;
    }

    public enum MemberCategory {
        NOT_MEMBER,
        MEMBER,
        MEMBER_TEXTILE_DISCOUNTS
    }
}
