package hello.servlet.basic.servlet.web.frontcontroller.v5;

import hello.servlet.basic.servlet.web.frontcontroller.ModelView;
import hello.servlet.basic.servlet.web.frontcontroller.MyView;
import hello.servlet.basic.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.basic.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.basic.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.basic.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.basic.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.basic.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.basic.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMapping();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMapping() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Object handler = getHandler(request);   //MemberFormControllerV3

        if(handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        MyHandlerAdapter adapter = getMyHandler(handler);
        //Instanceof를 통해 ControllerV3를 상속, 구현 하고 있냐?
        //있으면 V5에서 생성한 V#Adapter를 반환한다.
//        System.out.println("안녕하세요2");

        ModelView mv = adapter.handle(request, response, handler);
        //key, value, 렌더링 논리파일이름 포함한 mv 반환

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);   //템플릿 경로

        view.render(mv.getModel(), request, response);
        //mv에서 key,value를 담은 getModel,
    }

    private MyHandlerAdapter getMyHandler(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다."+ handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
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
