package com.exam.controller.filenotice;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.AttachDao;
import com.exam.dao.JdbcUtils;
import com.exam.dao.NoticeDao;
import com.exam.vo.AttachVo;
import com.exam.vo.NoticeVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileWriteProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileWriteProController......");
		
		// 파일 업로드 위해서 cos.jar 라이브러리를 프로젝트 빌드패스에 추가.

		//업로드 객체 생성할때 필요한 인자값
		//1. request
		//2. 업로드 할 폴더의 물리적 경로
		//3. 업로드 최대 크기 제한
		//4. 파일명 한글처리 utf-8
		//5. 파일명 중복될때 이름변경규칙 가진 객체를 전달

		//업로드 할 실제 물리적 경로 구하기
		ServletContext application = request.getServletContext(); // application 객체 참조 구하기
		String realPath = application.getRealPath("/upload1");
		System.out.println("realPath : " + realPath);

		// 오늘날짜 년월일 폴더가 존재하는지 확인해서 없으면 생성하기
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(date); // "2020/11/11"

		File dir = new File(realPath, strDate);
		System.out.println("dir : " + dir.getPath());

		if (!dir.exists()) {
			dir.mkdirs();
		}


		//파일 업로드 하기
		MultipartRequest multi = new MultipartRequest(
				request,
				dir.getPath(),
				1024 * 1024 * 100, // 최대 업로드 100MB로 제한
				"utf-8",
				new DefaultFileRenamePolicy());

		//enctype="multipart/form-data" 로 전송받으면
		//기본내장객체인 request에서 파라미터값을 바로 찾을수 없음!
		//MultipartRequest 객체로부터 파라미터값을 찾아야 함! 사용방법은 동일함.

		//post 파라미터값 한글처리는 위에 생성자에서 처리하기 때문에 할필요 없음!
		//request.setCharacterEncoding("utf-8");

		//pageNum 파라미터값 가져오기
		String pageNum = multi.getParameter("pageNum");


		//DAO 객체 준비
		NoticeDao noticeDao = NoticeDao.getInstance();
		AttachDao attachDao = AttachDao.getInstance();

		int nextNum = JdbcUtils.getNextNum("bnotice");

		//Enumeration은 반복자 객체. file의 name속성들을 가지고 있음
		Enumeration<String> enu = multi.getFileNames();

		while (enu.hasMoreElements()) {
			// AttachVo 객체 준비
			AttachVo attachVo = new AttachVo();
			
			String fname = enu.nextElement();
			// 실제 업로드된 파일명 가져오기
			String filename = multi.getFilesystemName(fname);
			System.out.println("실제파일명 : " + filename);
			
			attachVo.setFilename(filename); // 실제파일명을 VO에 저장
			attachVo.setUploadpath(strDate); // "년/월/일" 경로를 저장
			attachVo.setNoNum(nextNum);  // insert될 게시판 글번호를 저장
			attachVo.setImage( isImage(filename) ? "I" : "O" );
			
			// attachVo를 attach 테이블에 insert하기
			attachDao.insertAttach(attachVo);
		} // while




		//VO 객체 준비
		NoticeVo noticeVo = new NoticeVo();

		//파라미터값 가져와서 VO에 저장. MultipartRequest 로부터 찾음.
		noticeVo.setId(multi.getParameter("id"));
		noticeVo.setSubject(multi.getParameter("subject"));
		noticeVo.setContent(multi.getParameter("content"));

		//글번호 가져와서 VO에 저장
		noticeVo.setNum(nextNum);

		//ip  regDate  readcount  값 저장
		noticeVo.setIp(request.getRemoteAddr());
		noticeVo.setRegDate(new Timestamp(System.currentTimeMillis()));
		noticeVo.setReadcount(0);  // 조회수

		//re_ref  re_lev  re_seq
		noticeVo.setReRef(nextNum); // 주글일때는 글번호가 그룹번호가 됨
		noticeVo.setReLev(0); // 주글일때는 들여쓰기 레벨이 0 (들여쓰기 없음)
		noticeVo.setReSeq(0); // 주글일때는 글그룹 내에서 순번이 0 (첫번째)


		//주글 noticeVo 등록하기
		noticeDao.addNotice(noticeVo);


		//글내용 상세보기 화면 fileContent.jsp로 이동
		//response.sendRedirect("fileContent.jsp?num=" + noticeVo.getNum() + "&pageNum=" + pageNum);
		return "redirect:/fileboard.do";
	}
	
	
	
	
	private boolean isImage(String filename) {
		boolean result = false;
		// 파일 확장자 문자열 추출하기
		// aaaa.bbb.ccc.ddd
		int index = filename.lastIndexOf(".");
		String ext = filename.substring(index + 1); // 파일 확장자 문자열
		
		if (ext.equalsIgnoreCase("jpg") 
				|| ext.equalsIgnoreCase("jpeg")
				|| ext.equalsIgnoreCase("gif")
				|| ext.equalsIgnoreCase("png")) {
			result = true;
		}
		return result;
	}

}
