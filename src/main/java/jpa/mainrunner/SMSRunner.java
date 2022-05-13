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
                Student withAuth = studentDAO.getStudentByEmail(email);
                System.out.println("Welcome to the SMS!, " + withAuth.getsName() + "!");
                mainMenu(withAuth);
            }else {
            	//if invalid, run login again
                System.out.println("Invalid login.");
                login();
            }
        }  catch (NoResultException nre) {
            System.out.println("Cannot find student");
            login();
        }
    }
    //main menu
    public void mainMenu(Student withAuth) {

		//present menu
		System.out.println("You are successfully logged in!");
		System.out.println("_________________________________");
		System.out.println("Type in the corresponding number for which choice you would like to make");
		System.out.println("1. Show all students");
		System.out.println("2. Show all Courses");
		System.out.println("3. Show my Courses");
		System.out.println("4. Show student info");
		System.out.println("5. Exit");

		 //next will display options, with switch cases calling services
		int option = scan.nextInt();
		switch (option) {
		//each option will call a StudentService.whatever it needs to do
        case 1: 
        	System.out.println("\nYou are currently enrolled in:\n");
             findCourses(withAuth);
             break;
        case 2: 
        	System.out.println("Course Registration Menu");
        	registerCourse(withAuth);
        	break;
        case 3:
        	System.out.println("Thanks for using SMS");
        	break;
        default: 
        System.out.println("Please try another option");
        mainMenu(withAuth);
        break;
		}
    }
    private void registerCourse(Student withAuth) {
        System.out.println("Which course would you like to register for?");
       
        try {
            List<Course> courseList = courseDAO.getAllCourses();
            //parse data from courses
            courseList.forEach(Course -> System.out.println(Course.getcId() + " for " + Course.getcName() + " with " + Course.getcInstructorName()));
            //let user select course
            int courseSelection = Integer.parseInt(scan.nextLine());
            //register student
            studentDAO.registerStudentToCourse(withAuth.getsEmail(), courseSelection);
            System.out.println("Returning to Course Menu.");
            mainMenu(withAuth);
        } catch (NoResultException nre) {
            System.out.println("There is no course for that ID.");
            registerCourse(withAuth);
        } catch (NumberFormatException nfe) {
            System.out.println("Enter the Class ID of the Course.");
            registerCourse(withAuth);
        }
    }
    
    private void findCourses(Student withAuth) {
    	//if the student has any courses
        if(studentDAO.getStudentCourses(withAuth.getsEmail()).size() > 0) {
        	//get all courses for the student
            List<Course> courseList = studentDAO.getStudentCourses(withAuth.getsEmail());
            courseList.forEach(Course -> System.out.println(Course.getcName() + " with " + Course.getcInstructorName()));
            mainMenu(withAuth);
        }  else  {
        	//student has no courses
            System.out.println("You are not registered to any courses.");
            mainMenu(withAuth);
        }
    }
 
}
