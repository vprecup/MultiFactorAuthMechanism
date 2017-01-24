package model;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UserManager extends HibernateUtil {

	public User add(User user) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		return user;
	}
	public User edit(User user) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		return user;
	}
	public User delete(Long id) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, id);
		if(null != user) {
			session.delete(user);
		}
		session.getTransaction().commit();
		return user;
	}

	public List<User> list() 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = null;
		try {
			users = (List<User>)session.createQuery("from User").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return users;
	}

	public List<User> list(Map<String, String> filters) 
	{
		String queryConditions = "WHERE ";
      	Iterator it = filters.entrySet().iterator();
      	while (it.hasNext()) 
      	{	
         	Map.Entry entry = (Map.Entry)it.next();
         	queryConditions+=entry.getKey()+" = '"+entry.getValue()+"' ";
         	if(it.hasNext())
            	queryConditions+="&& ";
      	}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = null;
		try {
			users = (List<User>)session.createQuery("from User "+queryConditions).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return users;
	}
	public List<User> list(String query) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = null;
		try {
			users = (List<User>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return users;
	}
}
