package com.tsmc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tsmc.entity.MainForWinnerEntity;
import com.tsmc.model.FindNowPrizeModel;
import com.tsmc.model.PrizePeopleModel;
import com.tsmc.model.RandomWinnerModel;
import com.tsmc.service.MainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mainService")
public class MainController {
	
	@Autowired
	MainService mainService; 
	
	@GetMapping("/mainView")
	public String Login(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		return "views/main/index";
	}
	
	@PostMapping("/createPrize")
	public ModelAndView createPrize(@ModelAttribute MainForWinnerEntity mainForWinnerEntity, Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("views/prize/prize");
		log.info("MainForWinnerEntity:{}",mainForWinnerEntity);
		mainService.createRandomPeople(mainForWinnerEntity);
		modelAndView.addObject("totalPeople", mainForWinnerEntity.getTotalPeople());
		modelAndView.addObject("totalPrize", mainForWinnerEntity.getTotalPrize());
		modelAndView.addObject("currentPeople", mainForWinnerEntity.getTotalPeople());
		
		return modelAndView;
	}
	
	@PostMapping("/doRandomWinner")
	public ModelAndView randomWinner(@ModelAttribute PrizePeopleModel prizePeopleModel, Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("views/findPrize/findPrize");
		if (Integer.parseInt(prizePeopleModel.getPrizeName())<2) {
			modelAndView.setViewName("views/findPrize/findPrize");
		}else {
			modelAndView.setViewName("views/prize/prize");
		}
		
		RandomWinnerModel randomWinnerModel  = mainService.doRandomWinner(prizePeopleModel,request.getSession());
		modelAndView.addObject("totalPrize",(Integer.parseInt(prizePeopleModel.getPrizeName())-1));
		modelAndView.addObject("currentPeople",randomWinnerModel.getCurrentPeople());
		modelAndView.addObject("mainID",randomWinnerModel.getMainID());
		
		return modelAndView;
	}
	
	
	@PostMapping("/findNowPrize")
	public ModelAndView findPrize(@ModelAttribute FindNowPrizeModel findNowPrizeModel,Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("views/findPrize/findPrize");
		modelAndView.addObject("winnerList", mainService.findPrize(findNowPrizeModel));
		return modelAndView;

	}
	
	@PostMapping("/findAllPrize")
	public ModelAndView findAllPrize(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("views/findPrize/findPrize");
		modelAndView.addObject("winnerList", mainService.findAllPrize());
		return modelAndView;

	}
	
	@PostMapping("/deletePrize")
	public ModelAndView deletePrize(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("views/main/index");
//		 mainService.doPrizeAgain();
		return modelAndView;

	}

}
