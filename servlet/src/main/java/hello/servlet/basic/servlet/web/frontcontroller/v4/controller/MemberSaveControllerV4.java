package hello.servlet.basic.servlet.web.frontcontroller.v4.controller;

import hello.servlet.basic.servlet.web.frontcontroller.ModelView;
import hello.servlet.basic.servlet.web.frontcontroller.v4.FrontControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.Map;
import java.util.Objects;

public class MemberSaveControllerV4 implements FrontControllerV4 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }
}
