package ca.concordia.soen6441.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * The Class LoggerAspect.
 */
@Aspect
public class LoggerAspect {

	/** The log manager. */
	@Inject LogManager logManager;
	
	/**
	 * Logging.
	 *
	 * @param joinPoint the join point
	 */
	@After("execution(* *(..)) && @annotation(Log)")
	public void logging(JoinPoint joinPoint) {
		Log logAnnotation = getLogAnnotation(joinPoint);
		if (hasLogMessageFormat(logAnnotation)) {
			String logMessage = String.format(getLogMessageFormat(logAnnotation), getLoggingArgs(joinPoint));
//			logManager.log(joinPoint.getThis().getClass(),joinPoint.getThis(), logMessage);
		}
		else {
			System.err.println("No log message format for method: " +joinPoint.getSignature());
		}
	}

	/**
	 * Gets the log message format.
	 *
	 * @param logAnnotation the log annotation
	 * @return the log message format
	 */
	private String getLogMessageFormat(Log logAnnotation) {
		return logAnnotation.value();
	}
	
	/**
	 * Checks for log message format.
	 *
	 * @param logAnnotation the log annotation
	 * @return true, if successful
	 */
	private boolean hasLogMessageFormat(Log logAnnotation) {
		return getLogMessageFormat(logAnnotation) != null && !getLogMessageFormat(logAnnotation).trim().isEmpty();
	}

	/**
	 * Gets the log annotation.
	 *
	 * @param joinPoint the join point
	 * @return the log annotation
	 */
	private Log getLogAnnotation(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		return methodSignature.getMethod().getAnnotation(Log.class);
	}
	
	/**
	 * Gets the logging args.
	 *
	 * @param joinPoint the join point
	 * @return the logging args
	 */
	private Object[] getLoggingArgs(JoinPoint joinPoint) {
		List<Object> args = new ArrayList<>();
		args.add(joinPoint.getThis());
		args.addAll(Arrays.asList(joinPoint.getArgs()));
		return args.toArray();
	}
}
