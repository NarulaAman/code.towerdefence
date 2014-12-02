package ca.concordia.soen6441.guice;

import javax.inject.Singleton;

import ca.concordia.soen6441.dao.GameMapDao;
import ca.concordia.soen6441.dao.GamePlayDao;
import ca.concordia.soen6441.dao.HighScoresDao;
import ca.concordia.soen6441.dao.MapLoggerDao;
import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.io.HighScoresJavaSerializationDao;
import ca.concordia.soen6441.io.MapLoggerJavaSerializationDao;
import ca.concordia.soen6441.logger.GamePlayLogger;
import ca.concordia.soen6441.logger.MapLogger;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.HighScores;

import com.google.inject.AbstractModule;


/**
 * The Class TowerDefenseModule.
 */
public class TowerDefenseModule extends AbstractModule {
  
  /**
   * This function will configure the module bindings
   */
  @Override 
  protected void configure() {
		bind(GameMapJavaSerializationDao.class).in(Singleton.class);
		bind(GameMapDao.class).to(GameMapJavaSerializationDao.class);
		bind(HighScoresDao.class).to(HighScoresJavaSerializationDao.class);
		bind(MapLoggerDao.class).to(MapLoggerJavaSerializationDao.class);
		bind(GamePlayDao.class).to(GamePlayJavaSerialaizationDao.class);
		requestStaticInjection(GamePlay.class);
		requestStaticInjection(HighScores.class);
		requestStaticInjection(MapLogger.class);
		requestStaticInjection(GameMap.class);
		bind(GamePlayLogger.class).in(Singleton.class);
//		install(new FactoryModuleBuilder().build(GamePlayFactory.class));
//		LogInterceptor logger = new LogInterceptor();
//		requestInjection(logger);
//		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Log.class),  logger);
//		requestInjection(Aspects.aspectOf(LoggerAspect.class));
//		requestInjection(Aspects.aspectOf(InjectorAspect.class));
  }
}