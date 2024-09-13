package com.csc340.demo;

public class WhiskeyHunter {
    public String name;
    public String slug;
    public String base_currency;

    public WhiskeyHunter(String name, String slug, String base_currency) {
        this.name = name;
        this.slug = slug;
        this.base_currency = base_currency;
    }

    public String getName() { return name; }

    public void setName(String name) {this.name = name;}

    public String getSlug() {return slug;}

    public void setSlug(String slug) {this.slug = slug;}

    public String getBase_currency() {return base_currency;}

    public void setBase_currency(String base_currency) {this.base_currency = base_currency;}
}
