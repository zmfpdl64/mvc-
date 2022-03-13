package hello.servlet.basic.servlet.web.frontcontroller.v4.controller;

import hello.servlet.basic.servlet.web.frontcontroller.v4.FrontControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements FrontControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
