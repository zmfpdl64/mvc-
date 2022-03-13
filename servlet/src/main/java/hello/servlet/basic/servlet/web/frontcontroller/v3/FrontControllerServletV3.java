package hello.servlet.basic.servlet.web.frontcontroller.v3;

import hello.servlet.basic.servlet.web.frontcontroller.ModelView;
import hello.servlet.basic.servlet.web.frontcontroller.MyView;
import hello.servlet.basic.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.basic.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    Map<String, ControllerV3> controller1 = new HashMap<>();

    public FrontControllerServletV3() {
        controller1.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controller1.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controller1.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controller1.get(requestURI);
        if(requestURI == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }
        Map<String, String> parammap = createParam(request);    //parameter를 딕셔너리로 만든다.

        ModelView mv = controller.process(parammap);  //컨트롤러를 실행하여 비즈니스로직을 실행한다.
        String viewName = mv.getViewName(); //논리적인 로직이름을 가져온다.
        MyView view = viewResolver(viewName);   //논리 파일명을 물리 파일명으로 변경해 주며 경로를 설정한다.
        view.render(mv.getModel() ,request, response);  // MyView를 통해 jsp파일에 렌더링한다.forward

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParam(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
