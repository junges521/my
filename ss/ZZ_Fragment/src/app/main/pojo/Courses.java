package app.main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Courses entity. @author MyEclipse Persistence Tools
 */

public class Courses  implements java.io.Serializable {


    // Fields    

     private Integer courseId;
     private Grade grade;
     private Teachers teachers;
     private String courseName;
     private Integer courseMaxscore;
     private Set syllabuses = new HashSet(0);
     private Set teacherses = new HashSet(0);
     private Set teacherses_1 = new HashSet(0);
     private Set scores = new HashSet(0);
     private Set scores_1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public Courses() {
    }

	/** minimal constructor */
    public Courses(Integer courseId, String courseName, Integer courseMaxscore) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseMaxscore = courseMaxscore;
    }
    
    /** full constructor */
    public Courses(Integer courseId, Grade grade, Teachers teachers, String courseName, Integer courseMaxscore, Set syllabuses, Set teacherses, Set teacherses_1, Set scores, Set scores_1) {
        this.courseId = courseId;
        this.grade = grade;
        this.teachers = teachers;
        this.courseName = courseName;
        this.courseMaxscore = courseMaxscore;
        this.syllabuses = syllabuses;
        this.teacherses = teacherses;
        this.teacherses_1 = teacherses_1;
        this.scores = scores;
        this.scores_1 = scores_1;
    }

   
    // Property accessors

    public Integer getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Grade getGrade() {
        return this.grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Teachers getTeachers() {
        return this.teachers;
    }
    
    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseMaxscore() {
        return this.courseMaxscore;
    }
    
    public void setCourseMaxscore(Integer courseMaxscore) {
        this.courseMaxscore = courseMaxscore;
    }

    public Set getSyllabuses() {
        return this.syllabuses;
    }
    
    public void setSyllabuses(Set syllabuses) {
        this.syllabuses = syllabuses;
    }

    public Set getTeacherses() {
        return this.teacherses;
    }
    
    public void setTeacherses(Set teacherses) {
        this.teacherses = teacherses;
    }

    public Set getTeacherses_1() {
        return this.teacherses_1;
    }
    
    public void setTeacherses_1(Set teacherses_1) {
        this.teacherses_1 = teacherses_1;
    }

    public Set getScores() {
        return this.scores;
    }
    
    public void setScores(Set scores) {
        this.scores = scores;
    }

    public Set getScores_1() {
        return this.scores_1;
    }
    
    public void setScores_1(Set scores_1) {
        this.scores_1 = scores_1;
    }
   








}