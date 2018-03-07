package com.itgrids.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.itgrids" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration implements EnvironmentAware{

	private Environment environment;

    
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.itgrids" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    /*@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }*/
    
    @Bean
    public DataSource dataSource() {
    	try{
    	JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        return (DataSource) dataSource.getJndiTemplate().lookup("java:comp/env/jdbc/prrws", DataSource.class);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /*private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        
        //properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("jdbc.driverClassName"));
        properties.put("hibernate.connection.username", environment.getRequiredProperty("jdbc.username"));
        properties.put("hibernate.connection.password", environment.getRequiredProperty("jdbc.password"));
        properties.put("hibernate.connection.url", environment.getRequiredProperty("jdbc.url"));
        properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("jdbc.driverClassName"));
        properties.put("hibernate.connection.provider_class", "org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");
        properties.put("hibernate.c3p0.max_size", "100");
        properties.put("hibernate.c3p0.min_size", "5");
        properties.put("hibernate.c3p0.acquire_increment", "1");
        properties.put("hibernate.c3p0.idle_test_period", "300");
        properties.put("hibernate.c3p0.max_statements", "50");
        properties.put("hibernate.c3p0.timeout", "100");
        properties.put("hibernate.c3p0.testInterval", "1");
        properties.put("hibernate.c3p0.preferredTestQuery", "SELECT 1");
        properties.put("hibernate.c3p0.testConnectionOnCheckout", "true");
        
        properties.put("hibernate.connection.useUnicode",true);
        properties.put("hibernate.connection.characterEncoding", "utf8");
        properties.put("hibernate.connection.charSet", "utf8");
        
        return properties;        
    }*/
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("jdbc.driverClassName"));
        properties.put("hibernate.connection.useUnicode",true);
        properties.put("hibernate.connection.characterEncoding", "utf8");
        properties.put("hibernate.connection.charSet", "utf8");
       
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
    
    @Bean
	public HibernateTemplate hibernateTemplate( SessionFactory sessionFactory ) {
		return new HibernateTemplate(sessionFactory);
	}
    
    
   
}


