package com.example.schedule;

public class Event {
	private int id;
	private boolean check;
	private String Things;
	private String data;

	public Event(int id,boolean check, String Things) {
		this.id=id;
		this.check = check;
		this.Things = Things;
	}

	public Event(int id,boolean check, String Things, String data) {
		this.id=id;
		this.check = check;
		this.data = data;
		this.Things = Things;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getThings() {
		return Things;
	}

	public void setThings(String things) {
		Things = things;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
