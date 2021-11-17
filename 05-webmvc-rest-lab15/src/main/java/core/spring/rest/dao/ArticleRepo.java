package core.spring.rest.dao;

import core.spring.rest.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepo extends MongoRepository<Article, String> {
}
