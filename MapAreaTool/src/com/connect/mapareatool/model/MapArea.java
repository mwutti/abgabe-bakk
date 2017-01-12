package com.connect.mapareatool.model;

public class MapArea {
	private int id;
	private int number;
	private int x;
	private int y;
	
	public MapArea(int id , int number, int x, int y){
		this.id = id;
		this.number = number;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
