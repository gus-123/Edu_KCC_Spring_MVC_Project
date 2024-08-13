package org.zerock.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	// point cut 적용 지점
	// execution... 문자열은 AspectJ의 표현식
	// 맨 앞의 *은 접근제한자를 의미하며,
	// 맨뒤의 (*.*)에서 첫번째 *은 SampleService클래스의 이름과 두번째 *은 모든 메서드의 이름을 의미
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("--------------------------------");
	}
}
