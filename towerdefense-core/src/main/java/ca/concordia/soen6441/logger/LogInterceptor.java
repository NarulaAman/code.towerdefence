package ca.concordia.soen6441.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Log l = methodInvocation.getMethod().getAnnotation(Log.class);
		if (l.value() != null) {
			List<Object> args = new ArrayList<>();
			args.add(methodInvocation.getThis());
			args.addAll(Arrays.asList(methodInvocation.getArguments()));
			System.out.println(String.format(l.value(), args.toArray()));
		}
		else {
			System.err.println("Annotation:" + l.value() + " on method: " + methodInvocation.getMethod());
		}
		return methodInvocation.proceed();
	}

}
