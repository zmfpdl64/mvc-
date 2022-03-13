package hello.servlet.basic.servlet.web.frontcontroller.v2.controller;

import hello.servlet.basic.servlet.web.frontcontroller.MyView;
import hello.servlet.basic.servlet.web.frontcontroller.v2.FrontControllerV2;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements FrontControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        String urlpath = "/WEB-INF/views/members.jsp";
        return new MyView(urlpath);

    }
}
