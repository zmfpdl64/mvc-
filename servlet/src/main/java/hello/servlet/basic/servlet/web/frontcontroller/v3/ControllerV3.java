package hello.servlet.basic.servlet.web.frontcontroller.v3;

import hello.servlet.basic.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
