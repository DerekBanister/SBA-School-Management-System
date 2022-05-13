package jpa.entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "Student")
@NamedQueries({
        @NamedQuery(name="validateStudent", query="FROM Student s WHERE s.sEmail = :sEmail"),
        @NamedQuery(name="getStudentCourses", query="FROM Student s LEFT JOIN FETCH s.sCourses WHERE s.sEmail = :sEmail"),
})
public class Student {

    @Id
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String sEmail;
    @Column(name = "name", length = 50, nullable = false)
    private String sName;
    @Column(name = "password", length = 50, nullable = false )
    private String sPass;

    @ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    private List<Course> sCourses;

    public Student(){
        sEmail = "";
        sName = "";
        sPass = "";
        sCourses = new ArrayList<>();
    }

    public Student(String email, String name, String password, List<Course> sCourses) {
        this.sEmail = email;
        this.sName = name;
        this.sPass = password;
        this.sCourses = sCourses;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }
}