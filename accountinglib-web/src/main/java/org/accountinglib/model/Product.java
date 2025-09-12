package org.accountinglib.model;

import jakarta.persistence.*;

/**
 * The Product model object, stored in the database.
 */
@Entity
@Table(name = "product")
public class Product {

    public Product () {

    }

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private ProductCategory category;

    double price;

    double memberDiscount;

    private String description;

    private int number;

    private double weight;

    private String flavour;

    private String colour;


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }


    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory  category) {
        this.category = category;
    }

    public double getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(double memberDiscount) {
        this.memberDiscount = memberDiscount;
    }


    public double getFinalPrice(Customer customer) {
        if (customer.getMemberCategory() == Customer.MemberCategory.MEMBER &&
                (getCategory() == ProductCategory.FURNITURE || getCategory() == ProductCategory.HOT_DOG) ) {
            return getPrice() - getMemberDiscount();
        } else {
            return getPrice();
        }
    }

    public enum ProductCategory {
        FURNITURE,
        TEXTILE,
        HOT_DOG
    }
}
