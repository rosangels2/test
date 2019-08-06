package kr.green.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(	//URI에서 컨트롤러로 정보를 전송할 때 작동하는 interceptor
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();	//현재 요청된 페이지의 세션 객체를 가져와서  새 세션 객체에 저장한다
		Object user = session.getAttribute("user");	//세션에서 변수 user를 가져와 객체 Object객체 user에 저장한다
		if(user == null) {	//로그인이 안돼있다면
			response.sendRedirect(request.getContextPath()+"/");	//response.sendRedirect를 통해 해당 URI의 경로로 이동한다
		}
		return true;	//조건식에 해당되지 않는다면 true를 반환해 그대로 전송한다
	}
}
