package course.spring.webflux.init;

import course.spring.webflux.dao.ArticleRepository;
import course.spring.webflux.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ArticleRepository repository;

    public static final List<Article> SAMPLE_ARTICLES = Arrays.asList(
            new Article("Title 1", "Desc 1"),
            new Article("Title 2", "Desc 2"),
            new Article("Title 3", "Desc 3")
    );

    @Override
    public void run(String... args) throws Exception {
        repository.count()
                .filter(count -> count == 0)
                .map(count -> Flux.fromIterable(SAMPLE_ARTICLES)
                            .flatMap(article -> repository.insert(article))
                )
                .log()
                .subscribe(
                        System.out::println, //next
                        System.err::println, //error
                        () -> System.out.println("Data initialization finished.") //completion
                );
    }
}
