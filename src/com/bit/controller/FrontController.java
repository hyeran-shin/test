package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 요청을 받아 해당 요청에 맞는 Controller를 Mapping하는 역할
// -> HandlerMapping 포함
// 컨트롤러는 요청을 받는 서블릿이다.
public class FrontController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8647620004495475326L; // 해당 객체 클래스의 id 부여
	// 프로그램 내에서는 상관없음. jvm이 알아서 찾아쥼,
	// 외부에서 연결했을 때 알 수 있게 해야함. 객체가 왔다갔다해서 관리하기위한 id

	private HandlerMapping mappings = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// config : 서블릿 등록(초기화) 시 전달되는 파라미터 정보
		String configName = config.getInitParameter("configName"); // web.xml의 configName으로 init값 설정해둠
		// Mapping을 위한 설정 경로 전달! (bean.properties)
		mappings = new HandlerMapping(configName);

	}

	// 요청을 하면 service로 온다
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// 요청 URI에 대한 가공(mappings의 key값에 맞게)
			String uri = request.getRequestURI(); // "/jblog/loginForm.do"
			String context = request.getContextPath(); // "jbolg"
			uri = uri.substring(context.length()); // "/loginForm.do"

			// URI를 전달하여 실제 Controller 객체 반환
			Controller controller = mappings.getController(uri);

			// 해당 Controller를 통해, 실제 파일(views)의 경로를 반환 받겠다.
			String callPage = controller.handleRequest(request, response);

			// 실제(서버에 존재하는) views(JSP)의 경로를 알았으니,
			// forward로 이동시키겠다. (Dispatcher)

			RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(); 
			throw new ServletException(e); //문제 있을 시 강제로 터트림, 우리가 보기 위한 것
		}

	}

}
