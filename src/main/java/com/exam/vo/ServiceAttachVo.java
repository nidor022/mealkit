package com.exam.vo;

public class ServiceAttachVo {
	
	private int num;
	private String filename;   // 실제 업로드된 파일명
	private String uploadpath; // 실제 업로드된 폴더 경로
	private String image;      // 파일이 이미지인지 여부. "I"는 이미지, "O"는 이미지 아님
	private int noNum;    // notice 테이블의 글번호 num
	
	public ServiceAttachVo() {
	}

	public int getNum() {
		return num;
	}

	public String getFilename() {
		return filename;
	}

	public String getUploadpath() {
		return uploadpath;
	}

	public String getImage() {
		return image;
	}

	public int getNoNum() {
		return noNum;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setNoNum(int noNum) {
		this.noNum = noNum;
	}

	@Override
	public String toString() {
		return "AttachVo [num=" + num + ", filename=" + filename + ", uploadpath=" + uploadpath + ", image=" + image
				+ ", noNum=" + noNum + "]";
	}
	
}
