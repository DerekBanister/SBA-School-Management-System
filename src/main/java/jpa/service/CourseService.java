package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class CourseService implements CourseDAO {

    @Override
    public List<Course> getAllCourses() {
    	Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
        try {
        TypedQuery tq = session.createQuery("FROM Course");
        List<Course> results= tq.getResultList();
        session.close();
        return results;
        } catch (NoResultException e){
            session.close();
            System.out.println("|No Results Found|");
            return Collections.emptyList();
        }
    }
}
