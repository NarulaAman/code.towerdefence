package ca.concordia.soen6441.guice;

import javax.inject.Singleton;

import org.aspectj.lang.Aspects;

import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.logger.Log;
import ca.concordia.soen6441.logger.LogInterceptor;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.LoggerAspect;
import ca.concordia.soen6441.logic.GameMapDao;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;

public class TowerDefenseModule extends AbstractModule {
  @Override 
  protected void configure() {
	  LogInterceptor logger = new LogInterceptor();
	  requestInjection(logger);
//	    bindInterceptor(Matchers.any(), Matchers.annotatedWith(Log.class),  logger);
	   requestInjection(Aspects.aspectOf(LoggerAspect.class)); 
	   bind(GameMapDao.class).to(GameMapJavaSerializationDao.class);
	   bind(LogManager.class).in(Singleton.class);
	   install(new FactoryModuleBuilder().build(GamePlayFactory.class));
	   
  }
  
//  @Provides
//  LogMessage provide() {
//	  return new LogMessage();
//  }
  
//  
//  public static void main(String[] args) {
//	Injector injector = Guice.createInjector(new TowerDefenseModule());
//	InterceptorTest m = injector.getInstance(InterceptorTest.class);
////	System.out.println(m.g);
//	m.message();
////	System.out.println(m.method().toString());
	
//}
}