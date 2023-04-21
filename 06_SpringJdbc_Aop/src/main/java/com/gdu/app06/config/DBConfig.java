package com.gdu.app06.config;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@EnableAspectJAutoProxy
@EnableTransactionManagement // 트랜잭션 처리를 허용한다. (DBconfig 에서 앞으로도 사용할 예정)
@Configuration // 얘가 뭐하는 애냐면 Spring 컨테이너에 빈을 만들어 두는 파일이다.
public class DBConfig {

	
	@Bean // 만들 빈에 붙여놓고 메소드 형태로 구현한다.
	//JdbcTemplete Bean 을 만드려고 한다. -- 오늘 새로 배우는거
	
	// 그럴려면
	// DriverManagerDataSource Bean 얘부터 만들어줘야함.
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("GDJ61");
		dataSource.setPassword("1111");
		return dataSource;
	}
	
	// JdbcTemplete Bean (Connection, PreparedStatement, ResultSet을 이용해서 동작하는 스프링 클래스)
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource()); // DriverManagerDataSource Bean을 JdbcTemplete 생성자에 주입
	}
	
	// DataSourceTransactionManager Bean
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource()); // DriverManagerDataSource Bean을 DataSourceTransactionManager 생성자에 주입
	}
	
	// 아래 부분은,
	// AOP를 이용해서 트랜잭션 처리를 하기 위한 Bean들인데 얘네들은 필수로 적어두도록 하자
	
	
	// TransactionInterceptor Bean 만들어줘야해
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		
		// 모든 트랜젝션 처리에서 Exception이 발생하면 Rollback을 수행하시오.
		RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();
		ruleBasedTransactionAttribute.setName("*");
		ruleBasedTransactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class))); // 언제 취소 할껍니까
	
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		source.setTransactionAttribute(ruleBasedTransactionAttribute);
		
		return new TransactionInterceptor(transactionManager(), source);
	
	
	}
	
	// 이게 다 AOP 동작을 위한 Bean들임.
	
	/*
	 	AOP 관련 핵심 용어 3가지
	 	1. 조인포인트(JoinPoint) : AOP를 적용시킬 수 있는 메소드 전체		- 목록 , 상세, 삽입, 수정, 삭제 중에서    
	 	2. 포인트컷(PointCut)	 : 조인포인트 중에서 AOP를 동작시킬 메소드  - 삽입, 수정, 삭제 의
	 	3. 어드바이스(Advice)	 : 포인트컷에 동작시킬 AOP 동작 자체		- 트랜잭션을 적용시켜보자
	 	
	 	조인트포인트 중에서 ? 포인트컷의 ? 어드바이스 해보자.
	 	
	 */
	
	// Advice Bean
	@Bean
	public Advisor advisor() {
		
		/*
		 	표현식(Expression) 작성 방법
		 	1. 형식
		 		excution(반환타입 패키지.클래스.메소드(매개변수))
		 	2. 의미
		 		1) 반환타입
		 			(1) * 		: 모든 반환타입
		 			(2) void	: void 반환타입
		 			(3) !void   : void를 제외한 반환타입
		 		2) 매개변수
		 			(1) .. 		: 모든 매개변수
		 			(2) *		: 1개의 모든 매개변수
		 			=> 매개변수의 제한이 있는
		 */
		
		// 포인트컷 설정(어드바이스(트랜잭션)을 동작시킬 메소드)  어떤메소드에 트랜잭션을 동작시킬거냐
		AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
		pointCut.setExpression("execution(* com.gdu.app06.service.BoardServiceImpl.*Tx(..))"); // BoardServiceImpl 클래스에 있는 메소드 중에서 이름이 Tx로 끝나는 메소드
		//                                                                   여기에 있는 *(모든)tx..
		return new DefaultPointcutAdvisor(pointCut, transactionInterceptor()); // pointCut으로 등록된 메소드에  transactionInterceptor()를 동작 시키시오.
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
