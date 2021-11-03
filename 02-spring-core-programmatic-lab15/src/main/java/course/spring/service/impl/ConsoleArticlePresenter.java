package course.spring.service.impl;

import course.spring.model.Article;
import course.spring.service.ArticlePresenter;
import course.spring.service.ArticleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

//@Service("presenter")
//@Scope("singleton")
public class ConsoleArticlePresenter implements ArticlePresenter {
    private ArticleProvider articleProvider;


    private List<ArticleProvider> articleProviders;

//      @Resource(name = "alternativeProvider")
//    public void setArticleProviders(List<ArticleProvider> articleProviders) {
//        this.articleProviders = articleProviders;
//    }
//
//    public List<ArticleProvider> getArticleProviders() {
//        return articleProviders;
//    }

//    @Inject
//    public ConsoleArticlePresenter(@Qualifier("alternativeProvider")ArticleProvider provider) {
//        this.articleProvider = provider;
//    }


    public ConsoleArticlePresenter(ArticleProvider articleProvider) {
        this.articleProvider = articleProvider;
    }

    @Override
    public void present() {
        articleProvider.getArticles().forEach(System.out::println);
//        articleProviders.stream().flatMap(articleProvider -> articleProvider.getArticles().stream()).forEach(System.out::println);
    }
}
