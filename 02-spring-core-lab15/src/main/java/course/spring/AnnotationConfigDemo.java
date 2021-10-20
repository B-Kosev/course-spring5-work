package course.spring;

import course.spring.service.ArticlePresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("course.spring");
        ArticlePresenter presenter = context.getBean("presenter", ArticlePresenter.class);
        presenter.present();
    }
}
