package ru.firstline.studyapp.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public final class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException) throws IOException {

        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

//        PrintWriter writer = response.getWriter();
//        writer.println("HTTP Status 401 : " + authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(SecurityJavaConfig.REALM);
        super.afterPropertiesSet();
    }
}

