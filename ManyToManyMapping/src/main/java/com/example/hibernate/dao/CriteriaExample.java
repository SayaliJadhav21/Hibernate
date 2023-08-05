package com.example.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Subject;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.transaction.Transactional;

@Repository
public class CriteriaExample {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public List<Subject> getSubjects() {
		Session session = getSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Subject> cr = cb.createQuery(Subject.class);
		Root<Subject> root = cr.from(Subject.class);
		cr.select(root);

		Query<Subject> query = session.createQuery(cr);
		List<Subject> results = query.getResultList();
		results.forEach(subject -> subject.getName());
		return results;
	}

	@Transactional
	public List<Subject> getSubject() {
		Session session = getSession();
		// From Hibernate 6.0 the session.createCriteria is not available
		// Create criteriaBuilder
		CriteriaBuilder cb = session.getCriteriaBuilder();

		// Create criteriaQuery from a class using criteria builder
		CriteriaQuery<Subject> cr = cb.createQuery(Subject.class);

		// Set the root entity to get data from
		Root<Subject> root = cr.from(Subject.class);

		// Add the data to retrieve in select() and restictions in where(). The
		// restictions are created using criteriaBuilder
		cr.select(root).where(cb.gt(root.get("id"), 500));
//		cr.select(root).where(cb.lt(root.get("itemPrice"), 1000));
//		cr.select(root).where(cb.like(root.get("itemName"), "%chair%"));
//		cr.select(root).where(cb.between(root.get("itemPrice"), 100, 200));
//		cr.select(root).where(root.get("itemName").in("Skate Board", "Paint", "Glue"));
//		cr.select(root).where(cb.isNull(root.get("itemDescription")));
//		cr.select(root).where(cb.isNotNull(root.get("itemDescription")));

		Predicate[] predicates = new Predicate[2];
		predicates[0] = cb.isNotNull(root.get("id"));
		predicates[1] = cb.like(root.get("name"), "Data%");
		cr.select(root).where(predicates); // It will override previous criteria
//		cr.select(root).where(cb.or(predicates[0], predicates[1]));
//		cr.select(root).where(cb.and(predicates[0], predicates[1]));		

		// Order the results
		cr.orderBy(cb.asc(root.get("id")), cb.desc(root.get("name")));

		// Get the query from criteria
		Query<Subject> query = session.createQuery(cr);
		// Get the result
		List<Subject> results = query.getResultList();
		results.forEach(subject -> subject.getName());
		return results;
	}

	@Transactional
	public void learnCriteria() {
		Session session = getSession();
		// Aggregate Functions
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Double> cr2 = cb.createQuery(Double.class);
		Root<Subject> root2 = cr2.from(Subject.class);
		cr2.select(cb.avg(root2.get("id"))); // cb.avg(root.get("itemPrice")), max, sum,min can be used
		Query<Double> query2 = session.createQuery(cr2);
		Double itemProjected = query2.uniqueResult();
		System.out.println(itemProjected);

//		 Count no of rows in Subject table
//		CriteriaQuery<Long> cr2 = cb.createQuery(Long.class);
//		Root<Subject> root2 = cr2.from(Subject.class);
//		cr2.select(cb.count(root2)); // cb.avg(root.get("itemPrice")), max, sum,min can be used
//		Query<Long> query2 = session.createQuery(cr2);
//		Long itemProjected = query2.uniqueResult();
//		System.out.println(itemProjected);

	}

	@Transactional
	public void learnProjections() {
		// JPA Projections
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<Subject> subject = query.from(Subject.class);
		query.select(subject.get("name"));
		List<String> resultList = session.createQuery(query).getResultList();

		// To project multiple columns
		CriteriaQuery<Object[]> query2 = builder.createQuery(Object[].class);
		Root<Subject> Subject = query2.from(Subject.class);
		query2.multiselect(Subject.get("id"), Subject.get("name"), Subject.get("unitPrice"));
		List<Object[]> resultList2 = session.createQuery(query2).getResultList();

		// Projecting aggregated objects
		CriteriaQuery<Object[]> query3 = builder.createQuery(Object[].class);
		Root<Subject> subject2 = query3.from(Subject.class);
		query.multiselect(subject2.get("category"), builder.count(Subject));
		query.groupBy(Subject.get("category"));

		// HibernateProjection

		// Single-column projection
//		final CriteriaBuilder criteria = session.getCriteriaBuilder();
//		final CriteriaQuery<String> criteriaQuery = criteria.createQuery(String.class);
//		final Root<Subject> root = criteriaQuery.from(Subject.class);
//		final SingularAttribute<Subject, String> name = Subject_.name;
//		final Path<String> nameProjection = root.get(name);
//		criteriaQuery.select(nameProjection);
//		
//		//Multi-column projection
//		final CriteriaBuilder criteria = session.getCriteriaBuilder();
//		final CriteriaQuery<Object[]> criteriaQuery = criteria.createQuery(Object[].class);
//		final Root<Subject> root = criteriaQuery.from(Subject.class);
//		final SingularAttribute<Subject, String> name = Subject_.name;
//		final SingularAttribute<Subject, Long> id = Subject_.id;
//		final Path<String> nameProjection = root.get(name);
//		final Path<Long> idProjection = root.get(id);
//		criteriaQuery.multiselect(idProjection, nameProjection);

		// Group by is required for aggregation queries
//		final CriteriaBuilder criteria = session.getCriteriaBuilder();
//		final CriteriaQuery<Object[]> criteriaQuery = criteria.createQuery(Object[].class);
//		final Root<Product> root = criteriaQuery.from(Product.class);
//		criteriaQuery.groupBy(root.get("category"));
//		criteriaQuery.multiselect(root.get("category"), criteria.count(root));
	}

}
