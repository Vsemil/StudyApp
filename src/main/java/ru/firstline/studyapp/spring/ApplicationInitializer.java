package ru.firstline.studyapp.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.firstline.studyapp.security.SecurityJavaConfig;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk

    private MultipartConfigElement getMultipartConfigElement() {
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
        return new MultipartConfigElement(uploadDirectory.getAbsolutePath(), MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
        super.customizeRegistration(registration);
    }

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
