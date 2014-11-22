package ca.concordia.soen6441.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoggerAspect {

	@Inject LogManager logManager;
	
	@After("execution(* *(..)) && @annotation(Log)")
	public void logging(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Log logAnnotation = methodSignature.getMethod().getAnnotation(Log.class);
		if (logAnnotation.value() != null && !logAnnotation.value().trim().isEmpty()) {
			List<Object> args = new ArrayList<>();
			args.add(joinPoint.getThis());
			args.addAll(Arrays.asList(joinPoint.getArgs()));
			String logMessage = String.format(logAnnotation.value(), args.toArray());
			logManager.log(joinPoint.getThis().getClass(),joinPoint.getThis(), logMessage);
		}
		else {
			System.err.println("No log message for method: " +joinPoint.getSignature());
		}
	}
}
