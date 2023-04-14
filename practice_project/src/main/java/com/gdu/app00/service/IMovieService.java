package com.gdu.app00.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app00.domain.Movie;

public interface IMovieService {
	public Movie execute(HttpServletRequest request, HttpServletResponse response);
}
