package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamservlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Enumeration<String>
        System.out.println("========Param=======");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ":" + request.getParameter(paramName)));
        System.out.println("[단일 파라미터 조회]");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("username: " + name + "  " + "age : " +age);
        String [] usernames = request.getParameterValues("name");
        System.out.println("[중복 파라미터 조회]");
        for(String str : usernames) {
            System.out.println("username :" + str);
        }
        response.getWriter().write("ok");
    }
}
