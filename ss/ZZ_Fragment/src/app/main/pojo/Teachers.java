package app.main.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Teachers entity. @author MyEclipse Persistence Tools
 */

public class Teachers  implements java.io.Serializable {


    // Fields    

     private Integer teacherId;
     private Classes classes;
     private Courses courses;
     private String teacherName;
     private String teacherPwd;
     private String teacherSex;
     private Integer teacherTel;
     private String teacherSelf;
     private String teacherAddress;
     private Date teacherWorktime;
     private String teacherPosition;
     private Set taskses = new HashSet(0);
     private Set courseses = new HashSet(0);
     private Set syllabuses = new HashSet(0);


    // Constructors

    /** default constructor */
    public Teachers() {
    }

	/** minimal constructor */
    public Teachers(Integer teacherId, Courses courses, String teacherName, String teacherPwd, String teacherSex, Integer teacherTel, String teacherPosition) {
        this.teacherId = teacherId;
        this.courses = courses;
        this.teacherName = teacherName;
        this.teacherPwd = teacherPwd;
        this.teacherSex = teacherSex;
        this.teacherTel = teacherTel;
        this.teacherPosition = teacherPosition;
    }
    
    /** full constructor */
    public Teachers(Integer teacherId, Classes classes, Courses courses, String teacherName, String teacherPwd, String teacherSex, Integer teacherTel, String teacherSelf, String teacherAddress, Date teacherWorktime, String teacherPosition, Set taskses, Set courseses, Set syllabuses) {
        this.teacherId = teacherId;
        this.classes = classes;
        this.courses = courses;
        this.teacherName = teacherName;
        this.teacherPwd = teacherPwd;
        this.teacherSex = teacherSex;
        this.teacherTel = teacherTel;
        this.teacherSelf = teacherSelf;
        this.teacherAddress = teacherAddress;
        this.teacherWorktime = teacherWorktime;
        this.teacherPosition = teacherPosition;
        this.taskses = taskses;
        this.courseses = courseses;
        this.syllabuses = syllabuses;
    }

   
    // Property accessors

    public Integer getTeacherId() {
        return this.teacherId;
    }
    
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Classes getClasses() {
        return this.classes;
    }
    
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Courses getCourses() {
        return this.courses;
    }
    
    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public String getTeacherName() {
        return this.teacherName;
    }
    
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPwd() {
        return this.teacherPwd;
    }
    
    public void setTeacherPwd(String teacherPwd) {
        this.teacherPwd = teacherPwd;
    }

    public String getTeacherSex() {
        return this.teacherSex;
    }
    
    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public Integer getTeacherTel() {
        return this.teacherTel;
    }
    
    public void setTeacherTel(Integer teacherTel) {
        this.teacherTel = teacherTel;
    }

    public String getTeacherSelf() {
        return this.teacherSelf;
    }
    
    public void setTeacherSelf(String teacherSelf) {
        this.teacherSelf = teacherSelf;
    }

    public String getTeacherAddress() {
        return this.teacherAddress;
    }
    
    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public Date getTeacherWorktime() {
        return this.teacherWorktime;
    }
    
    public void setTeacherWorktime(Date teacherWorktime) {
        this.teacherWorktime = teacherWorktime;
    }

    public String getTeacherPosition() {
        return this.teacherPosition;
    }
    
    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
    }

    public Set getTaskses() {
        return this.taskses;
    }
    
    public void setTaskses(Set taskses) {
        this.taskses = taskses;
    }

    public Set getCourseses() {
        return this.courseses;
    }
    
    public void setCourseses(Set courseses) {
        this.courseses = courseses;
    }

    public Set getSyllabuses() {
        return this.syllabuses;
    }
    
    public void setSyllabuses(Set syllabuses) {
        this.syllabuses = syllabuses;
    }

	@Override
	public String toString() {
		return "{\'teacherAddress\':\'" + teacherAddress
				+ "\', \'teacherId\':\'" + teacherId + "\', \'teacherName\':\'"
				+ teacherName + "\', \'teacherPosition\':\'" + teacherPosition
				+ "\', \'teacherPwd\':\'" + teacherPwd
				+ "\', \'teacherSelf\':\'" + teacherSelf
				+ "\', \'teacherSex\':\'" + teacherSex
				+ "\', \'teacherTel\':\'" + teacherTel
				+ "\', \'teacherWorktime\':\'" + teacherWorktime + "\'}";
	}


   








}