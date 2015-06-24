package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.enetity.ArriveAt;
import com.enetity.DepartFrom;
import com.enetity.SpecialDeal;

@Controller
public class AirPortController {
	
	@RequestMapping(value = "/airPort")
	public ModelAndView getAirInfo(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("showAirPort");
		
		List<SpecialDeal> li = getSpecialDealList();
		
		mav.addObject("Specials", li);
		return mav;
	}
	
	private List<SpecialDeal> getSpecialDealList(){
		List<SpecialDeal> li = new ArrayList<SpecialDeal>(); 
		SpecialDeal special = new SpecialDeal();
		DepartFrom df = new DepartFrom();
		df.setName("B1");
		ArriveAt aat = new ArriveAt();
		aat.setName("A1");
		special.setDepartFrom(df);
		special.setArriveAt(aat);
		li.add(special);
		
		df.setName("B2");
		aat.setName("A2");
		special.setDepartFrom(df);
		special.setArriveAt(aat);
		li.add(special);
		return li;
	}
}
