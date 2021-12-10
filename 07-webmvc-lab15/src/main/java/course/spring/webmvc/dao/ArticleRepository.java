package course.spring.webmvc.dao;

import course.spring.webmvc.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article, String> {
    Optional<Article> findByTitle(String title);
}
