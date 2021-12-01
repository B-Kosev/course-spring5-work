package course.spring.webmvc.domain.impl;

import course.spring.webmvc.dao.ArticleRepository;
import course.spring.webmvc.domain.ArticleService;
import course.spring.webmvc.entity.Article;
import course.spring.webmvc.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository=articleRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Article with ID=%s not found", id)));
    }

    @Override
    public Article findByTitle(String title) {
        return articleRepository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException(String.format("Article with title=%s not found", title)));

    }

    @Override
    public Article createArticle(Article article) {
        article.setId(null);
        article.setCreated(LocalDateTime.now());
        article.setModified(LocalDateTime.now());
       return articleRepository.insert(article);
    }

    @Override
    public Article updateArticle(Article article) {
        Article oldArticle = findById(article.getId());
        article.setTitle(oldArticle.getTitle());
        article.setCreated(oldArticle.getCreated());
        article.setModified(LocalDateTime.now());
        return articleRepository.save(article);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public long count() {
        return articleRepository.count();
    }
}