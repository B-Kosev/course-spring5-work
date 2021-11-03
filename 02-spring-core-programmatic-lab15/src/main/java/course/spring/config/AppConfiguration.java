package course.spring.config;

import course.spring.AnnotationConfigDemo;
import course.spring.qualifiers.Alternative;
import course.spring.qualifiers.Mock;
import course.spring.service.ArticlePresenter;
import course.spring.service.ArticleProvider;
import course.spring.service.impl.AlternativeArticleProvider;
import course.spring.service.impl.ConsoleArticlePresenter;
import course.spring.service.impl.MockArticleProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = AnnotationConfigDemo.class)
public class AppConfiguration {
    @Bean(name = "mockProvider")
    @Mock
    public ArticleProvider mockProvider(){
        return new MockArticleProvider();
    }

    @Bean(name = "alternativeProvider")
    @Alternative
    public ArticleProvider alternativeProvider(){
        return new AlternativeArticleProvider();
    }

//    @Bean(name = "presenter")
//    public ArticlePresenter presenter(@Mock ArticleProvider provider){
//        return new ConsoleArticlePresenter(provider);
//    }

    @Bean(name = "presenter")
    public ArticlePresenter presenter(@Qualifier("alternativeProvider") ArticleProvider provider){
        return new ConsoleArticlePresenter(provider);
    }
}
