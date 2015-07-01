package com.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.enetity.ArriveAt;
import com.enetity.DepartFrom;
import com.enetity.SpecialDeal;

/**
 * 
 * @author hanliang 2015/05/26
 * -add Object to ModelAndView return
 */
@Controller
public class AirPortController {
	
	@RequestMapping(value = "/airPort")
	public ModelAndView getAirInfo(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ShowAirPort");
		List<SpecialDeal> li = getSpecialDealList();
		mav.addObject("Specials", li);
		return mav;
	}
	
	@RequestMapping(value = "/airPortTag")
	public ModelAndView getAirTagInfo(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ShowAirPortTag");
		List<SpecialDeal> li = getSpecialDealList();
		mav.addObject("specials", li);
		return mav;
	}
	
	private List<SpecialDeal> getSpecialDealList(){
		List<SpecialDeal> li = new ArrayList<SpecialDeal>(); 
		for (int i = 1; i < 3; i++) {
			SpecialDeal special = new SpecialDeal();
			DepartFrom df = new DepartFrom();
			ArriveAt aat = new ArriveAt();
			df.setName("B"+i);
			aat.setName("A"+i);
			special.setDepartFrom(df);
			special.setArriveAt(aat);
			special.setCost(i);
			li.add(special);
		}
		return li;
	}
	
	
	
	public static void main(String[] args) {
		AirPortController ac = new AirPortController();
		List<SpecialDeal> li = ac.getSpecialDealList();
		for(Iterator i = li.iterator();i.hasNext();){
			SpecialDeal special = (SpecialDeal)i.next();
			System.out.println(special.getDepartFrom().getName());
			System.out.println(special.getArriveAt().getName());
			System.out.println(special.getCost());
		}
	}
}
