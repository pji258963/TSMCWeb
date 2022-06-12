package com.tsmc.entity;

import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MAIN_FOR_WINNER")
@Data
public class MainForWinnerEntity {
	
	
	@Id
	@Column(name="ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator (name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private int id; 
	
	@Column(name="WINNG_DATE")
	private String winnerDate; 
	
	
	@Column(name="TOTAL_PEOPLE")
	private String totalPeople; 
	
	@Column(name="TOTAL_PRIZE")
	private String totalPrize;
	
	@Column(name="CURRENT_PEOPLE")
	private String currentPeople;
	
	@Column(name="TOTAL_SET")
	private String totalSet;
}
