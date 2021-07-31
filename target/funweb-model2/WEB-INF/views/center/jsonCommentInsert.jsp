<%@page import="java.sql.Timestamp"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.exam.vo.CommentVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.CommentDao"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 로그인 여부 확인
	String id = (String) session.getAttribute("id");
	if (id == null) {
		response.sendRedirect("/fileboard.do");
	}
	
	//VO DAO 준비
	CommentDao commentDao = CommentDao.getInstance();
	CommentVo commentVo = new CommentVo();
	
	//글번호 가져오기
	int b_num = Integer.parseInt(request.getParameter("b_num"));
	String c_content = request.getParameter("c_content");
	System.out.println("b_num:"+b_num);
	System.out.println("c_content:"+c_content);

	commentVo.setC_id(id);
	commentVo.setC_content(c_content);
	commentVo.setC_date(new Timestamp(System.currentTimeMillis()));
	commentVo.setB_num(b_num);
	
	commentDao.insertComment(commentVo);
	
	response.sendRedirect("/fileContent.do?"+ b_num);
%>

