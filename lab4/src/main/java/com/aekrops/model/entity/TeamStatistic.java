package com.aekrops.model.entity;

public class TeamStatistic {

    private Integer id;

    private Integer victories;

    private Integer percentageHitsOnTarget;

    public TeamStatistic() {

    }

    public TeamStatistic(Integer victories, Integer percentageHitsOnTarget) {
        this(-1, victories, percentageHitsOnTarget);
    }

    public TeamStatistic(Integer id, Integer victories, Integer percentageHitsOnTarget) {
        this.id = id;
        this.victories = victories;
        this.percentageHitsOnTarget = percentageHitsOnTarget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVictories() {
        return victories;
    }

    public void setVictories(Integer victories) {
        this.victories = victories;
    }

    public Integer getPercentageHitsOnTarget() {
        return percentageHitsOnTarget;
    }

    public void setPercentageHitsOnTarget(Integer percentageHitsOnTarget) {
        this.percentageHitsOnTarget = percentageHitsOnTarget;
    }

    @Override
    public String toString() {
        return "\n---------------\nid= " + id + ", \nvictories= " + victories +
                ", \npercentageHitsOnTarget= " + percentageHitsOnTarget;
    }
}
