package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="trainResponseHeaderServlet", urlPatterns = "/train-response-header")
public class TrainResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);



        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello my head");

        Cookie cookie = new Cookie("my-cookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", "/basic/hello-form.html"); //Location이라는 이름을 사용하여 경로를 설정해야한다.
//        response.sendRedirect("/basic/hello-form.html");    //실제 리다이렉트하는 코드
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요 ㅎㅎ");

    }
}
