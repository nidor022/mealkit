<%@page import="java.io.File"%>
<%@page import="com.exam.vo.AttachVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.AttachDao"%>
<%@page import="com.exam.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// num  pageNum  파라미터값 가져오기
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

// DAO 객체 준비
NoticeDao noticeDao = NoticeDao.getInstance();
AttachDao attachDao = AttachDao.getInstance();

// 게시글번호에 첨부된 첨부파일 리스트 가져오기
List<AttachVo> attachList = attachDao.getAttachesByNoNum(num);

String realPath = application.getRealPath("/upload"); // 업로드 기준경로

// 첨부파일 삭제하기
for (AttachVo attachVo : attachList) {
	// 삭제할 파일을 File 타입 객체로 준비
	File file = new File(realPath + "/" + attachVo.getUploadpath(), attachVo.getFilename());
	
	if (file.exists()) { // 해당 경로에 파일이 존재하면
		file.delete();   // 파일 삭제하기
	}
} // for

// attach 첨부파일내용 삭제하기
attachDao.deleteAttachesByNoNum(num);

// notice 게시글 삭제하기
noticeDao.deleteNoticeByNum(num);

// 글목록 fileNotice.jsp 로 이동
response.sendRedirect("fileNotice.jsp?pageNum=" + pageNum);
%>


