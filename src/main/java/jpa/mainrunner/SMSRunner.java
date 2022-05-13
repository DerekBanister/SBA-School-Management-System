package jpa.mainrunner;

import static java.lang.System.out;
import java.lang.System.Logger.Level;
import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

// example email: tattwool4@biglobe.ne.jp
// example password: Hjt0SoVmuBz

public class SMSRunner {
	// need a method that prompts user for input, stores that in var, then uses sql commands and switch case? to create new student
	CourseDAO courseDAO= new CourseService();
    StudentDAO studentDAO= new StudentService();
    Session session = new 
			Configuration().configure().buildSessionFactory().openSession();
	Scanner scan = new Scanner(System.in); 
	
	public static void main(String[] args) {
	
		 SMSRunner sms = new SMSRunner();
	        sms.start();
	}
	
	private void start() {
		// prompt user to start program, if yes run login function
	   System.out.println(" ");
       System.out.println("_____________________________________________________________________________ ");
	   System.out.println("Welcome to the School Management System. Are you a Student? Type Yes or No");
	   String action = scan.nextLine(); 
	   if(action.equals("Yes") || action.equals("yes")) {
		   login();
	   } else {
		   System.out.println("Thanks for using the SMS");
	   }

	}
    public void login() {
        System.out.println("Enter your e-mail:");
        String email = scan.nextLine();
        System.out.println("Enter your password:");
        String pass = scan.nextLine();
        try {
        	//if student gets validated, send them to class menu
            if (studentDAO.validateStudent(email, pass)) {
                Student verified = studentDAO.getStudentByEmail(email);
                System.out.println("Welcome, " + verified.getsName());
                classMenu(verified);
            }else {
            	//if invalid, run login again
                System.out.println("Invalid login.");
                login();
            }
        }  catch (NoResultException nre) {
            System.out.println("Cannot find Student");
            login();
        }
    }
    public void classMenu(Student withAuth) {

		//present menu
		System.out.println("You are successfully logged in!");
		System.out.println("_________________________________");
		System.out.println("Type in what you would like to do, here are some options");
		System.out.println("Show all students");
		System.out.println("Show all Courses");
		System.out.println("Show my Courses");
		System.out.println("Show student info");
		System.out.println("Exit");

		 //next will display options, with switch cases calling services
		int option = scan.nextInt();
		switch (option) {
		//each option will call a StudentService.whatever it needs to do
        case 1: 
        	System.out.println("\nYou are currently enrolled in Course(s):\n");
             findCourses(withAuth);
             break;
        case 2: 
        	System.out.println("Course Registration Menu");
        	registerCourse(withAuth);
        	break;
        case 3:  
        	System.out.println("Invalid input.");
        	classMenu(withAuth);
        	break;
        case 4: 
        	System.out.println("Thank you for visiting!");
        	System.exit(option);
        	break;
        case 5:
        	System.out.println("Thanks for using SMS");
        	break;
        default: System.out.println("Please try another option");
        break;
		}
    }
    private void registerCourse(Student withAuth) {
        System.out.println("Which course would you like to register for?");
        try {
            List<Course> courseList = courseDAO.getAllCourses();
            courseList.forEach(Course -> System.out.println(Course.getcId() + " for " + Course.getcName() + " with " + Course.getcInstructorName()));
            int courseSelection = Integer.parseInt(scan.nextLine());
            studentDAO.registerStudentToCourse(withAuth.getsEmail(), courseSelection);
            System.out.println("Returning to Course Menu.");
            classMenu(withAuth);
        } catch (NoResultException nre) {
            System.out.println("There is no course for that ID.");
            registerCourse(withAuth);
        } catch (NumberFormatException nfe) {
            System.out.println("Enter the Class ID of the Course.");
            registerCourse(withAuth);
        }
    }
    
    private void findCourses(Student withAuth) {
        if(studentDAO.getStudentCourses(withAuth.getsEmail()).size() > 0) {
            List<Course> courseList = studentDAO.getStudentCourses(withAuth.getsEmail());
            courseList.forEach(Course -> System.out.println(Course.getcName() + " with " + Course.getcInstructorName()));
            classMenu(withAuth);
        }  else  {
            System.out.println("You are not registered to any courses.");
            classMenu(withAuth);
        }
    }
 
}
