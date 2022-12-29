package com.example.ebrainstudy__springbootbbs.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageHandlerInterface {
	public void process(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
