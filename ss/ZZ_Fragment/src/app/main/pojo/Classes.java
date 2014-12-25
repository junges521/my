package app.main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Classes entity. @author MyEclipse Persistence Tools
 */

public class Classes  implements java.io.Serializable {


    // Fields    

     private Integer classId;
     private Grade grade;
     private String className;
     private String classNumber;
     private Integer classTotal;
     private Integer classGradename;
     private String classUrl;
     private Set teacherses = new HashSet(0);
     private Set taskses = new HashSet(0);
     private Set syllabuses = new HashSet(0);
     private Set studentses = new HashSet(0);
     private Set teacherses_1 = new HashSet(0);
     private Set studentses_1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public Classes() {
    }

	/** minimal constructor */
    public Classes(Integer classId) {
        this.classId = classId;
    }
    
    /** full constructor */
    public Classes(Integer classId, Grade grade, String className, String classNumber, Integer classTotal, Integer classGradename, String classUrl, Set teacherses, Set taskses, Set syllabuses, Set studentses, Set teacherses_1, Set studentses_1) {
        this.classId = classId;
        this.grade = grade;
        this.className = className;
        this.classNumber = classNumber;
        this.classTotal = classTotal;
        this.classGradename = classGradename;
        this.classUrl = classUrl;
        this.teacherses = teacherses;
        this.taskses = taskses;
        this.syllabuses = syllabuses;
        this.studentses = studentses;
        this.teacherses_1 = teacherses_1;
        this.studentses_1 = studentses_1;
    }

   
    // Property accessors

    public Integer getClassId() {
        return this.classId;
    }
    
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Grade getGrade() {
        return this.grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNumber() {
        return this.classNumber;
    }
    
    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getClassTotal() {
        return this.classTotal;
    }
    
    public void setClassTotal(Integer classTotal) {
        this.classTotal = classTotal;
    }

    public Integer getClassGradename() {
        return this.classGradename;
    }
    
    public void setClassGradename(Integer classGradename) {
        this.classGradename = classGradename;
    }

    public String getClassUrl() {
        return this.classUrl;
    }
    
    public void setClassUrl(String classUrl) {
        this.classUrl = classUrl;
    }

    public Set getTeacherses() {
        return this.teacherses;
    }
    
    public void setTeacherses(Set teacherses) {
        this.teacherses = teacherses;
    }

    public Set getTaskses() {
        return this.taskses;
    }
    
    public void setTaskses(Set taskses) {
        this.taskses = taskses;
    }

    public Set getSyllabuses() {
        return this.syllabuses;
    }
    
    public void setSyllabuses(Set syllabuses) {
        this.syllabuses = syllabuses;
    }

    public Set getStudentses() {
        return this.studentses;
    }
    
    public void setStudentses(Set studentses) {
        this.studentses = studentses;
    }

    public Set getTeacherses_1() {
        return this.teacherses_1;
    }
    
    public void setTeacherses_1(Set teacherses_1) {
        this.teacherses_1 = teacherses_1;
    }

    public Set getStudentses_1() {
        return this.studentses_1;
    }
    
    public void setStudentses_1(Set studentses_1) {
        this.studentses_1 = studentses_1;
    }

	@Override
	public String toString() {
		return "{\'classGradename\':\'" + classGradename + "\', \'classId\':\'"
				+ classId + "\', \'className\':\'" + className
				+ "\', \'classNumber\':\'" + classNumber
				+ "\', \'classTotal\':\'" + classTotal + "\', \'classUrl\':\'"
				+ classUrl + "\', \'grade\':" + grade +"}";
	}
}