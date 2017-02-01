package model;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DefaultManager extends HibernateUtil {

	private String className = "";

	public DefaultManager(String className) 
	{
	    this.className = className;
	}
	public <Any> Any add(Object item) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		return ((Any)item);
	}
	public <Any> Any edit(Object item) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(item);
		session.getTransaction().commit();
		return ((Any)item);
	}
	public <Any> Any delete(Object item) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		return ((Any)item);
	}

	public List<Object> list() 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Object> result = null;
		try {
			result = (List<Object>)session.createQuery("from "+className).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return result;
	}

	public List<Object> list(Map<String, String> filters) 
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
		List<Object> result = null;
		try {
			result = (List<Object>)session.createQuery("from "+className+" "+queryConditions).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return result;
	}
	public List<Object> list(String query) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Object> result = null;
		try {
			result = (List<Object>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return result;
	}
	public Object findById(String id) 
	{
		String queryConditions = "WHERE id = "+id;
      	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Object> result = null;
		try {
			result = (List<Object>)session.createQuery("from "+className+" "+queryConditions).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return result.get(0);
	}
}
