package hello.servlet.basic.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.basic.servlet.web.frontcontroller.ModelView;
import hello.servlet.basic.servlet.web.frontcontroller.v4.FrontControllerV4;
import hello.servlet.basic.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof FrontControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        FrontControllerV4 controller = (FrontControllerV4) handler;
        Map<String, String> paramMap = createParam(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }
    private Map<String, String> createParam(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
