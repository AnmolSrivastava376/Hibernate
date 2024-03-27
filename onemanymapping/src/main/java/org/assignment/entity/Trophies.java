package org.assignment.entity;

import jakarta.persistence.*;

@Entity
public class Trophies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private GamingOrganization gamingOrganization;
    
    public Trophies() {
    	
    }
    
    public Trophies(String name) {
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

    public GamingOrganization getGamingOrganization() {
        return gamingOrganization;
    }

    public void setGamingOrganization(GamingOrganization gamingOrganization) {
        this.gamingOrganization = gamingOrganization;
    }
}