package core.spring.rest.web;

import core.spring.rest.dao.ArticleRepo;
import core.spring.exception.EntityNotFoundException;
import core.spring.rest.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    @Autowired
    private ArticleRepo articleRepo;

    @GetMapping
    public List<Article> getArticles() {
        return articleRepo.findAll();
    }

    @GetMapping("/{id}")
    public Article getArticles(@PathVariable String id) {
        return articleRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Entity with ID=%s does not exist.", id)));
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article created = articleRepo.insert(article);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}").buildAndExpand(created.getId()).toUri()
//                MvcUriComponentsBuilder.
//                        fromMethodName(ArticlesController.class, "addArticle", Article.class)
//                        .pathSegment("{id}").buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

}