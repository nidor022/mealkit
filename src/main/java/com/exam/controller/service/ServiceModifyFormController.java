package com.exam.controller.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;
import com.exam.vo.ServiceVo;

public class ServiceModifyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ServiceModifyFormController....");
		
				// 세션 가져오기
				HttpSession session = request.getSession();
				
				// 로그인 여부 확인
				String id = (String) session.getAttribute("id");
				if (id == null) {
					return "redirect:/serviceBoard.do";
				}
				
				// 파라미터값  pageNum  가져오기
				int num = Integer.parseInt(request.getParameter("num"));
				String pageNum = request.getParameter("pageNum");
				
				// DAO 준비
				ServiceDao serviceDao = ServiceDao.getInstance();
				ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();

				// 글번호 num에 해당하는 글내용 VO로 가져오기
				ServiceVo serviceVo = serviceDao.getServiceByNum(num);
				// 글번호 num에 해당하는 첨부파일정보를 리스트로 가져오기
				List<ServiceAttachVo> serviceAttachList = serviceAttachDao.getServiceAttachesByNoNum(num);
				
				
				
				// 뷰(jsp)에서 필요한 정보를 request 영역객체에 저장
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("serviceAttachList", serviceAttachList);
				request.setAttribute("serviceVo", serviceVo);
		
		
		
				return "service/serviceModifyForm";
	}

}
