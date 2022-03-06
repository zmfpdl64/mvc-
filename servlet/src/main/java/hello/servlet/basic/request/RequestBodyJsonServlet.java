package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageInput = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageInput : " + messageInput);
        HelloData helloData = objectMapper.readValue(messageInput, HelloData.class);
        //제이슨형식의 key value값이 전달되면 hellodata form의 key와 변수명이 일치하는 것 끼리 연결하여 저장된다.
        //즉 helloData라는 객체로 사용할 수 있는 것이다.
        System.out.println("helloData.username = " + helloData.getName());
        System.out.println("helloData.age = " + helloData.getAge());
    }
}
