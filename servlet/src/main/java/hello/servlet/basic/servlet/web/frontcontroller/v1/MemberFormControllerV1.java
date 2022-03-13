package hello.servlet.basic.servlet.web.frontcontroller.v1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements FrontControllerServletV1 {
    @Override
    public void process(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatchar = request.getRequestDispatcher(viewPath);
        dispatchar.forward(request, response);
    }
}
