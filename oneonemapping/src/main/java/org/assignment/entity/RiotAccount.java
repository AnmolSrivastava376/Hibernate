package org.assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "riot_account")
public class RiotAccount {

    @Id
    @Column(name = "riot_unique_id")
    private String riotUniqueId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "riotAccount", cascade = CascadeType.ALL)
    private ValorantAccount valorantAccount;

    public ValorantAccount getValorantAccount() {
        return valorantAccount;
    }

    public void setValorantAccount(ValorantAccount valorantAccount) {
        this.valorantAccount = valorantAccount;
    }

    public RiotAccount() {
    }

    public RiotAccount(String riotUniqueId, String username, String password) {
        this.riotUniqueId = riotUniqueId;
        this.username = username;
        this.password = password;
    }

    public String getRiotUniqueId() {
        return riotUniqueId;
    }

    public void setRiotUniqueId(String riotUniqueId) {
        this.riotUniqueId = riotUniqueId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RiotAccount{" +
                "riotUniqueId='" + riotUniqueId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
