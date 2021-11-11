package core.spring.web;

import core.spring.dao.ArticleRepo;
import core.spring.exception.EntitiNotFoundException;
import core.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    @Autowired
    private ArticleRepo repository;

    @GetMapping
    public List<Article> getArticles() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new EntitiNotFoundException(String.format("Entity with id %s not found", id)));
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article created = repository.insert(article);
        return ResponseEntity.created(MvcUriComponentsBuilder
                .fromMethodName(ArticlesController.class, "addArticle", Article.class)
                .pathSegment("{id}")
                .buildAndExpand(created.getId())
                .toUri()).body(created);
    }
}