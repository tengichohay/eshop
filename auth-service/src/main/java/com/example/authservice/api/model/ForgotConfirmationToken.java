package com.example.authservice.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "FORGOT_CONFIRMATION_TOKEN")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "account"})
public class ForgotConfirmationToken implements Serializable {

	private static final long serialVersionUID = 2743938183491274930L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TOKEN_ID")
	private long tokenid;

	@Column(name = "CONFIRMATION_TOKEN")
	private String confirmationToken;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@OneToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "UUID_ACCOUNT")
	private Account account;

}
