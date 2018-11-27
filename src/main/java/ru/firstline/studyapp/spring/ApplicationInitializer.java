package ru.firstline.studyapp.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.firstline.studyapp.security.SecurityJavaConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                ApplicationConfiguration.class,
                SecurityJavaConfig.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }
}
