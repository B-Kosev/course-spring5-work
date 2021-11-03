package course.spring;

import course.spring.config.AppConfiguration;
import course.spring.service.ArticlePresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ArticlePresenter presenter = context.getBean("presenter", ArticlePresenter.class);
        ArticlePresenter presenter2 = context.getBean("presenter",ArticlePresenter.class);
        presenter.present();
        presenter2.present();
        System.out.println(presenter);
        System.out.println(presenter2);
    }
}
