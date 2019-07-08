package com.payment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="account")
public class Account {
	@Id
	private Long id;
	@Column(name = "accountnumber")
	private Integer accountNumber;
	@Column(name = "currentbalance")
	private Double currentBalance;
}
