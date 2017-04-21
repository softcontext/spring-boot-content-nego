package com.example.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.example.web.viewresolver.ExcelViewResolver;
import com.example.web.viewresolver.JsonViewResolver;
import com.example.web.viewresolver.PdfViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true)
			.defaultContentType(MediaType.TEXT_HTML)
			.favorPathExtension(true);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<>();
		
		resolvers.add(excelViewResolver());
		resolvers.add(pdfViewResolver());
		resolvers.add(jsonViewResolver());
        resolvers.add(jspViewResolver());

		/*
		 * ContentNegotiatingViewResolver is an implementation of ViewResolver, 
		 * which uses the requested media type 
		 * (based on filetype extension, URL parameter specifying type of output format or accept header) 
		 * to select a suitable View for a request. 
		 * 
		 * ContentNegotiatingViewResolver does not resolve view by itself 
		 * but delegates to other ViewResolver 
		 * you can configure to handle specific views(XML,JSON,PDF,XLS,HTML,..).
		 */
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}
	
	@Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
	
	@Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
