<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.exam.vo.CommentVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.CommentDao"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//VO DAO 준비
	CommentDao commentDao = CommentDao.getInstance();

	//글번호 가져오기
	int b_num = Integer.parseInt(request.getParameter("b_num"));
	System.out.println("b_num:"+b_num);

	//데이터 가져오기, json-simple 활용
	List<CommentVo> commentList = commentDao.getCommentsByNum(b_num);
	
	// Gson 객체 준비
	Gson gson = new Gson();
	
	// commentList 객체를 JSON 문자열로 변환하기
	String strJson = gson.toJson(commentList);
	
	// 웹서버 콘솔에출력
	System.out.println(strJson);
	
%>
<%=strJson %>