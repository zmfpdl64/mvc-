package hello.servlet.basic.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface FrontControllerV4 {

    String process(Map<String, String> paramMap, Map<String, Object> model);
}
