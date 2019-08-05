package kr.green.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.test.service.BoardService;
import kr.green.test.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv) throws Exception{
		ArrayList<BoardVO> list = boardService.getBoardList();
		System.out.println("board/list list : " + list);
	    mv.setViewName("/board/list");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    mv.addObject("list", list);
	    return mv;
	}

}
