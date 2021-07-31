package com.exam.controller.filenotice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.NoticeDao;
import com.exam.vo.NoticeVo;
import com.exam.vo.PageDto;

public class FileNoticeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileNoticeController......");
		
		// 검색어 관련 파라미터값 가져오기. 없으면 null 리턴
		String category = request.getParameter("category"); // 검색유형
		String search = request.getParameter("search"); // 검색어

		// 검색어 관련 파라미터값이 null이면 빈문자열("")로 대체
		category = (category == null) ? "" : category;
		search = (search == null) ? "" : search;

		System.out.println("category = " + category);
		System.out.println("search = " + search);

		// DAO 객체 준비
		NoticeDao noticeDao = NoticeDao.getInstance();

		// 전체 글갯수 가져오기
		//int count = noticeDao.getCountAll();
		int count = noticeDao.getCountBySearch(category, search); // 검색어 기준으로 글갯수 가져오기

		
		// ==================================================
		// 한 페이지에 해당하는 글목록 구하기 작업
		// ==================================================
		
		// 한페이지당 보여줄 글갯수 설정
		int pageSize = 10;

		// 사용자가 요청하는 페이지번호 파라미터값 가져오기
		String strPageNum = request.getParameter("pageNum");
		// 사용자 요청 페이지번호 정보가 없을때(null 일때)
		// 기본 요청 페이지번호를 1페이지로 설정하기
		strPageNum = (strPageNum == null) ? "1" : strPageNum;
		// 사용자 요청 페이지를 정수로 변환
		int pageNum = Integer.parseInt(strPageNum);

		// 가져올 첫행번호 구하기
		int startRow = (pageNum - 1) * pageSize;

		// 글목록 가져오기
		List<NoticeVo> noticeList = null;
		if (count > 0) {
			//noticeList = noticeDao.getNotices(startRow, pageSize);
			noticeList = noticeDao.getNoticesBySearch(startRow, pageSize, category, search);
		}
		
		
		
		
		// ==================================================
		// 페이지블록 관련정보 구하기 작업
		// ==================================================
		
		PageDto pageDto = new PageDto();
		
		// 글갯수가 0보다 크면 페이지블록 계산해서 출력하기
		if (count > 0) {
			// 총 필요한 페이지 갯수 구하기
			// 글50개. 한화면에보여줄글 10개 => 50/10 = 5 
			// 글55개. 한화면에보여줄글 10개 => 55/10 = 5 + 1페이지(나머지존재) => 6
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			//int pageCount = (int) Math.ceil((double) count / pageSize);
			
			// 한 화면에 보여줄 페이지갯수 설정
			int pageBlock = 5;
			
			// 화면에 보여줄 시작페이지번호 구하기
			// 1~5          6~10          11~15          16~20       ...
			// 1~5 => 1     6~10 => 6     11~15 => 11    16~20 => 16
			int startPage = ((pageNum / pageBlock) - (pageNum % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;
			
			// 화면에 보여줄 끝페이지번호 구하기
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			
			// 뷰에서 필요한 데이터를 PageDto에 저장
			pageDto.setCategory(category);
			pageDto.setSearch(search);
			pageDto.setCount(count);
			pageDto.setPageCount(pageCount);
			pageDto.setPageBlock(pageBlock);
			pageDto.setStartPage(startPage);
			pageDto.setEndPage(endPage);
		} // if
		
		
		// 뷰(jsp)에서 사용할 데이터를 request 영역객체에 저장
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageDto", pageDto);
		request.setAttribute("pageNum", pageNum);
		
		
		return "center/fileNotice";
	}

}
