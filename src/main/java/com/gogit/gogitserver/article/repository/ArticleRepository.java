package com.gogit.gogitserver.article.repository;

import com.gogit.gogitserver.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
