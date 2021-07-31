package com.exam.vo;

import java.sql.Timestamp;

public class CommentVo {
	
	private int c_num; // ��� ��ȣ(auto_increment)
	private String c_id; // �۾���
	private String c_content; // ������
	private Timestamp c_date; // ������
	private int b_num; // ���Խñ۹�ȣ
	
	
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
	