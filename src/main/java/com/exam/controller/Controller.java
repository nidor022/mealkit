package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

public interface Controller {
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
