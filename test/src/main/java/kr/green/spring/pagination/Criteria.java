package kr.green.spring.pagination;

public class Criteria {

	private int page;		//현재 페이지를 저장할 멤버변수
	private int perPageNum;	//한 페이지에 보여줄 개시글 개수를 정하는 멤버변수
	private int type;		//검색 카테고리 값을 저장할 멤버변수
	private String search;	//검색창에 입력된 값을 저장할 멤버변수
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Criteria() {	//기본 생성자
		this.page = 1;
		this.perPageNum = 10;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) {		//현재 페이지 번호를 0보다 작게 설정하려 할 때
			this.page = 1;
		}
		else
			this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 100) {	//한 페이지당 컨텐츠 개수를 100개로 제한
			this.perPageNum = 10;
		}
		else
			this.perPageNum = perPageNum;
	}

	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", type=" + type + ", search=" + search + "]";
	}

	
	public int getPageStart() {
		return (this.page -1) * perPageNum;	//getBoardList에서 limit을 통해 보여줄 개시글 수를 제한할 때 사용될 getter
	}	//쿼리문에선 멤버변수를 호출하는 게 아니라 멤버변수의 getter를 호출하기 때문에 생성하지 않은 멤버변수여도 getter를 생성해서 호출 가능(기존 멤버변수로 계산이 가능하기 때문)


}
