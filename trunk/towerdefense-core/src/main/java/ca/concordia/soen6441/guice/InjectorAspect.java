//package ca.concordia.soen6441.guice;
//
//import javax.inject.Inject;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//
//import com.google.inject.Injector;
//
//@Aspect
//public class InjectorAspect {
//
//	@Inject Injector injector;
//	
//	@Before("execution(*.new(..)) && !within(InjectorAspect)")
//	public void logging(JoinPoint joinPoint) {
//		if (injector != null) {
//			injector.injectMembers(joinPoint.getThis());
//		}
//	}
//}
