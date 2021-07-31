package com.exam.vo;

public class OrderVo {
	private String order_date;
	private String name;
	private String id;
	private String pass;
	private String address;
	private String product;
	private int p_count;
	private int price;
	
	
	public String getOrder_date() {
		return order_date;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getPass() {
		return pass;
	}
	public String getAddress() {
		return address;
	}
	public String getProduct() {
		return product;
	}
	public int getP_count() {
		return p_count;
	}
	public int getPrice() {
		return price;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public void setP_count(int p_count) {
		this.p_count = p_count;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "OrderVo [order_date=" + order_date + ", name=" + name + ", id=" + id + ", pass=" + pass + ", address="
				+ address + ", product=" + product + ", p_count=" + p_count + ", price=" + price + "]";
	}
	
}
