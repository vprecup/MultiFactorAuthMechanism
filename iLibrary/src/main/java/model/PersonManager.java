package model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class PersonManager extends HibernateUtil {

	public Person add(Person person) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
		return person;
	}
	public Person delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person person = (Person) session.load(Person.class, id);
		if(null != person) {
			session.delete(person);
		}
		session.getTransaction().commit();
		return person;
	}

	public List<Person> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Person> persons = null;
		try {
			
			persons = (List<Person>)session.createQuery("from person").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return persons;
	}
}
