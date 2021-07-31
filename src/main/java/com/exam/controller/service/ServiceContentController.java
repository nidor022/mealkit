package com.exam.controller.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;
import com.exam.vo.ServiceVo;

public class ServiceContentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ServiceContentController......");
		
		// 파라미터값  num  pageNum  가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// DAO 객체 준비
		ServiceDao serviceDao = ServiceDao.getInstance();
		ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();

		// 조회수 1 증가
		serviceDao.updateReadcount(num);

		// 글 한개 가져오기
		ServiceVo serviceVo = serviceDao.getServiceByNum(num);
		// 첨부파일 리스트 정보 가져오기
		List<ServiceAttachVo> serviceAttachList = serviceAttachDao.getServiceAttachesByNoNum(num);


		// 글 내용에서 "\n" 줄바꿈 문자열을 "<br>"로 교체하기
		String content = "";
		if (serviceVo.getContent() != null) {
			content = serviceVo.getContent().replace("\n", "<br>");
			serviceVo.setContent(content);
		}
		
		// 뷰(jsp)에서 필요한 데이터를 request 영역객체에 저장
		request.setAttribute("serviceVo", serviceVo);
		request.setAttribute("serviceAttachList", serviceAttachList);
		request.setAttribute("pageNum", pageNum);
		
		return "service/serviceContent";
	}

}
