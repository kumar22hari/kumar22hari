package com.onetomanyexp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAQueryExample {

	void retriveCompanyByCompanyName(String companyName) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			emf = Persistence.createEntityManagerFactory("corebanking");
			entityManager = emf.createEntityManager();

			transaction = entityManager.getTransaction();
			
			/*
			 * //Named Parameters in Queries Query query = entityManager.
			 * createQuery("SELECT c FROM Company c WHERE c.companyName LIKE :cName");
			 * query.setParameter("cName", companyName) .getResultList();
			 */
			
			//Positional Parameters in Queries
			Query query1 = entityManager.createQuery("SELECT c FROM Company c WHERE c.companyName LIKE ?1");
			query1.setParameter(1, companyName);
	        List<Company> complist =query1.getResultList(); 
	        
	        for(Company comp:complist) {  
	             System.out.println("\n Company id : " + comp.getCid());  
	             System.out.println("\n Company Name : " + comp.getCompanyName());  
	             System.out.println("\n" + comp.getContactNo());  
	          }  

			entityManager.close();

			System.out.println("Company details successfull....");

		} catch (Exception e) {
//			System.out.println(e);
			transaction.rollback();
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.commit();
			}
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

	public static void main(String[] args) {
		
		JPAQueryExample jpaQueryExample= new JPAQueryExample();
		jpaQueryExample.retriveCompanyByCompanyName("Samsung");

	}

}