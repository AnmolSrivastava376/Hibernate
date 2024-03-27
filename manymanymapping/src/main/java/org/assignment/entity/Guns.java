package org.assignment.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guns")
public class Guns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Skins> getSkins() {
		return skins;
	}

	public void setSkins(Set<Skins> skins) {
		this.skins = skins;
	}

	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "gun_skin",
            joinColumns = {@JoinColumn(name = "gun_id")},
            inverseJoinColumns = {@JoinColumn(name = "skin_id")}
    )
    private Set<Skins> skins = new HashSet<>();

}
