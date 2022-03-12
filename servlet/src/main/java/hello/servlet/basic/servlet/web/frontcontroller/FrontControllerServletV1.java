package hello.servlet.basic.servlet.web.frontcontroller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public interface FrontControllerServletV1 {
    void process(ServletRequest request, ServletResponse response) throws ServletException, IOException;
}
