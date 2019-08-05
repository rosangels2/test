package kr.green.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.test.service.MemberService;
import kr.green.test.vo.MemberVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv) throws Exception{
	    mv.setViewName("/board/list");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    return mv;
	}

}
