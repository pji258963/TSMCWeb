package com.tsmc.model;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class DoRandomPeopleForWinnerModel {

	private List<String> totalPeopleArrayList;
	private Set<String> winPeopleSet;
}
