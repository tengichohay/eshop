package com.example.authservice.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UUID_ROLE")
	private String uuidRole;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "UUID_APPILICATION")
	private String applicationName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLE_PRIVILEGE", joinColumns = @JoinColumn(name = "UUID_ROLE", referencedColumnName = "UUID_ROLE"), inverseJoinColumns = @JoinColumn(name = "UUID_PRIVILEGE", referencedColumnName = "UUID_PRIVILEGE"))
	@JsonIgnore
	private Collection<Privilege> privileges;

	@Override
	public String toString() {
		return "Role [UUID=" +
				uuidRole +
				", name=" +
				name +
				"]";
	}

}
