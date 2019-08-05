package kr.green.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value= "/")
	public ModelAndView openTilesView(ModelAndView mv) throws Exception{
	    mv.setViewName("/main/home");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    mv.addObject("setHeader", "타일즈");	//변수 setHeader에 값 타일즈를 저장
	    return mv;
	}
	
}
