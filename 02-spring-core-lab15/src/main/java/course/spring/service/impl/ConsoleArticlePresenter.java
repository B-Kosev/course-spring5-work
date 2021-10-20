package course.spring.service.impl;

import course.spring.model.Article;
import course.spring.service.ArticlePresenter;
import course.spring.service.ArticleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("presenter")
public class ConsoleArticlePresenter implements ArticlePresenter {
    private ArticleProvider articleProvider;

    @Autowired
    ConsoleArticlePresenter(ArticleProvider articleProvider) {
        this.articleProvider = articleProvider;
    }

    @Override
    public void present() {
        articleProvider.getArticles().forEach(System.out::println);
    }
}
