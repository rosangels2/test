package kr.green.test.service;

import org.springframework.stereotype.Service;

import kr.green.spring.pagination.Criteria;
import kr.green.spring.pagination.PageMaker;

@Service
public class PageMakerServiceImp implements PageMakerService{

	@Override
	public PageMaker getPageMaker(int displayPageNum, Criteria cri, int totalCount) {
		PageMaker pm = new PageMaker();
		pm.setDisplayPageNum(displayPageNum);
		pm.setCriteria(cri);
		pm.setTotalCount(totalCount);	//totalCount는 마지막에 계산해야 한다
		//pm.calcData(); calcData를 따로 호출할 경우 totalCount의 순서 상관 x
		return pm;
	}

}
