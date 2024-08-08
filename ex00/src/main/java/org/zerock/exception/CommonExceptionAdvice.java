package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	// 에러가 발생됬을때 'error_page'를 띄워줌
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.error("Exception..............." + ex.getMessage());
		model.addAttribute("exception", ex);
		return "error_page";
	}
	
	//404error가 발생할시 'custom404'를 띄워줌
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "custom404";
	}
}
