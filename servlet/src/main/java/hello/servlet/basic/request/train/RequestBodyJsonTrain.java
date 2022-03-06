package hello.servlet.basic.request.train;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonTrain", urlPatterns = "/request-json-train")
public class RequestBodyJsonTrain extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream inputStream = request.getInputStream();
        String outputStream = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("String Stream: " + outputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        HelloData helloData = objectMapper.readValue(outputStream, HelloData.class);
        System.out.println("username: " + helloData.getName());
        System.out.println("age: " + helloData.getAge());

    }
}
