package com.example.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.example.web.model.Users;
import com.example.web.viewresolver.CsvViewResolver;
import com.example.web.viewresolver.ExcelViewResolver;
import com.example.web.viewresolver.JsonViewResolver;
import com.example.web.viewresolver.PdfViewResolver;
import com.example.web.viewresolver.XmlViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/**
		 * 1) URL에 붙는 확장자 : favorPathExtension 프로퍼티 값을 보고 URL의 확장자에서 리턴 포맷을 결정할지를 판단. 디폴트 true
		 * 2) URL의 특정 파라미터에 설정되는 값 : favorParameter와 parameterName 프로퍼티의 값을 보고 URL의 파라미터 값으로 리턴포맷을 결정할지를 판단. 디폴트 false, "format". 사용예) a.do?format=xml
		 * 3) Request Header중 Accept 항목에 설정되어 있는 값 : ignoreAcceptHeader 프로퍼티의 값을 보고 HttpRequest Header의 Accept 항목에서 리턴포맷을 결정할지를 판단
		 * 4) 1),2),3)번을 모두 사용하지 않을 경우 defaultContentType 프로퍼티에서 정해진 값
		 * 모두 만족하지 않으면 ContentNegotiatingViewResolver는 자기가 View를 만들것이 아니라 판단하고 다음 순위의  ViewResolver를 사용한다.
		 */
		configurer
			.favorPathExtension(true)
			.favorParameter(false).parameterName("format")
			.ignoreAcceptHeader(true)
			.defaultContentType(MediaType.TEXT_HTML)
			.mediaType("csv", new MediaType("text", "csv"));
		
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<>();
		
		resolvers.add(csvViewResolver());
		resolvers.add(excelViewResolver());
		resolvers.add(pdfViewResolver());
		resolvers.add(xmlViewResolver());
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
	public ViewResolver xmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Users.class);
		return new XmlViewResolver(marshaller);
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
	
	@Bean
	public ViewResolver csvViewResolver() {
		ViewResolver viewResolver = new CsvViewResolver();
		return viewResolver;
	}
	
}
