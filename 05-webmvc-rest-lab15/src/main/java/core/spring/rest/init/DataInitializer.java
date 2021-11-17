package core.spring.rest.init;

import core.spring.rest.dao.ArticleRepo;
import core.spring.rest.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    public static final List<Article> SAMPLE_ARTICLES = List.of(
            new Article("New in Spring 5", "WebFlux is here ..."),
            new Article("DI in Spring", "To @Autowitre or not to autowire ..."),
            new Article("AOP for Everybody", "Spring AOP is good middle ground ...")
    );
    @Autowired
    private ArticleRepo articleRepository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing...");
        SAMPLE_ARTICLES.forEach(articleRepository::insert);
        System.out.println("Finished initialization.");
    }
}
