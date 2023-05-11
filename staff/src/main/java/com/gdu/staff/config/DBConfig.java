package com.gdu.staff.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan(basePackages = {"com.gdu.staff.mapper"})
@PropertySource(value={"classpath:application.properties"}) // 프로퍼티파일 읽어와라 (에플리케이션.프로퍼티스 파일의 속성일 읽어오자.
						//     리소시스바로밑에만들어서 폴더이름 없이 바로 application~적었음.
@EnableTransactionManagement // 트랜잭션 처리를 허용한다.
@Configuration
public class DBConfig {

	@Autowired
	private Environment env; 
	
	// HidaryConfig Bean 
	//애플리케이션에있는 설정값을 읽어오세요
	//(디비) 프로퍼티를 분리하는 이유: gitignore안할라고 깃에 안올릴라고
	@Bean
	public HikariConfig hikariConfig() { // 얘는 커넥션풀에 속도를 높여주는애
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));
										//env.getProperty 가 ""를 읽어 옴. 그냥 히카리의 사용법임
		hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
		hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
		hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
		
		return hikariConfig;
		// 방금 만든 객체 반환.
	}
	
	// HikariDataSource Bean
	@Bean(destroyMethod="close") // 이건그냥 히카리홈피에 사용법 나와있는거.
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig()); // 위에서 만든 메소드 호출
	}
	
	// == ==> 커넥션 풀 끝
	
	// SqlSessionFactory Bean
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(hikariDataSource()); // 위에서 만든 메소드
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location")));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
		return bean.getObject();
	}
	
	// SqlSessionTemplate Bean (기존의 SqlSessionFactory에서 이름이 달라진 것 )
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	// 빈 4개 만들었음. 결론적으로 sqlSessionTemplate 만드려고.
	
	
	// TransactionManager Bean
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(hikariDataSource());
	}
	// 얘를 쓰기 위해 맨위에 @EnableTransactionManagement를 추가해줬음
	

	
	
	
	
	
	
	
	
	
	
	
	
}
