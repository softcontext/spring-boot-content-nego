package com.example.web.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.example.web.view.CsvView;

public class CsvViewResolver implements ViewResolver {
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		System.out.println("CsvViewResolver > viewName = "+viewName);
		CsvView view = new CsvView();
		return view;
	}
}
