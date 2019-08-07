package kr.green.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@RequestMapping(value= "/signup", method = RequestMethod.GET)
	public ModelAndView signupGet(ModelAndView mv) throws Exception{
	    mv.setViewName("/member/signup");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    return mv;
	}
	@RequestMapping(value ="/dup")	//id 중복검사를 위한 메서드 매핑
	@ResponseBody
	public boolean idcheck(@RequestBody String id){
	    return memberService.getMember(id) != null;	//비교 연산자의 결과값은 boolean이기 떄문에 조건식의 결과를 즉시 리턴
	}
}
