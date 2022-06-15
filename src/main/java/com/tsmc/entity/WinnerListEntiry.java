package com.tsmc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "WINNER_LIST")
public class WinnerListEntiry {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private int id; 
	
	
	@Column(name="MAIN_ID")
	private int mainId;
	
	@Column(name="PEIZE")
	private String prize;
	
	@Column(name="WINNER_DATE")
	private String winnerDate;
	
	@Column(name="PEOPLE")
	private String people;

}
