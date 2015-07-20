package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.entity.Article;

@Component("ArticleOperation")
public interface ArticleOperation {

	public List<Article> getUserArticles(int id);
	
}
