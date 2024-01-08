package com.myweb.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.model.BoardDAO;

public class BoardServiceImpl implements BoardService{
	
	//
	private BoardDAO dao =BoardDAO.getInstance();

	@Override
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		dao.insert(writer, title, content);
		
		
	}

}
