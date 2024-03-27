package org.assignment.entity;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class GamingOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "gamingOrganization", cascade = CascadeType.ALL)
    private List<Trophies> trophies = new ArrayList<>();
    public GamingOrganization() {
    	
    }
    
    public GamingOrganization(String name) {
    	this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trophies> getTrophies() {
        return trophies;
    }

    public void setTrophies(List<Trophies> trophies) {
        this.trophies = trophies;
    }
}
