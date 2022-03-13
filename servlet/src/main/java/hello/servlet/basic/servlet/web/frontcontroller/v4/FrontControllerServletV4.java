package hello.servlet.basic.servlet.web.frontcontroller.v4;

import hello.servlet.basic.servlet.web.frontcontroller.ModelView;
import hello.servlet.basic.servlet.web.frontcontroller.MyView;
import hello.servlet.basic.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.basic.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    Map<String, FrontControllerV4> controller = new HashMap<>();

    public FrontControllerServletV4() {
        controller.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controller.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controller.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        FrontControllerV4 controllerV4 = controller.get(requestURI);

        if(controllerV4 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramNames -> paramMap.put(paramNames, request.getParameter(paramNames)));
        Map<String, Object> model = new HashMap<>();        //v3에서는 (httpRequestServlet)request객체에 key value를 담았지만
                                                            //v4에서는 새로운 (Map<String, Object>)model 객체를 전달하여 사용한다.
        String viewName = controllerV4.process(paramMap, model);

        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        view.render(model, request, response);

    }
}
