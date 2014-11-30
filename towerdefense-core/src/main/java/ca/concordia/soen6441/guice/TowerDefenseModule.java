package ca.concordia.soen6441.guice;

import javax.inject.Singleton;

import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.io.HighScoresJavaSerializationDao;
import ca.concordia.soen6441.io.MapLoggerJavaSerializationDao;
import ca.concordia.soen6441.logger.LogInterceptor;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.MapLogger;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GameMapDao;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.GamePlayDao;
import ca.concordia.soen6441.logic.HighScores;
import ca.concordia.soen6441.logic.HighScoresDao;
import ca.concordia.soen6441.logic.MapLoggerDao;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;


/**
 * The Class TowerDefenseModule.
 */
public class TowerDefenseModule extends AbstractModule {
  
  /**
   * This function will configure
   */
  @Override 
  protected void configure() {
	  LogInterceptor logger = new LogInterceptor();
	  requestInjection(logger);
//	    bindInterceptor(Matchers.any(), Matchers.annotatedWith(Log.class),  logger);
//	   requestInjection(Aspects.aspectOf(LoggerAspect.class));
//	   requestInjection(Aspects.aspectOf(InjectorAspect.class));
	   bind(GameMapJavaSerializationDao.class).in(Singleton.class);
	   bind(GameMapDao.class).to(GameMapJavaSerializationDao.class);
	   bind(HighScoresDao.class).to(HighScoresJavaSerializationDao.class);
	   bind(MapLoggerDao.class).to(MapLoggerJavaSerializationDao.class);
	   bind(GamePlayDao.class).to(GamePlayJavaSerialaizationDao.class);
	   requestStaticInjection(GamePlay.class);
	   requestStaticInjection(HighScores.class);
	   requestStaticInjection(MapLogger.class);
	   requestStaticInjection(GameMap.class);
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