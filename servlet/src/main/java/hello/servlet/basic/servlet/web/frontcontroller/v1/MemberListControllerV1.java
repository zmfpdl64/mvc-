package hello.servlet.basic.servlet.web.frontcontroller.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements FrontControllerServletV1 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);
        String filePath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(filePath);
        dispatcher.forward(request, response);

    }
}
