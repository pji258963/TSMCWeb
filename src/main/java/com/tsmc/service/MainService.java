package com.tsmc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsmc.entity.MainForWinnerEntity;
import com.tsmc.entity.WinnerListEntiry;
import com.tsmc.model.FindNowPrizeModel;
import com.tsmc.model.PrizePeopleModel;
import com.tsmc.model.RandomWinnerModel;
import com.tsmc.repository.MainRepository;
import com.tsmc.repository.WinnerListRepository;
import com.tsmc.util.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainService {
	
	@Autowired
	MainRepository mainRepository;
	
	@Autowired
	WinnerListRepository winnerListRepository;
	
	
	
	public void createRandomPeople(MainForWinnerEntity mainForWinnerEntity){
		mainForWinnerEntity.setCurrentPeople(mainForWinnerEntity.getTotalPeople());
		mainForWinnerEntity.setWinnerDate(com.tsmc.util.Util.getFormatToday());
		mainRepository.save(mainForWinnerEntity);
//		winnerListRepository.deleteByDate(com.tsmc.util.Util.getFormatToday());
	}
	
	public RandomWinnerModel doRandomWinner(PrizePeopleModel prizePeopleModel,HttpSession httpSession){
		HashSet<Integer> prizePeopleSet = new HashSet<Integer>();
		WinnerListEntiry wiinListEntiry = new WinnerListEntiry();
		RandomWinnerModel randomWinnerModel = new RandomWinnerModel();
		MainForWinnerEntity mainForWinnerEntity = mainRepository.findByDate(Util.getFormatToday());
		log.info(String.valueOf(mainForWinnerEntity.getId()));
		
		int totalPeople = Integer.valueOf(mainForWinnerEntity.getTotalPeople());
		int prizePeople = Integer.valueOf(prizePeopleModel.getPrizePeople());
		int currentPeople = Integer.valueOf(mainForWinnerEntity.getCurrentPeople())-Integer.valueOf(prizePeopleModel.getPrizePeople());
		if (mainForWinnerEntity.getTotalSet()!=null) {
			mainForWinnerEntity.setTotalSet(mainForWinnerEntity.getTotalSet().replace("[", "").replace("]", ""));
			HashSet<String> tempSet = new  HashSet<String>();
			tempSet.addAll(Arrays.asList(mainForWinnerEntity.getTotalSet().trim().split(",")));
			HashSet<Integer> totalHashSet = new  HashSet<Integer>();
			totalHashSet = (HashSet<Integer>) tempSet.stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toSet());
			prizePeopleSet.addAll(totalHashSet);
			
			totalHashSet = randomSet(1,totalPeople,prizePeople+totalHashSet.size() ,totalHashSet);
			HashSet<Integer> finalHashSet = new HashSet<Integer>();
			finalHashSet.addAll(totalHashSet);
			
			
			mainRepository.upDatePeopleAndCurrentPoppleByDate(finalHashSet.toString(), String.valueOf(currentPeople), Util.getFormatToday());
			
			
			totalHashSet.removeAll(prizePeopleSet);
			wiinListEntiry.setPeople(totalHashSet.toString());
			
		}else {
			HashSet<Integer> totalHashSet = new HashSet<Integer>();
			prizePeopleSet = randomSet(1,totalPeople,prizePeople ,totalHashSet);
			wiinListEntiry.setPeople(prizePeopleSet.toString());
			
			mainRepository.upDatePeopleAndCurrentPoppleByDate(prizePeopleSet.toString(), String.valueOf(currentPeople), Util.getFormatToday());
		}
		
		wiinListEntiry.setMainId(mainForWinnerEntity.getId());
		wiinListEntiry.setPrize(prizePeopleModel.getPrizeName());
		wiinListEntiry.setWinnerDate(com.tsmc.util.Util.getFormatToday());
		
		
		winnerListRepository.save(wiinListEntiry);
		
		randomWinnerModel.setCurrentPeople(String.valueOf(currentPeople));
		randomWinnerModel.setMainID(String.valueOf(mainForWinnerEntity.getId()));
		
		return randomWinnerModel;
	}
	
	public List<WinnerListEntiry> findPrize(FindNowPrizeModel findNowPrizeModel) {
		MainForWinnerEntity mainForWinnerEntity = mainRepository.findByDate(Util.getFormatToday());
		return winnerListRepository.getWinnerListByDate(Util.getFormatToday(),String.valueOf(mainForWinnerEntity.getId()));
	}

	public void doPrizeAgain() {
		winnerListRepository.deleteByDate(com.tsmc.util.Util.getFormatToday());
	}
	
	private  HashSet<Integer> randomSet(int min, int max, int prizePeople, HashSet<Integer> totalHashSet) { 
		while (totalHashSet.size() < prizePeople ) {
		int num = (int) (Math.random() * (max - min))*min; 
		totalHashSet.add(num);
		}
		return totalHashSet;	
	}

	public List<WinnerListEntiry> findAllPrize() {
		List<WinnerListEntiry> winnerListEntiries = new ArrayList<WinnerListEntiry>();
		List<MainForWinnerEntity> mainForWinnerEntities = mainRepository.findTodyMainID(Util.getFormatToday());
		
		for (MainForWinnerEntity mainForWinnerEntity : mainForWinnerEntities) {
			winnerListEntiries.addAll(winnerListRepository.getWinnerListByDate(Util.getFormatToday(),String.valueOf(mainForWinnerEntity.getId())));
		}
		
		
		return winnerListEntiries;
	}

	
}
