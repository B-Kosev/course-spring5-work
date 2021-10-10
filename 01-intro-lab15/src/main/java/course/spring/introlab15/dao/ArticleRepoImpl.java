package course.spring.introlab15.dao;

import course.spring.introlab15.model.Article;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepoImpl implements ArticleRepo{
    private Map<Long, Article> articles = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(0L);

    @Override
    public Collection<Article> findAll() {
        return null;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return null;
    }

    @Override
    public Article create(Article article) {
        article.setId(nextId.getAndIncrement());
        articles.put(article.getId(),article);
        return article;
    }
}
