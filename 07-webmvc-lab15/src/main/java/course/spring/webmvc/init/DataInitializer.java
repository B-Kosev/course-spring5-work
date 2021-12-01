package course.spring.webmvc.init;

import course.spring.webmvc.domain.ArticleService;
import course.spring.webmvc.domain.UserService;
import course.spring.webmvc.entity.Article;
import course.spring.webmvc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    private final List<User> DEFAULT_USERS = List.of(
            new User("admin", "admin","admin", "Admin123&"));

    private final List<Article> DEFAULT_ARTICLES = List.of(
            new Article("New in Spring", "Non-blocking reactive WebFlux services..."),
            new Article("Spring Data MongoDB - What's new?", "References between MongoDB collections support"),
            new Article("Spring Security - is it easy?", "Nope"));

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userService.count()==0){
            log.info("Successfully created users: {}",  DEFAULT_USERS.stream().map(userService::createUser).collect(Collectors.toList()));
        }

        if (articleService.count()==0){
            log.info("Successfully created articles: {}",  DEFAULT_ARTICLES.stream().map(articleService::createArticle).collect(Collectors.toList()));
        }
    }
}
