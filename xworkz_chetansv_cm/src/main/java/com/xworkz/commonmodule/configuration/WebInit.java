package com.xworkz.commonmodule.configuration;

import javax.servlet.MultipartConfigElement;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.extern.slf4j.Slf4j;
import java.io.File;

@Slf4j

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

	public WebInit() {
		log.info("creating" + this.getClass().getSimpleName());
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("this is getRootConfigClasses");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("this is getServletConfigClasses");
		return new Class[] { WebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		log.info("this is getServletMappings");
		return new String[] { "/" };
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		System.out.println("this is Default Servlet Handler Configurer");
		configurer.enable();
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		log.info("creating" + " customizeRegistration");
		String tempDir = "D:\\temp";
		int maxUploadSizeInMb = 3 * 1024 * 1024;
		File uploadDirectory = new File(tempDir);
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
				maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
		registration.setMultipartConfig(multipartConfigElement);
	}

	
	

}
