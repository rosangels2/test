package kr.green.spring.pagination;

public class PageMaker {

	private int totalCount;	//게시글의 전체 개수(마지막 페이지에 다음 버튼을 비활성화하기 위해 필요)
	private int startPage;	//페이지네이션의 시작 번호
	private int endPage;	//페이지네이션의 끝 번호
	private boolean prev;	//이전 버튼 활성화 여부
	private boolean next;	//다음 버튼 활성화 여부
	private int displayPageNum;	//페이지네이션의 개수
	private Criteria criteria;	//현재 페이지 정보
	
	
	public void calcData() {
		//startPage와 endPage는 현재 페이지 정보인 criteria와 displayPageNum을 이용하여 계산
		endPage = (int) (Math.ceil(criteria.getPage()/(double) displayPageNum)*displayPageNum);
		//displayPageNum이 10이고 현재 페이지가 3페이지면 startPage = 1, endPage = 10이 되도록 계산
		//ceil - 올림 함수
		
		startPage = (endPage - displayPageNum)+1;	//마지막 페이지 기준으로 계산
		//startPage = 31 , perPageNum = 15, totalCount = 563
		
		int tempEndPage = (int)(Math.ceil(totalCount/(double)criteria.getPerPageNum()));	//총 게시글의 마지막 페이지 계산
		
		if(endPage > tempEndPage){	//게시글의 마지막 페이지와 총 개시글 기준 마지막 페이지를 비교	
			endPage = tempEndPage;	//총 게시글 기준 마지막 페이지가 작을 경우 게시글의 마지막 페이지를 총 게시글 기준으로 변경 
		}

		prev = startPage == 1 ? false : true;	//스타트페이지가 1이면 false로 만들고 1이 아니면 true로 만든다
		
		next = endPage * criteria.getPerPageNum() >= totalCount ? false:true; 
		//마지막 페이지의 게시글 수가 총 게시글 수보다 크면 next를 숨긴다(게시글이 더 남아있기 때문)
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", criteria=" + criteria + "]";
	}
	
	
}
