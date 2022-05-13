package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;

public class SMSRunner {
	// need a method that prompts user for input, stores that in var, then uses sql commands and switch case? to create new student
	public static void main(String[] args) {
		
		Session session = new 
				Configuration().configure().buildSessionFactory().openSession();
		Scanner scan = new Scanner(System.in);  
	    System.out.println("Welcome to the School Management System. Are you a Student? Enter Yes or No");

	    String action = scan.nextLine(); 
	    String moveOn = "Yes";
	    
	    if(action.equals(moveOn)) {
	    	System.out.println("Enter your Email:");
	    	String email = scan.nextLine();
	    	System.out.println("Enter your password:");
	    	String password = scan.nextLine();
	    	System.out.println(password);
	    	System.out.println(email);
	    	//check login values pass/email versus what is in db? idk how we do that
	    } else {
	    	System.out.println("Goodbye!");
	    	scan.close();
	    }
	    
	    
	    //next will display options, with switch cases calling services
	    
	    //example query to db. works and connected
//		String SELECT = "FROM Student";
//		Query q = session.createQuery(SELECT, Student.class);
//		List<Student> resultList = (List<Student>) q.list();
//		
//		for(Student s: resultList) {
//			System.out.println(s);
//		}
	}

}
