package org.assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "valorant_account")
public class ValorantAccount {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String valorantUsername;

    public ValorantAccount() {
    }

    public ValorantAccount(String valorantUsername, int id) {
        this.valorantUsername = valorantUsername;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValorantUsername() {
        return valorantUsername;
    }

    public void setValorantUsername(String valorantUsername) {
        this.valorantUsername = valorantUsername;
    }

    @Override
    public String toString() {
        return "ValorantAccount{" +
                "id=" + id +
                ", valorantUsername='" + valorantUsername + '\'' +
                '}';
    }
}
