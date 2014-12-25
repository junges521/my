package app.main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade  implements java.io.Serializable {


    // Fields    

     private Integer gradeId;
     private String gradeName;
     private Integer gradeMax;
     private Set courseses = new HashSet(0);
     private Set classeses = new HashSet(0);
     private Set classeses_1 = new HashSet(0);
     private Set courseses_1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public Grade() {
    }

	/** minimal constructor */
    public Grade(String gradeName, Integer gradeMax) {
        this.gradeName = gradeName;
        this.gradeMax = gradeMax;
    }
    
    /** full constructor */
    public Grade(String gradeName, Integer gradeMax, Set courseses, Set classeses, Set classeses_1, Set courseses_1) {
        this.gradeName = gradeName;
        this.gradeMax = gradeMax;
        this.courseses = courseses;
        this.classeses = classeses;
        this.classeses_1 = classeses_1;
        this.courseses_1 = courseses_1;
    }

   
    // Property accessors

    public Integer getGradeId() {
        return this.gradeId;
    }
    
    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return this.gradeName;
    }
    
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getGradeMax() {
        return this.gradeMax;
    }
    
    public void setGradeMax(Integer gradeMax) {
        this.gradeMax = gradeMax;
    }

    public Set getCourseses() {
        return this.courseses;
    }
    
    public void setCourseses(Set courseses) {
        this.courseses = courseses;
    }

    public Set getClasseses() {
        return this.classeses;
    }
    
    public void setClasseses(Set classeses) {
        this.classeses = classeses;
    }

    public Set getClasseses_1() {
        return this.classeses_1;
    }
    
    public void setClasseses_1(Set classeses_1) {
        this.classeses_1 = classeses_1;
    }

    public Set getCourseses_1() {
        return this.courseses_1;
    }
    
    public void setCourseses_1(Set courseses_1) {
        this.courseses_1 = courseses_1;
    }

	@Override
	public String toString() {
		return "{\'gradeId\':\'" + gradeId + "\', \'gradeMax\':\'" + gradeMax
				+ "\', \'gradeName\':\'" + gradeName + "\'}";
	}
   








}