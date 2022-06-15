package com.tsmc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsmc.entity.MainForWinnerEntity;
import com.tsmc.entity.WinnerListEntiry;
import com.tsmc.model.DoRandomPeopleForWinnerModel;
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

    public void createRandomPeople(MainForWinnerEntity mainForWinnerEntity) {
        mainForWinnerEntity.setCurrentPeople(mainForWinnerEntity.getTotalPeople());
        mainForWinnerEntity.setWinnerDate(com.tsmc.util.Util.getFormatToday());
        mainRepository.save(mainForWinnerEntity);
    }

    public RandomWinnerModel doRandomWinner(PrizePeopleModel prizePeopleModel, HttpSession httpSession) {
        ArrayList<String> prizePeopleArrayList = new ArrayList<String>();
        WinnerListEntiry winListEntity = new WinnerListEntiry();
        RandomWinnerModel randomWinnerModel = new RandomWinnerModel();
        MainForWinnerEntity mainForWinnerEntity = mainRepository.findByDate(Util.getFormatToday());
        log.info(String.valueOf(mainForWinnerEntity.getId()));

        int totalPeople = Integer.valueOf(mainForWinnerEntity.getTotalPeople());
        int prizePeople = Integer.valueOf(prizePeopleModel.getPrizePeople());
        int currentPeople = Integer.valueOf(mainForWinnerEntity.getCurrentPeople())
                - Integer.valueOf(prizePeopleModel.getPrizePeople());
        int startIndex = totalPeople - (currentPeople + prizePeople);

        if (mainForWinnerEntity.getTotalSet() != null) {
            mainForWinnerEntity.setTotalSet(mainForWinnerEntity.getTotalSet().replace("[", "").replace("]", ""));
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.addAll(Arrays.asList(mainForWinnerEntity.getTotalSet().trim().split(",")));
            List<String> totalHashList = new ArrayList<String>();
            totalHashList = tempList.stream().map(s -> s.trim()).collect(Collectors.toList());

            prizePeopleArrayList.addAll(totalHashList);
            log.info("prizePeopleArrayList Size:{}", prizePeopleArrayList.size());

            DoRandomPeopleForWinnerModel doRandomPeopleForWinnerModel = doRandomPeopleForWinner(prizePeopleArrayList,
                    startIndex, prizePeople);
            mainRepository.updatePeopleAndCurrentPoppleByDate(
                    doRandomPeopleForWinnerModel.getTotalPeopleArrayList().toString(), String.valueOf(currentPeople),
                    Util.getFormatToday());
            winListEntity.setPeople(doRandomPeopleForWinnerModel.getWinPeopleSet().toString());
        } else {
            ArrayList<String> totalPeopleArrayList = createPeopleArray(totalPeople);
            DoRandomPeopleForWinnerModel doRandomPeopleForWinnerModel = doRandomPeopleForWinner(totalPeopleArrayList,
                    startIndex, prizePeople);
            winListEntity.setPeople(doRandomPeopleForWinnerModel.getWinPeopleSet().toString());
            mainRepository.updatePeopleAndCurrentPoppleByDate(
                    doRandomPeopleForWinnerModel.getTotalPeopleArrayList().toString(), String.valueOf(currentPeople),
                    Util.getFormatToday());
        }

        winListEntity.setMainId(mainForWinnerEntity.getId());
        winListEntity.setPrize(prizePeopleModel.getPrizeName());
        winListEntity.setWinnerDate(com.tsmc.util.Util.getFormatToday());

        winnerListRepository.save(winListEntity);

        randomWinnerModel.setCurrentPeople(String.valueOf(currentPeople));
        randomWinnerModel.setMainID(String.valueOf(mainForWinnerEntity.getId()));

        return randomWinnerModel;
    }

    private DoRandomPeopleForWinnerModel doRandomPeopleForWinner(ArrayList<String> totalPeopleArrayList, int startIndex,
            int prizePeople) {
        HashSet<String> winPeopleSet = new HashSet<String>();
        DoRandomPeopleForWinnerModel doRandomPeopleForWinnerModel = new DoRandomPeopleForWinnerModel();
        for (int i = startIndex; i < (startIndex + prizePeople); i++) {
            winPeopleSet.add(totalPeopleArrayList.get(i));
        }
        log.info("totalPeopleArrayList Size:{}", totalPeopleArrayList.size());
        log.info("winPeopleSet Size:{}", winPeopleSet.size());

        doRandomPeopleForWinnerModel.setTotalPeopleArrayList(totalPeopleArrayList);
        doRandomPeopleForWinnerModel.setWinPeopleSet(winPeopleSet);

        return doRandomPeopleForWinnerModel;
    }

    public List<WinnerListEntiry> findPrize(FindNowPrizeModel findNowPrizeModel) {
        MainForWinnerEntity mainForWinnerEntity = mainRepository.findByDate(Util.getFormatToday());
        return winnerListRepository.getWinnerListByDate(Util.getFormatToday(),
                String.valueOf(mainForWinnerEntity.getId()));
    }

    public void doPrizeAgain() {
        winnerListRepository.deleteByDate(com.tsmc.util.Util.getFormatToday());
    }

    private ArrayList<String> createPeopleArray(int totalPeople) {
        ArrayList<String> peopleArrayList = new ArrayList<String>();
        for (int i = 1; i <= totalPeople; i++) {
            peopleArrayList.add(String.valueOf(i));
        }
        // 打亂排序
        Collections.shuffle(peopleArrayList, new Random());
        return peopleArrayList;
    }

    public List<WinnerListEntiry> findAllPrize() {
        List<WinnerListEntiry> winnerListEntiries = new ArrayList<WinnerListEntiry>();
        List<MainForWinnerEntity> mainForWinnerEntities = mainRepository.findTodyMainID(Util.getFormatToday());

        for (MainForWinnerEntity mainForWinnerEntity : mainForWinnerEntities) {
            winnerListEntiries.addAll(winnerListRepository.getWinnerListByDate(Util.getFormatToday(),
                    String.valueOf(mainForWinnerEntity.getId())));
        }
        return winnerListEntiries;
    }
}
