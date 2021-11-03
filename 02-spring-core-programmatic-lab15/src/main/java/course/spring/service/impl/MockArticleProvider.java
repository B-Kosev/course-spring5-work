package course.spring.service.impl;

import course.spring.dao.ArticleRepository;
import course.spring.model.Article;
import course.spring.qualifiers.Mock;
import course.spring.service.ArticleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

//@Service("mockProvider")
//@Mock
//@Order(1)
public class MockArticleProvider implements ArticleProvider {
    @Autowired
    private ArticleRepository articleRepository;

    public static final List<Article> SAMPLE_ARTICLES = List.of(
            new Article("Title 1", "Desc 1"),
            new Article("Title 2", "Desc 2"),
            new Article("Title 3", "Desc 3")
    );

    @PostConstruct
    public void init(){
        SAMPLE_ARTICLES.forEach(articleRepository::create);
    }

    @Override
    public Collection<Article> getArticles() {
        return articleRepository.findAll();
    }
}
