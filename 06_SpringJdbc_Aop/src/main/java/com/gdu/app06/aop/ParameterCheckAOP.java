package com.gdu.app06.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component // Spring Container에 ParameterCheckAOP 객체를 Bean으로 만든다.
@Aspect // ParameterCheckAOP 클래스는 AOP동작을 위한 클래스이다.
// @EnableAspectJAutoProxy => DBConfig에 이미 있어서 여기서는 안써도 됨
public class ParameterCheckAOP {
	
	// 포인트컷(어떤 메소드에 어드바이스(AOP 동작)를 적용할 것인가?
	@Pointcut("execution(* com.gdu.app06.controller.*Controller.*ParamCheck(..))") // 컨트롤러의 메소드 중 ParamCheck로 끝나는 메소드
	//                                              모든컨트롤러에 모든파람
	public void setPointCut() {
		// 이 메소드는 이름만 제공하는 역할 (바지 사장) 아무 이름이나 써도 되고, 본문도 필요없음
		// 셋포인트컷을 부르면 위에 골뱅이 @포인트컷이 실행된다. 로 알면 됨.
	}
	
	// 어드바이스(포인트컷에서 실제로 동작할 작업 : 파라미터들의 값을 LOGGER를 이용해서 콘솔로 확인하고자 한다)
	
	// 파라미터를 콘솔에 출력하기 위한 LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterCheckAOP.class);
	// 파이널처리해서 필드명 대문자임
	
	@After("setPointCut()")// @After : 메소드 실행 후에 동작한다.
	public void paramLogging(JoinPoint joinPoint) {
		// 모든파라미터가 저장된 HttpServletRequest 가져오기
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		// HttpServletRequest -> Map으로 변환하기
		// 모든 파라미터가 Map의 Key로 변환된다. Map의 Key는 반복문으로 모두 순회할 수 있다.
		Map<String, String[]> map = request.getParameterMap();
		
		// 콘솔에 출력할 형태 만들기
		// [파라미터명=값]
		String str = "";
		if(map.isEmpty()) { // 파라미터가 없으면
			str += "[No Parameter]";
		} else {
			for(Entry<String, String[]> entry : map.entrySet()) {
				str += "[" + entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "]";
				// 배열을 문자열로 바꿔주는.
			}
		}

		/*어드바이스 실행
		// 치환 문자 : {}
		LOGGER.debug("{} {} {}", request.getMethod(), request.getRequestURI(), str);
		
		/*어드바이스 실행
		Object obj = null;
		try {
			obj = joinPoint.proceed(); // 실제 실행은 여기
		} catch(Exception e) {
			throw e; // parapLogging() 메소드가 throws Throwable 처리하고 있다.
		} finally {
			// 언제나 실행되는 구간
			// 여기서 콘솔에 str을 로그로 남긴다.
		}
		return obj;
		 */
	
		
		// 어드바이스 실행
		// 로그 치환 문자 : {}
		LOGGER.debug("{} {} {}", request.getMethod(), request.getRequestURI(), str);
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
