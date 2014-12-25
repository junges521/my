package app.main.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Students entity. @author MyEclipse Persistence Tools
 */

public class Students  implements java.io.Serializable {


    // Fields    

     private Integer studentId;
     private Classes classes;
     private String studentName;
     private String studentBirth;
     private String studentSex;
     private String studentQq;
     private String studentPwd;
     private Set scores = new HashSet(0);
     private Set parentses = new HashSet(0);
     private Set scores_1 = new HashSet(0);
     private Set logs = new HashSet(0);
     private Set parentses_1 = new HashSet(0);
     private Set logs_1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public Students() {
    }

	/** minimal constructor */
    public Students(Integer studentId, Classes classes, String studentName, String studentBirth, String studentSex, String studentPwd) {
        this.studentId = studentId;
        this.classes = classes;
        this.studentName = studentName;
        this.studentBirth = studentBirth;
        this.studentSex = studentSex;
        this.studentPwd = studentPwd;
    }
    
    /** full constructor */
    public Students(Integer studentId, Classes classes, String studentName, String studentBirth, String studentSex, String studentQq, String studentPwd, Set scores, Set parentses, Set scores_1, Set logs, Set parentses_1, Set logs_1) {
        this.studentId = studentId;
        this.classes = classes;
        this.studentName = studentName;
        this.studentBirth = studentBirth;
        this.studentSex = studentSex;
        this.studentQq = studentQq;
        this.studentPwd = studentPwd;
        this.scores = scores;
        this.parentses = parentses;
        this.scores_1 = scores_1;
        this.logs = logs;
        this.parentses_1 = parentses_1;
        this.logs_1 = logs_1;
    }

   
    // Property accessors

    public Integer getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Classes getClasses() {
        return this.classes;
    }
    
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getStudentName() {
        return this.studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentBirth() {
        return this.studentBirth;
    }
    
    public void setStudentBirth(String studentBirth) {
        this.studentBirth = studentBirth;
    }

    public String getStudentSex() {
        return this.studentSex;
    }
    
    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentQq() {
        return this.studentQq;
    }
    
    public void setStudentQq(String studentQq) {
        this.studentQq = studentQq;
    }

    public String getStudentPwd() {
        return this.studentPwd;
    }
    
    public void setStudentPwd(String studentPwd) {
        this.studentPwd = studentPwd;
    }

    public Set getScores() {
        return this.scores;
    }
    
    public void setScores(Set scores) {
        this.scores = scores;
    }

    public Set getParentses() {
        return this.parentses;
    }
    
    public void setParentses(Set parentses) {
        this.parentses = parentses;
    }

    public Set getScores_1() {
        return this.scores_1;
    }
    
    public void setScores_1(Set scores_1) {
        this.scores_1 = scores_1;
    }

    public Set getLogs() {
        return this.logs;
    }
    
    public void setLogs(Set logs) {
        this.logs = logs;
    }

    public Set getParentses_1() {
        return this.parentses_1;
    }
    
    public void setParentses_1(Set parentses_1) {
        this.parentses_1 = parentses_1;
    }

    public Set getLogs_1() {
        return this.logs_1;
    }
    
    public void setLogs_1(Set logs_1) {
        this.logs_1 = logs_1;
    }

	@Override
	public String toString() {
		return "{\'classes\':" + classes + ", \'studentBirth\':\'"
				+ studentBirth + "\', \'studentId\':\'" + studentId
				+ "\', \'studentName\':\'" + studentName
				+ "\', \'studentPwd\':\'" + studentPwd + "\', \'studentQq\':\'"
				+ studentQq + "\', \'studentSex\':\'" + studentSex + "\'}";
	}
   








}