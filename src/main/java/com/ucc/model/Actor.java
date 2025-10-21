package com.ucc.model;

public class Actor {
    private Long actor_id;
    private String first_name;
    private String last_name;
    
    public Long getActor_id() {
        return actor_id;
    }
    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    @Override
    public String toString() {
        return "Actor [actor_id=" + actor_id + ", first_name=" + first_name + ", last_name=" + last_name + "]";
    }

    
    
}
