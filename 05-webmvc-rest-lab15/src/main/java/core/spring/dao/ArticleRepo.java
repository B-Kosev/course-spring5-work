package core.spring.dao;

import core.spring.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepo extends MongoRepository<Article, String> {
}
