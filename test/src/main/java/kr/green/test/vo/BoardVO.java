package kr.green.test.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {

	private int num;
	private String title;
	private String contents;
	private String writer;
	private Date registered;
	private String file;
	private int views;
	private String valid;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegistered() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");	//jsp에서 호출할 때 원하는 형식으로 보기 위해 코드를 수정
		return f.format(registered);										//변수 registered를 위의 형식으로 변환하여 반환
	}
	public String getRegisteredUntilDay() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");			//jsp에서 호출할 때 원하는 형식으로 보기 위해 코드를 수정
		return f.format(registered);										//변수 registered를 위의 형식으로 변환하여 반환
	}
	public void setRegistered(String registered) {		//jsp에서 form태그를 통해 컨트롤러로 넘어갈 떄 setter를 호출하여 전송
		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		try {
			this.registered = f.parse(registered);
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	public String getFile() {
		return file;
	}
	public String getFileName(){
		if(file == null){
			return "";
		}
		int index = file.indexOf("_");	//UUID 뒤에 오는 첫번째 _의 위치를 찾아서 저장
		if(index == -1){	//파일이 없을 때의 예외처리
			return "";
		}
		return file.substring(index+1);	//언더바 다음 위치의 문자열을 서브스트링으로 변환하여 반환
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", contents=" + contents + ", writer=" + writer
				+ ", registered=" + registered + ", file=" + file + ", views=" + views + ", valid=" + valid + "]";
	}
	
	
}
