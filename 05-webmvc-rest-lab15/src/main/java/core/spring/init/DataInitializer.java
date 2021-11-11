package core.spring.init;

import core.spring.dao.ArticleRepo;
import core.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    public static final List<Article> SAMPLE = List.of(
            new Article("Article 1", "Sample text for article 1."),
            new Article("Article 2", "Sample text for article 2."),
            new Article("Article 3", "Sample text for article 3.")
    );

    @Autowired
    private ArticleRepo repository;

    @Override
    public void run(String... args) throws Exception {
        SAMPLE.forEach(repository::insert);
    }
}
