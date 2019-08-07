package kr.green.test.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.spring.pagination.Criteria;
import kr.green.spring.pagination.PageMaker;
import kr.green.test.service.BoardService;
import kr.green.test.service.PageMakerService;
import kr.green.test.utils.UploadFileUtils;
import kr.green.test.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	@Autowired
	PageMakerService pageMakerService;
	@Resource
	private String uploadPath;
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv, Criteria cri){
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
	public ModelAndView boardRegisterGet(ModelAndView mv){
		mv.setViewName("/board/register");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    return mv;
	}
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String boardRegisterPost(Model model, BoardVO bVo, MultipartFile file2) throws IOException, Exception{
		if(file2.getOriginalFilename().length() != 0) {
			String file = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(),file2.getBytes());
			bVo.setFile(file);
		}
		boardService.registerBoard(bVo);
	    return "redirect:/board/list";
	}
	@RequestMapping(value= "/display", method = RequestMethod.GET)
	public ModelAndView boardDisplayGet(ModelAndView mv, Integer num, Criteria cri){
		BoardVO board = boardService.getBoard(num);
		board = boardService.increaseViews(board);
		mv.setViewName("/board/display");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
		mv.addObject("board", board);
		mv.addObject("cri", cri);
	    return mv;
	}
	@RequestMapping(value= "/modify", method = RequestMethod.GET)
	public ModelAndView boardModifyGet(ModelAndView mv, Integer num, Criteria cri, HttpServletRequest r){
		boolean isWriter = boardService.isWriter(num, r);
		BoardVO board = null;
		if(isWriter){
			board = boardService.getBoard(num);
			mv.setViewName("/board/modify");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
		}else {
			mv.setViewName("redirect:/board/list");
		}
		mv.addObject("board", board);
		mv.addObject("cri", cri);
	    return mv;
	}
	@RequestMapping(value= "/modify", method = RequestMethod.POST)
	public String boardModifyPost(BoardVO bVo, MultipartFile file2) throws IOException, Exception{
		if(file2.getOriginalFilename().length() != 0) {
			String file = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(),file2.getBytes());
			bVo.setFile(file);
		}
		boardService.modifyBoard(bVo);
	    return "redirect:/board/list";
	}
	@RequestMapping(value= "/delete", method = RequestMethod.GET)
	public ModelAndView boardDeleteGet(ModelAndView mv, Integer num, HttpServletRequest r){
		if(boardService.isWriter(num, r)){
			boardService.deleteBoard(num);
		}
		mv.setViewName("redirect:/board/list");	//setViewName :  return "/main/home" 와 같이 /main/home.jsp를 호출
	    return mv;
	}
	@ResponseBody
	@RequestMapping("download")
	public ResponseEntity<byte[]> downloadFile(String fileName)throws Exception{
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    try{
	        HttpHeaders headers = new HttpHeaders();
	        in = new FileInputStream(uploadPath+fileName);

	        fileName = fileName.substring(fileName.indexOf("_")+1);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.add("Content-Disposition",  "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
	    }catch(Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	    }finally {
	        in.close();
	    }
	    return entity;
	}
}
