package com.onetomanyexp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

//https://www.objectdb.com/java/jpa/query/named

public class CriteriaQueryExample {

	void retriveCompanyByCompanyName(String companyName) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			emf = Persistence.createEntityManagerFactory("corebanking");
			entityManager = emf.createEntityManager();

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			CriteriaQuery<Company> q = cb.createQuery(Company.class);
			Root<Company> c = q.from(Company.class);
			q.select(c);
			TypedQuery<Company> query = entityManager.createQuery(q);
			List<Company> complist = query.getResultList();

			for (Company comp : complist) {
				System.out.println("\n Company id : " + comp.getCid());
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

		CriteriaQueryExample criteriaQueryExample = new CriteriaQueryExample();
		criteriaQueryExample.retriveCompanyByCompanyName("Samsung");

	}

}
