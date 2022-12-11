/*******************************************************************************
 * Copyright (c) 2017 ANHTCN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.example.authservice.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "ACCOUNT_USER")
public class Account implements Serializable {

	private static final long serialVersionUID = -2291526615427864866L;

	@Id
	@Column(name = "UUID_ACCOUNT")
	private String uuidAccount;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	@JsonIgnore
	private String password;

	@Column(name = "BIRTHDAY")
	private String birthday;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "LOCATION")
	private String location = "";

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "GENDER")
	private String gender = "MALE";

	@Column(name = "PROVIDER")
	private String provider = "WEB";

	@Column(name = "ENABLED")
	private boolean enabled = false;

	@Column(name = "CREDENTIALS_EXPIRED")
	private boolean credentialsExpired = false;

	@Column(name = "EXPIRED")
	private boolean expired = false;

	@Column(name = "LOCKED")
	private boolean locked = false;

	@Column(name = "USING2FA")
	private boolean using2FA = false;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ACCOUNT_ROLE", joinColumns = @JoinColumn(name = "UUID_ACCOUNT", referencedColumnName = "UUID_ACCOUNT"), inverseJoinColumns = @JoinColumn(name = "UUID_ROLE", referencedColumnName = "UUID_ROLE"))
	private Collection<Role> roles;

}