package com.onetomanyexp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class NamedQueryExample {

	void retriveCompanyByCompanyName(String companyName) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			emf = Persistence.createEntityManagerFactory("corebanking");
			entityManager = emf.createEntityManager();

			TypedQuery<Company> query = entityManager.createNamedQuery("company.findAll", Company.class);
			List<Company> complist = query.getResultList();

			for (Company comp : complist) {
				System.out.println("\n Company Id : " + comp.getCid());
				System.out.println("\n Company Name : " + comp.getCompanyName());
				System.out.println("\n" + comp.getContactNo());
			}

			entityManager.close();

			System.out.println("Company details successfull....");

		} catch (Exception e) {
			System.out.println(e);
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

		NamedQueryExample namedQueryExample = new NamedQueryExample();
		namedQueryExample.retriveCompanyByCompanyName("Samsung");

	}

}
