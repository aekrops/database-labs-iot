package com.aekrops.model.entity;

import com.aekrops.model.annotation.Column;
import com.aekrops.model.annotation.PrimaryKey;
import com.aekrops.model.annotation.Table;

@Table(name = "player")
public class Player {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "team_id")
    private Integer team_id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private Integer age;
    
    public Player() {
        
    }
    
    public Player(Integer team_id, String name, Integer age) {
        this(-1, team_id, name, age);
    }
    
    public Player(Integer id, Integer team_id, String name, Integer age) {
        this.id = id;
        this.team_id = team_id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "\n---------------\nid= " + id + ", \nteam_id= " + team_id + ", \nname= " + name + ", \nage= " + age;
    }
}
