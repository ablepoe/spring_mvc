package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.ArticleOperation;
import com.entity.Article;
import com.service.ArticleService;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier("articleMapper")
	private ArticleOperation articleOperation;
	
	@Override
	public List<Article> getUserArticles(int id) {
		return articleOperation.getUserArticles(id);
	}

}
