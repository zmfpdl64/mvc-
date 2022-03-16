# mvc_base

<h1><쓰레드/h1>

<h3>단일 요청 - 쓰레드 하나 사용 </h3>


성능 테스트 앱

1. 아파치 ab
2. 제이미터
3. nGrinder

<strong>WAS의 멀티 쓰레드 지원을 해준다.</strong>

Was사용시 주의사항

공유변수값이 공유되는것을 조심해야 한다.

<h1>HTML, HTTP API, CSR, SSR</h1>

API를 이용하여 웹과 앱에 동시에 데이터를 뿌려줄 수 있다.

<li> UI 클라이언트 접점 </li>
    <li> 앱 클라이언트(아이폰, 안드로이드, pc 앱)</li>
    <li> 웹 브라우저에서 자바스크립트를 통한 HTTP API 호출</li>
    <li> React, Vue.js 같은 웹 클라이언트</li>
<li> 서버 to 서버</li>
    <li> 주문 서버 -> 결제 서버</li>
    <li> 기업간 데이터 통신</li>

백엔드 프로그래머가 고민해야할 3가지

1. 정적리소스 제공
2. 동적 제공되는 html제공
3. http api 데이터 제공


<h3>SSR - 서버 사이드 렌더링</h3>
타임리프를 사용한 동적 html을 의미한다.

<h3>CSR - 클라이언트 사이드 렌더링</h3>
1. HTML 요청
2. 자바스크립트 요청

뷰 템플릿 역사

1. JSP
2. 프리마커, 벨로시티
3. 타임리프

타임리프는 태그를 사용하여 HTML을 조작하기 때문에 프론트엔드와 협업할때 좋다.

<h1>프로젝트 생성하기</h1>

1. http://start.spring.io

여기서 gradle파일로 spring.web 과 lombok을 추가하고 생성한다.

2. plugin에서 lombock을 설치한다.

3. 빌드 -> 컴파일러 -> 어노테이션 프로세서 -> 어노테이션 처리 활성화 ON

4. postman 사이트에서 운영체제에 맞는 프로그램 설치

Servlet을 이용한 parameter 받기

1. request.getParameter("name")

파라미터의 key값과 동일한 values를 가져온다.
2. request.getParameterNames()

파라미터의 모든 Key값을 가져온다.
3. request.getParameterValues("name")

파라미터 key값과 동일한 Value값을 가져온다.
4. response.Writer().write("hello")

클라이언트에게 "hello"라는 문자열을 반환한다.

<h1>Api Servlet</h1>

    ServletInputStream inputStream = request.getInputStream();
    //byte형식의 스트림으로 문자를 출력이 가능하다.
    
    streamUtils.copyToString(inputStream, StandraCharset.UTF_8);
    //byte스트림을 문자열로 디코딩해준다.

<br>
<h1>Json형식의 api를 받아 객체로 사용하기</h1>

        HelloData helloData = objectMapper.readValue(messageInput, HelloData.class);
        //제이슨형식의 key value값이 전달되면 hellodata form의 key와 변수명이 일치하는 것 끼리 연결하여 저장된다.
        //즉 helloData라는 객체로 사용할 수 있는 것이다.

hello-form

    @Setter @Getter
    public class HelloData {
    private String name;
    private int age;
    }

<h1>Response에 정보 담기</h1>

1. response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
2. response.setHeader("Cache-Control", "no_Cache, must_revalidate, no_store)
3. response.setStatus(HttpServletResponse.SC_OK);
4. response.addCookie(cookie);
5. response.sendRedirect("/basic/hello-form.html");


<h1>HTTP 응답 데이터 - 단순데이터, HTML</h1>

    PrintWriter writer = response.getWriter();
    writer.println("<html> <body><div>hello</div></body></html>);
writer에 태그를 담아 전달하면 tag형식으로 화면이 출력된다.


<h1>Json 형식의 데이터 전달</h1>

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setName("kim");
        helloData.setAge(20);

        //{"name":"kim", "age:20}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

request의 json형식이랑 거의 동일하다. objectMapper를 이용하여 객체를 스트링으로 변환하고 response에 담는것이다.



<h1>JSP방식을 사용하기 위한 환경설정</h1>

build.gradle에서 필요한 라이브러리를 추가한다.

	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	implementation 'javax.servlet:jstl'

<h1>V5 어뎁터를 이용한 프론트컨트롤러 변경</h1>

1. url신호로 어떤 컨트롤러를 불러와야하는지 확인한다.
2. 예를들어 V3의 컨트롤러로 인식을하였다면 V5에서 선언한 V3Adapter를 사용한다.
3. adapter.handle을 통해 논리파일이름, key, value를 ModelView에 담는다.
4. ModelView에 저장된 논리경로를 MyView에 설정하여 물리경로를 설정하고
5. MyView.render를 통해 해당 템플릿에 key, value를 전달한다.

<h1>V5 어뎁터를 이용한 프론트컨트롤러 V4추가하기</h1>

V3방식과 매우 유사하지만 V4방식은 렌더링할때 String문자열을 받아 렌더링을 했었다.

하지만 ModelView방식으로 동일해야하기 때문에 ModelView에 SetName(파일 논리 이름), setModel(key:value)를 추가하여 전달한다.

