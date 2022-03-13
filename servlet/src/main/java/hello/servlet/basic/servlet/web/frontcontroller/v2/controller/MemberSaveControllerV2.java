package hello.servlet.basic.servlet.web.frontcontroller.v2.controller;

import hello.servlet.basic.servlet.web.frontcontroller.MyView;
import hello.servlet.basic.servlet.web.frontcontroller.v2.FrontControllerV2;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements FrontControllerV2 {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        request.setAttribute("member", member);

        String urlpath = "/WEB-INF/views/save-result.jsp";
        return new MyView(urlpath);
    }
}
