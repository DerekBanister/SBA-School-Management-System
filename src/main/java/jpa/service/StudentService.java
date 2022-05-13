package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {
	
//	Session session = null;

//	public Session getSession() {
//		SessionFactory factory = new Configuration().configure().buildSessionFactory();
//		
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		
//
//		t.commit();
//		return session;
//	}
	
	
	@Override
	public List<Student> getAllStudents() {
		return null;
//		String hql = "SELECT * FROM student";
//		session = getSession();
//		Query<Student> query = session.createQuery(hql);
//		List results = query.list();
//		System.out.println(results);
//		return results;
	
		
	}

	@Override
	public String getStudentByEmail(String sEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateStudent(String sEmail, String sPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Course> getStudentCourses(String sEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
