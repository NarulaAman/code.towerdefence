package ca.concordia.soen6441.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor{

	@Inject private LogManager logManager;
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object result = methodInvocation.proceed();
		Log l = methodInvocation.getMethod().getAnnotation(Log.class);
		if (l.value() != null && !l.value().trim().isEmpty()) {
			List<Object> args = new ArrayList<>();
			args.add(methodInvocation.getThis());
			args.addAll(Arrays.asList(methodInvocation.getArguments()));
			String logMessage = String.format(l.value(), args.toArray());
			logManager.log(methodInvocation.getMethod().getDeclaringClass(),methodInvocation.getThis(), logMessage);
		}
		else {
			System.err.println("No log message for method: " + methodInvocation.getMethod());
		}
		return result;
	}

}
