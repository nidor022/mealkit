package com.exam.controller.notice;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.NoticeDao;
import com.exam.vo.NoticeVo;
import com.exam.vo.PageDto;

public class WriteFormProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteProController......");
		
		
		
		return "redirect:/notice.do"; 
	}

}
