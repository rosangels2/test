package kr.green.test.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	public static String uploadFile(String uploadPath, String originalName, byte[] 	//파일을 업로드하는 메소드
			fileData)throws Exception{
		UUID uid = UUID.randomUUID();	//고유 식별자 UUID를 통해 같은 파일명이어도 다르게 저장
		String savedName = uid.toString() +"_" + originalName;	//고유식별명_파일명의 형식으로 파일명을 저장
		String savedPath = calcPath(uploadPath);	//업로드 날짜를 기준으로 파일 경로를 추가
		File target = new File(uploadPath + savedPath, savedName);	//기본 폴더에 업로드 날짜를 경로로 추가한 뒤 이름을 저장
		FileCopyUtils.copy(fileData, target);	//해당 파일에 클라이언트가 보낸 파일 복사(서버에 업로드)
		String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		return uploadFileName;
	}
	
	private static String calcPath(String uploadPath) {	//업로드한 파일을 저장할 경로를 계산하는 메소드(현재 시간을 기준으로 년월을 가져온다)
		Calendar cal = Calendar.getInstance();
		//separator - 운영체제에 맞게 \ 또는 / 를 설정해 주는 구분자
		String yearPath = File.separator+cal.get(Calendar.YEAR);	// /2019	
		String monthPath = yearPath + File.separator 				// /2019/07
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator 				// /2019/07/29
            + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);		//해당 경로를 매개변수로 폴더를 생성하는 메서드 호출
		
		return datePath;
 
	}
	private static void makeDir(String uploadPath, String... paths) {	//String... : 3개의 Path들을 paths라는 배열로 만들어서 사용
		if(new File(uploadPath+paths[paths.length-1]).exists())	//datePath가 만들어져 있다면 폴더를 생성하지 않고 리턴 
			return;
		for(String path : paths) {	//향상된 포문으로 yearPath부터 하나씩 꺼내서 실행
			File dirPath = new File(uploadPath + path);
			if( !dirPath.exists())	//존재하지 않으면
				dirPath.mkdir();	//해당 폴더를 생성
		}
	}
	private static String makeIcon(String uploadPath, String path, String fileName)
        	throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;	//파일 경로를 가져와 저장
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');	//replace를 통해 구분자를 \에서 /로 변경
	}
	
}
