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
public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value= "/", method = RequestMethod.GET)
	public ModelAndView mainGet(ModelAndView mv) throws Exception{
	    mv.setViewName("/main/home");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    return mv;
	}
	@RequestMapping(value= "/", method = RequestMethod.POST)
	public String mainPost(MemberVO loginInfo, Model model) throws Exception{
		MemberVO user = memberService.signin(loginInfo);
		System.out.println(user);
		model.addAttribute("user", user);
	    return "redirect:/";
	}
	@RequestMapping(value= "/signout", method = RequestMethod.GET)
	public String signoutGet(HttpServletRequest r) throws Exception{
		r.getSession().removeAttribute("user");
	    return "redirect:/";
	}
}
