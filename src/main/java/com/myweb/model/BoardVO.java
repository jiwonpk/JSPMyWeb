package com.myweb.model;

import java.security.Timestamp;

public class BoardVO {
	private int bno;
	private String writer;
	private String tilte;
	private String content;
	private int hit;
	private Timestamp regdate;
	
	public BoardVO() {
		
	}
	

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", writer=" + writer + ", tilte=" + tilte + ", content=" + content + ", hit="
				+ hit + "]";
	}


	public BoardVO(int bno, String writer, String tilte, String content, int hit, Timestamp regdate) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.tilte = tilte;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getTilte() {
		return tilte;
	}


	public void setTilte(String tilte) {
		this.tilte = tilte;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	

	
}
