package hello.servlet.basic.servlet.web.frontcontroller.v1;

import hello.servlet.basic.servlet.web.frontcontroller.FrontControllerServletV1;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements FrontControllerServletV1 {

    MemberRepository memberRepository = MemberRepository.getInstance();
    
    @Override
    public void process(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model 데이터를 보관한다.
        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
