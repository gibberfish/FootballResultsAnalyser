package mjf.hibernate.test;

import java.util.List;

import mindbadger.footballresultsanalyser.hibernate.Division;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTestApplicationStart {
	public static void main(String[] args) {
		Transaction tx = null;
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
	    Session session = sessionFactory.openSession();
	    		
	    try {
	      tx = session.beginTransaction();
	      
	      List divisions = session.createQuery("from Division").list();
	      
	      for ( Division division : (List<Division>) divisions ) {
	    	  System.out.println("Division id = " + division.getDivId() + ", name= " + division.getDivName());
	    	}
	      
	      tx.commit();
	    } catch (RuntimeException e) {
	      if (tx != null && tx.isActive()) {
	        try {
	        	// Second try catch as the rollback could fail as well
	        	tx.rollback();
	        } catch (HibernateException e1) {
	        	System.err.println("Error rolling back transaction");
	        }
	        // throw again the first exception
	        throw e;
	      }
	    }
	}
}
