package com.exam.vo;

import java.sql.Timestamp;

public class CommentVo {
	
	private int c_num; // 댓글 번호(auto_increment)
	private String c_id; // 글쓴놈
	private String c_content; // 쓴내용
	private Timestamp c_date; // 쓴날자
	private int b_num; // 원게시글번호
	
	
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Timestamp getC_date() {
		return c_date;
	}
	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	
	@Override
	public String toString() {
		return "CommentVo [c_num=" + c_num + ", c_id=" + c_id + ", c_content=" + c_content + ", c_date=" + c_date
				+ ", b_num=" + b_num + "]";
	}
	
}
	