package course.spring.introlab15.web;

import course.spring.introlab15.dao.ArticleRepo;
import course.spring.introlab15.dao.ArticleRepoImpl;
import course.spring.introlab15.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleRepo repository;

    @GetMapping
    public Collection<Article> getAllArticles(){
        return repository.findAll();
    }
}
