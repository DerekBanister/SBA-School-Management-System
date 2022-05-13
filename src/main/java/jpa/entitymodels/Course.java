package jpa.entitymodels;

import javax.persistence.*;

@Entity
@Table(name= "Course")
public class Course {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Integer cId;
    @Column(name = "name", length = 50, nullable = false)
    private String cName;
    @Column(name = "instructor", length = 50, nullable = false)
    private String cInstructorName;

    public Course() {
        this.cId = 0;
        this.cName = "";
        this.cInstructorName = "";
    }

    public Course(Integer id, String name, String instructor) {
        this.cId = id;
        this.cName = name;
        this.cInstructorName = instructor;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }
}