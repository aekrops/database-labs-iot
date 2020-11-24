package com.aekrops.model.entity;

import com.aekrops.model.annotation.Column;
import com.aekrops.model.annotation.PrimaryKey;
import com.aekrops.model.annotation.Table;

@Table(name = "stadium")
public class Stadium {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public Stadium() {

    }

    public Stadium(String name, String city, String country) {
        this(-1, name, city, country);
    }

    public Stadium(Integer id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "\n---------------\nid= " + id + ", \nname= " + name + ", \ncity= " + city + ", \ncountry= " + country;
    }
}
