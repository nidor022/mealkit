package com.exam.controller.filenotice;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.AttachDao;
import com.exam.dao.NoticeDao;
import com.exam.vo.AttachVo;
import com.exam.vo.NoticeVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileModifyProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("FileModifyProController....");
			
			// 세션 가져오기
			HttpSession session = request.getSession();
			
			// 로그인 여부 확인
			String id = (String) session.getAttribute("id");
			if (id == null) {
				return "redirect:/fileboard.do";
			}
			
			//업로드 할 실제 물리적 경로 구하기
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload1");
			System.out.println("realPath : " + realPath);
			

			//오늘날짜 년월일 폴더가 존재하는지 확인해서 없으면 생성하기
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String strDate = sdf.format(date); // "2020/11/11"

			File dir = new File(realPath, strDate);
			System.out.println("dir : " + dir.getPath());

			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			//파일 업로드 하기. (신규 첨부파일을 업로드)
			MultipartRequest multi = new MultipartRequest(
					request,
					dir.getPath(),
					1024 * 1024 * 100, // 최대 업로드 100MB로 제한
					"utf-8",
					new DefaultFileRenamePolicy());
			
			// DAO 준비
			NoticeDao noticeDao = NoticeDao.getInstance();
			AttachDao attachDao = AttachDao.getInstance();
			
			// 게시판 글번호
			int noNum = Integer.parseInt(multi.getParameter("num"));
			
			// 신규 첨부파일 정보를 테이블에 insert하기
			Enumeration<String> enu = multi.getFileNames(); // 파일선택상자의 name속성들 가져오기
			while (enu.hasMoreElements()) {
				String fname = enu.nextElement();
				String filename = multi.getFilesystemName(fname);
				
				if (filename == null) {
					continue;
				}
				
				// insert를 위한 VO 객체 준비
				AttachVo attachVo = new AttachVo();
				attachVo.setFilename(filename);
				attachVo.setUploadpath(strDate);
				attachVo.setImage( isImage(filename) ? "I" : "O" );
				attachVo.setNoNum(noNum);
				
				// 첨부파일정보 insert하기
				attachDao.insertAttach(attachVo);
			} // while
			
			// 삭제해야 할 첨부파일들 번호 가져오기
			String[] delFileNums = multi.getParameterValues("delfile");
			if(delFileNums != null) {
			for (String delFileNum : delFileNums) {
				// 삭제할 첨부파일 번호를 숫자로 변환
				int num = Integer.parseInt(delFileNum);
				// 첨부파일 번호에 해당하는 첨부파일 정보 한개를 VO로 가져오기
				AttachVo attachVo = attachDao.getAttachByNum(num);
				
				// 파일정보로 실제파일 존재여부 확인해서 삭제하기
				File delFile = new File(realPath + "/" + attachVo.getUploadpath(), attachVo.getFilename());
				if (delFile.exists()) {
					delFile.delete();
				}
				
				// 첨부파일 DB테이블에 첨부파일번호에 해당하는 레코드 한개 삭제하기
				attachDao.deleteAttachByNum(num);
			} // for
			}
			
			// NoticeVo 객체 준비
			NoticeVo noticeVo = new NoticeVo();

			// 파라미터값을 NoticeVo에 저장
			noticeVo.setNum(noNum);
			noticeVo.setId(multi.getParameter("id"));
			noticeVo.setSubject(multi.getParameter("subject"));
			noticeVo.setContent(multi.getParameter("content"));

			// 게시판 테이블 글 update하기
			noticeDao.updateBoard(noticeVo);

			// pageNum 파라미터값 가져오기
			String pageNum = multi.getParameter("pageNum");

			// 상세보기 화면으로 이동
			// response.sendRedirect("fileContent.jsp?num=" + noNum + "&pageNum=" + pageNum);
		String rtn = "redirect:/fileboard.do?num=\"+noNum+\"&pagenum=\"+pageNum";
		return "redirect:/fileboard.do?num=\"+noNum+\"&pagenum=\"+pageNum";
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
