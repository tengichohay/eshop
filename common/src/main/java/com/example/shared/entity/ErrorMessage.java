package com.example.shared.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "error_message")
public class ErrorMessage {

	@Id
	@Column
	private Integer id;

	@Column
	private String locale;

	@Column(name = "key")
	private String key;

	@Column(name = "content")
	private String content;

}
