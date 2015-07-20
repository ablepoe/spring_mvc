package com.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Article;
import com.service.ArticleService;

@Controller
public class ArticleController {

	@Resource(name="ArticleService")
	private ArticleService articleService;
	
	@RequestMapping(value = "/findArticle")
	public ModelAndView findArticle(){
		System.out.println("findArticle");
		ModelAndView mav = new ModelAndView();
		List<Article> li = articleService.getUserArticles(1);
		ObjectMapper mapper = new ObjectMapper();
		for (int i = 0; i < li.size(); i++) {
			try {
				System.out.println( mapper.writeValueAsString(li.get(i)) );
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		mav.setViewName("article");
		return mav;
	}
	
//	@ResponseBody
	@RequestMapping(value = "/ajaxArticle")
	public ModelAndView ajaxArticle(){
		System.out.println("ajaxArticle");
		ModelAndView mav = new ModelAndView();
		List<Article> li = articleService.getUserArticles(1);
		ObjectMapper mapper = new ObjectMapper();
		for (int i = 0; i < li.size(); i++) {
			try {
				System.out.println( mapper.writeValueAsString(li.get(i)) );
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		mav.setViewName("article");
		mav.addObject("userArticle", li);
		return mav;
	}
}
