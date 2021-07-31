package com.exam.controller.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.JdbcUtils;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;
import com.exam.vo.ServiceVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ServiceWriteProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ServiceWriteProController......");
		
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

		//pageNum 파라미터값 가져오기
		String pageNum = multi.getParameter("pageNum");

		// DAO 준비
		ServiceDao serviceDao = ServiceDao.getInstance();
		ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();
		
		//VO 객체 준비
		ServiceVo serviceVo = new ServiceVo();
		ServiceAttachVo serviceAttachVo = new ServiceAttachVo();
		
		//insert될 글번호 가져오기
		int nextNum = JdbcUtils.getNextNum("service");
		serviceVo.setNum(nextNum);
		System.out.println(nextNum);

		//Enumeration은 반복자 객체. file의 name속성들을 가지고 있음
		Enumeration<String> enu = multi.getFileNames();

		while (enu.hasMoreElements()) {
			String fname = enu.nextElement();
			// 실제 업로드된 파일명 가져오기
			String filename = multi.getFilesystemName(fname);
			System.out.println("실제파일명 : " + filename);
			
			serviceAttachVo.setFilename(filename); // 실제파일명을 VO에 저장
			serviceAttachVo.setUploadpath(strDate); // "년/월/일" 경로를 저장
			serviceAttachVo.setNoNum(nextNum);  // insert될 게시판 글번호를 저장
			if(filename != null) {
				serviceAttachVo.setImage( isImage(filename) ? "I" : "O" );
			}
			// attachVo를 attach 테이블에 insert하기
			serviceAttachDao.insertServiceAttach(serviceAttachVo);
		} // while

		//파라미터값 가져와서 VO에 저장. MultipartRequest 로부터 찾음.
		serviceVo.setId(multi.getParameter("id"));
		serviceVo.setSubject(multi.getParameter("subject"));
		serviceVo.setContent(multi.getParameter("content"));

		//ip  regDate  readcount  값 저장
		serviceVo.setIp(request.getRemoteAddr());
		serviceVo.setRegDate(new Timestamp(System.currentTimeMillis()));
		serviceVo.setReadcount(0);  // 조회수

//		//re_ref  re_lev  re_seq
		serviceVo.setReRef(nextNum); // 주글일때는 글번호가 그룹번호가 됨
		serviceVo.setReLev(0); // 주글일때는 들여쓰기 레벨이 0 (들여쓰기 없음)
		serviceVo.setReSeq(0); // 주글일때는 글그룹 내에서 순번이 0 (첫번째)

		// 답글 insert하기
		serviceDao.addService(serviceVo);

		//글내용 상세보기 화면 fileContent.jsp로 이동
		//response.sendRedirect("fileContent.jsp?num=" + noticeVo.getNum() + "&pageNum=" + pageNum);
		return "redirect:/serviceBoard.do";
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
