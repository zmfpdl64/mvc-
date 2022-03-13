package hello.servlet.basic.servlet.web.frontcontroller.v1;

import hello.servlet.basic.servlet.web.frontcontroller.v1.FrontControllerServletV1;
import hello.servlet.basic.servlet.web.frontcontroller.v1.MemberFormControllerV1;
import hello.servlet.basic.servlet.web.frontcontroller.v1.MemberListControllerV1;
import hello.servlet.basic.servlet.web.frontcontroller.v1.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {
    private Map<String, FrontControllerServletV1> controllerMap = new HashMap<>();

    public FrontControllerV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello");
        String requesturl = request.getRequestURI();
        FrontControllerServletV1 controller = controllerMap.get(requesturl);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
