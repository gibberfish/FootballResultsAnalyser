package uk.co.mindbadger.footballresultsanalyser.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
   private static final SessionFactory sessionFactory = buildSessionFactory();
   
   private static SessionFactory buildSessionFactory() {
       try {
    	   Configuration configuration = new Configuration();
    	   configuration.configure();
    	   ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
    	   SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	   return sessionFactory;
    	   
           // The deprecated way of creating the SessionFactory from hibernate.cfg.xml
           //return new Configuration().configure().buildSessionFactory();
       } catch (Throwable ex) {
           System.err.println("Initial SessionFactory creation failed." + ex);
           throw new ExceptionInInitializerError(ex);
       }
   }
 
   public static SessionFactory getSessionFactory() {
       return sessionFactory;
   }
   
   public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
