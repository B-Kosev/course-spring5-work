package course.spring.webmvc.domain;

import course.spring.webmvc.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();
    Article findById(String id);
    Article findByTitle(String title);
    Article create(Article article);
    Article update(Article article);
    Article deleteById(String id);
    long count();
}
