package com.aekrops.model.entity;

public class Team {

    private Integer id;

    private String name;

    private Integer teamStatisticId;

    private Integer coachId;

    public Team() {

    }

    public Team(String name, Integer teamStatisticId, Integer coachId) {
        this(-1, name, teamStatisticId, coachId);
    }

    public Team(Integer id, String name, Integer teamStatisticId, Integer coachId) {
        this.id = id;
        this.name = name;
        this.teamStatisticId = teamStatisticId;
        this.coachId = coachId;
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

    public Integer getTeamStatisticId() {
        return teamStatisticId;
    }

    public void setTeamStatisticId(Integer teamStatisticId) {
        this.teamStatisticId = teamStatisticId;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    @Override
    public String toString() {
        return "\n---------------\nid= " + id + ", \nname= " + name + ", \nteam_statistic_id= " + teamStatisticId +
                ", \ncoach_id= " + coachId;
    }
}

