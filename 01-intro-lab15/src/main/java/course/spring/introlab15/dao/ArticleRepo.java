package course.spring.introlab15.dao;

import course.spring.introlab15.model.Article;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

public interface ArticleRepo {
    Collection<Article> findAll();
    Optional<Article> findById(Long id);
    Article create(Article article);

}
