package com.gordon.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gordon.exception.EmptyFile;
import com.gordon.service.GordonService;

@Controller
public class HomeController {

	@Autowired
	private GordonService gordonService;

	@RequestMapping("/")
	public String upload() {
		return "upload";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploadFile(Map model, @RequestParam MultipartFile file) throws IOException, EmptyFile

	{
		if (file.isEmpty())
			throw new EmptyFile();
		model.put("maxSatisfaction", gordonService.calculateMaxSatisfaction(file.getInputStream()));
		return "success";
	}
}
