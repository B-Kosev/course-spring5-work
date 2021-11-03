package course.spring.service.impl;

import course.spring.model.Article;
import course.spring.qualifiers.Alternative;
import course.spring.service.ArticleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

//@Service("alternativeProvider")
//@Alternative
//@Order(2)
public class AlternativeArticleProvider implements ArticleProvider {
    @Value("${articles.titles}")
    private String[] titles;

    @Value("${articles.contents}")
    private String[] contents;

    private ArrayList<Article> articles = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i = 0; i < titles.length; i++) {
            addArticle(new Article(titles[i],contents[i]));
        }
    }
    public void addArticle(Article article){
        this.articles.add(article);
    }

    @Override
    public Collection<Article> getArticles() {
        return articles;
    }
}
