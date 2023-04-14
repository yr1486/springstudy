package com.gdu.app00.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app00.domain.Movie;
import com.gdu.app00.service.IMovieService;

@Controller
public class PosterController {
	
	@Autowired
	private IMovieService movieService;
	
	@GetMapping("/poster1.dokkk")
	public Movie poster(HttpServletRequest request, HttpServletResponse response) {
		return movieService.execute(request, response);
	}
	
}
