package com.exam.controller.filenotice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.AttachDao;
import com.exam.dao.NoticeDao;
import com.exam.vo.AttachVo;
import com.exam.vo.NoticeVo;

public class FileModifyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileModifyFormController....");
		
		// 세션 가져오기
				HttpSession session = request.getSession();
				
				// 로그인 여부 확인
				String id = (String) session.getAttribute("id");
				if (id == null) {
					return "redirect:/fileboard.do";
				}
				
				// 파라미터값  pageNum  가져오기
				int num = Integer.parseInt(request.getParameter("num"));
				String pageNum = request.getParameter("pageNum");
				
				// DAO 준비
				NoticeDao noticeDao = NoticeDao.getInstance();
				AttachDao attachDao = AttachDao.getInstance();

				// 글번호 num에 해당하는 글내용 VO로 가져오기
				NoticeVo noticeVo = noticeDao.getNoticeByNum(num);
				// 글번호 num에 해당하는 첨부파일정보를 리스트로 가져오기
				List<AttachVo> attachList = attachDao.getAttachesByNoNum(num);
				
				
				
				// 뷰(jsp)에서 필요한 정보를 request 영역객체에 저장
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("attachList", attachList);
				request.setAttribute("noticeVo", noticeVo);
		
		
		
				return "center/fileModifyForm";
	}

}
