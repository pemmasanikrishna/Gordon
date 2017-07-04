package com.gordon.exception;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = Logger.getLogger(this.getClass());

	@ExceptionHandler(EmptyFile.class)
	public String emptyFile(Model model) {
		model.addAttribute("message", "Please upload valid file");
		return "upload";
	}

	@ExceptionHandler(Exception.class)
	public String globalException(Exception exception) {
		Long incidentId = System.currentTimeMillis();
		logger.info("Exception has occured with id " + incidentId);
		logger.info(exception.getStackTrace());
		return "Internal Server Error Has occured." + "Please Contact admin with incident id " + incidentId;
	}
}
