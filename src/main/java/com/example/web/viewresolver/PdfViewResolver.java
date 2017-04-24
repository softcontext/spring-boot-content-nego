package com.example.web.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.example.web.view.PdfView;

public class PdfViewResolver implements ViewResolver {
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		System.out.println("PdfViewResolver > viewName = "+viewName);
		
		PdfView view = new PdfView();
		return view;
	}
}
