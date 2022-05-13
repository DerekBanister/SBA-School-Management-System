package jpa.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {
	
	@Override
	public List<Student> getAllStudents() {
//	    Select all Students
		Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
		 try {
	            TypedQuery tQuery = session.createQuery("FROM Student");
	            List<Student> studentList = tQuery.getResultList();
	            session.close();
	            return studentList;
	        } catch (NoResultException e) {
	            session.close();
	            System.out.println("Nothing Found");
	            return Collections.emptyList();
	        }
	}
	
	
	@Override
	public Student getStudentByEmail(String sEmail) {
		//select student by email
		Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
		String tQuery = "FROM Student s WHERE s.sEmail = :sEmail";
		//setParameter to filter by email
        Query q = session.createQuery(tQuery).setParameter("sEmail", sEmail);
        try{
        	//get one result from the query list
            Student studentName = (Student) q.getSingleResult();
            return studentName;
        } catch (NoResultException e){
            System.out.println("Nothing Found");
            session.close();
            return null;
        }
	}

	@Override
	  public Boolean validateStudent(String sEmail, String sPassword) {
		Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
        try {
        	//first find a student by their email
            TypedQuery tQuery = session.getNamedQuery("validateStudent");
            tQuery.setParameter("sEmail", sEmail);
            Student student = (Student) tQuery.getSingleResult();
            session.close();
            //then check if email && pass are correct
            return Objects.equals(student.getsPass(), sPassword) && (Objects.equals(student.getsEmail(), sEmail));
        } catch (NoResultException e) {
            System.out.println("Student cannot be validated");
            return false;
        }
    }

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
		Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();
        String hql = "FROM Student s LEFT JOIN FETCH s.sCourses c WHERE s.sEmail = :sEmail";
        String hql2 = "FROM Course c WHERE c.cId = :cId";
        TypedQuery tQuery = session.createQuery(hql).setParameter("sEmail", sEmail);
        TypedQuery tQuery2 = session.createQuery(hql2).setParameter("cId", cId);
        Student s = (Student) tQuery.getSingleResult();
        Course c = (Course) tQuery2.getSingleResult();
        try {
            if (s.getsCourses().contains(c)) {
                System.out.println("You are already registered in that course!");
            } else if (!s.getsCourses().contains(c)) {
                s.getsCourses().add(c);
                System.out.println("You are now enrolled in this course.");
                session.save(s);
                t.commit();
                session.close();
            }
        } catch (NoResultException e) {
            System.out.println("Nothing Found");
        }
	}
	@Override
	public List<Course> getStudentCourses(String sEmail) {
		Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
		try {
            TypedQuery tQuery = session.getNamedQuery("getStudentCourses");
            tQuery.setParameter("sEmail", sEmail);
            Student student = (Student) tQuery.getSingleResult();
            List<Course> courseList = student.getsCourses();
            session.close();
            return courseList;
        } catch (NoResultException e) {
            session.close();
            System.out.println("Nothing");
            return Collections.emptyList();
        }
	}
}
