package com.exam.vo;

public class CartDTO  {
	private String user_id;
	private String product_name;
	private int product_price;		
	private int product_count;		
	
	public CartDTO() {
	}
	
	

	public CartDTO(String user_id, String product_name, int product_price, int product_count) {
		this.user_id = user_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_count = product_count;
	}



	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}

	
	@Override
	public String toString() {
		return "CartDTO [user_id=" + user_id + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", product_count=" + product_count + "]";
	}
	
}
