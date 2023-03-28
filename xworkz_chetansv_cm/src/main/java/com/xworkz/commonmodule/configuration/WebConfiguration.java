package com.xworkz.commonmodule.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.xworkz.commonmodule")
@Slf4j
@EnableWebMvc

public class WebConfiguration {

	public WebConfiguration() {
		log.info("creating" + this.getClass().getSimpleName());
	}

	@Bean

	public ViewResolver viewResolver() {

		log.info("Registering the viewResolver in to spring");

		return new InternalResourceViewResolver("/", ".jsp");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		log.info("Registering the multipart resolver in to spring");
		return new StandardServletMultipartResolver();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		log.info("Registering localContainerEntityManagerFactoryBean in to spring");
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.xworkz.commonmodule.entity");
		bean.setDataSource(dataSource());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return bean;
	}

	public DataSource dataSource() {
		log.info("registering datasource in to spring ");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/project");
		dataSource.setPassword("Xworkzodc@123");
		dataSource.setUsername("root");

		return dataSource;

	}
}