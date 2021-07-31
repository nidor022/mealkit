package com.exam.vo;

// VO(Value Object) : 데이터베이스 테이블과 연관된 값객체
// DTO(Data Tranfer Object) : 데이터베이스 테이블과 무관한 데이터 전송용도의 값객체
public class PageDtoService {
	private String category; // 검색유형
	private String search; 	 // 검색어
	private int count;	   	 // 전체글갯수
	private int pageCount;	 // 총 필요한 페이지(마지막 페이지번호)
	private int pageBlock; 	 // 페이지블록 당 갯수
	private int startPage;	 // 페이지블록 내 시작페이지 번호
	private int endPage; 	 // 페이지블록 내 끝페이지 번호
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "PageDto [category=" + category + ", search=" + search + ", count=" + count + ", pageCount=" + pageCount
				+ ", pageBlock=" + pageBlock + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}
