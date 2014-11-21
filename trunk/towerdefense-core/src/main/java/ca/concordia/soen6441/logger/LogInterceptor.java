package ca.concordia.soen6441.logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		Log l = arg0.getMethod().getAnnotation(Log.class);
		System.out.println("Annotation:" + l.value() + " on method: " + arg0.getMethod());
		return arg0.proceed();
	}

}
