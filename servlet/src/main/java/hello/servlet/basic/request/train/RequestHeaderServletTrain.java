package hello.servlet.basic.request.train;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//name이 겹치면 오류가 발생한다.
@WebServlet(name = "requestHeaderServlet1", urlPatterns = "/request-servlet-train")
public class RequestHeaderServletTrain extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String [] names = request.getParameterValues("name");

        System.out.println("name: "+ name);
        System.out.println("age: " + age);
        for( String nm : names) {
            System.out.println("name :" +nm);
        }
    }
}
