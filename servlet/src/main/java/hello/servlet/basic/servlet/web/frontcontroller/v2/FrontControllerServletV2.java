package hello.servlet.basic.servlet.web.frontcontroller.v2;

import hello.servlet.basic.servlet.web.frontcontroller.MyView;
import hello.servlet.basic.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.basic.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.basic.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, FrontControllerV2> controllerv2 = new HashMap<>();

    public FrontControllerServletV2() {
        controllerv2.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerv2.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerv2.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getRequestURI();
        FrontControllerV2 ctr = controllerv2.get(urlPath);
        if(ctr == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }
        MyView view = ctr.process(request, response);
        view.render(request, response);
    }
}
