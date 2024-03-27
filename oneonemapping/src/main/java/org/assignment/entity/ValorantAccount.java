package org.assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "valorant_account")
public class ValorantAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String valorantUsername;

    @OneToOne
    @JoinColumn(name = "riot_unique_id")
    private RiotAccount riotAccount;

    public RiotAccount getRiotAccount() {
        return riotAccount;
    }

    public void setRiotAccount(RiotAccount riotAccount) {
        this.riotAccount = riotAccount;
    }

    public ValorantAccount() {
    }

    public ValorantAccount(String valorantUsername) {
        this.valorantUsername = valorantUsername;
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
                "ValorantId=" + id +
                ", valorantUsername='" + valorantUsername + '\'' +
                '}';
    }
}
