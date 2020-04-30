package ch.bitz.SpringData;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class JpaConfig {


	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:h2:~/test");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "ch.bitz.SpringData.data" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return properties;
	}
	
//	
//	/**
//	 * DB settings
//	 * @return
//	 */
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		dataSource.setUrl("jdbc:h2:~/test");
//
//		return dataSource;
//	}
//
//	/** 
//	 * EntityManager sets DataSource, Packages to Scan,
//	 * 
//	 * @return
//	 */
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan(new String[] { "ch.bitz.SpringData.data" });
//
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(additionalProperties());
//
//		return em;
//	}
//	
//	/**
//	 * sagt das die Transaktion mit JPA gemanaged werden
//	 * @return
//	 */
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//	    JpaTransactionManager transactionManager = new JpaTransactionManager();
//	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//	 
//	    return transactionManager;
//	}
//	 
//	/** 
//	 * wandelt vermutlich Exceptions der DB um
//	 * @return
//	 */
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//	    return new PersistenceExceptionTranslationPostProcessor();
//	}
//	 
//	Properties additionalProperties() {
//	    Properties properties = new Properties();
//	    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//	        
//	    return properties;
//	}

}
