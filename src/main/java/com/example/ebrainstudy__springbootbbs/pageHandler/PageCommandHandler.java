package com.example.ebrainstudy__springbootbbs.pageHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageCommandHandler {
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException;
}
