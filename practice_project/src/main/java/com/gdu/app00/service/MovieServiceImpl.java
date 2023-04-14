package com.gdu.app00.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app00.domain.Movie;

public class MovieServiceImpl implements IMovieService {

	@Override
	public Movie execute(HttpServletRequest request, HttpServletResponse response) {

		String location = request.getParameter("location");
		String time = request.getParameter("time");
		
		return new Movie(location, time);
	}

}
