package hello.servlet.basic.servlet.web.frontcontroller.v4.controller;

import hello.servlet.basic.servlet.web.frontcontroller.v4.FrontControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements FrontControllerV4 {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members =  memberRepository.findAll();


        model.put("members", members);
        return "members";
    }
}
