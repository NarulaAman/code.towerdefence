//package ca.concordia.soen6441.guice;
//
//import javax.inject.Inject;
//
//import ca.concordia.soen6441.logger.Log;
//import ca.concordia.soen6441.logger.LogManager;
//
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//
//public class InterceptorTest {
//
//	@Inject public LogManager logManager;
//	
//	@Log("YES")
//	public void message() {
//		System.out.println("Message");
//	}
//	
//  public static void main(String[] args) {
//	Injector injector = Guice.createInjector(new TowerDefenseModule());
//	InterceptorTest m = injector.getInstance(InterceptorTest.class);
////	System.out.println(m.g);
//	System.out.println(new InterceptorTest().logManager);
////	System.out.println(m.method().toString());
//}
//}
