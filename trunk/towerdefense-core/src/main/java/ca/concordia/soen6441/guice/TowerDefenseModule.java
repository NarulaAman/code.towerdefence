package ca.concordia.soen6441.guice;

import ca.concordia.soen6441.logger.Log;
import ca.concordia.soen6441.logger.LogInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TowerDefenseModule extends AbstractModule {
  @Override 
  protected void configure() {
	  LogInterceptor weekendBlocker = new LogInterceptor();
	  requestInjection(weekendBlocker);
	    bindInterceptor(Matchers.any(), Matchers.annotatedWith(Log.class), 
	       weekendBlocker);
//	    bind(Dog.class).to(SuperDog.class);
	    
//     /*
//      * This tells Guice that whenever it sees a dependency on a TransactionLog,
//      * it should satisfy the dependency using a DatabaseTransactionLog.
//      */
//    bind(TransactionLog.class).to(DatabaseTransactionLog.class);
//
//     /*
//      * Similarly, this binding tells Guice that when CreditCardProcessor is used in
//      * a dependency, that should be satisfied with a PaypalCreditCardProcessor.
//      */
//    bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
  }
}