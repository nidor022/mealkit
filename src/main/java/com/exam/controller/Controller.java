package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

public interface Controller {
	
	// 톰캣에서의 request, response받고 호출한 톰캣서버로 예외를 던질 예정
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
