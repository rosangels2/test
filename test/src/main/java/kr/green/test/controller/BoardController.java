package kr.green.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.spring.pagination.Criteria;
import kr.green.spring.pagination.PageMaker;
import kr.green.test.service.BoardService;
import kr.green.test.service.PageMakerService;
import kr.green.test.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	@Autowired
	PageMakerService pageMakerService;
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv, Criteria cri) throws Exception{
		String valid = "I";
		int displayPageNum = 5;
		ArrayList<BoardVO> list = boardService.getBoardList(cri, valid);
		int totalCount = boardService.getTotalCount(cri, valid);
		PageMaker pm = pageMakerService.getPageMaker(displayPageNum, cri, totalCount);
		System.out.println("board/list pm : " + pm);
	    mv.setViewName("/board/list");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    mv.addObject("list", list);
	    mv.addObject("pageMaker", pm);
	    return mv;
	}
	@RequestMapping(value= "/register", method = RequestMethod.GET)
	public ModelAndView boardRegisterGet(ModelAndView mv) throws Exception{
		mv.setViewName("/board/register");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    return mv;
	}
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String boardRegisterPost(Model model, BoardVO bVo) throws Exception{
		boardService.registerBoard(bVo);
	    return "redirect:/board/list";
	}
}
