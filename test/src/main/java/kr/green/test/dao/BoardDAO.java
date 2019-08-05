package kr.green.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.spring.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardDAO {

	public ArrayList<BoardVO> selectBoardList(@Param("cri")Criteria cri, @Param("valid")String valid);	//매개변수가 2개 이상일 경우 param을 사용

	public int selectCountBoardList(@Param("cri")Criteria cri, @Param("valid")String valid);

}
