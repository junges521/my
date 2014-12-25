package app.main.pojo;



/**
 * Score entity. @author MyEclipse Persistence Tools
 */

public class Score  implements java.io.Serializable {


    // Fields    


     private Term term;
     private Courses courses;
     private Test test;
     private Students students;
     private Integer scoreResult;
     private Integer scoreTermid;


    // Constructors

    /** default constructor */
    public Score() {
    }

	/** minimal constructor */
    public Score( Courses courses, Students students) {

        this.courses = courses;
        this.students = students;
    }
    
    /** full constructor */
    public Score( Term term, Courses courses, Test test, Students students, Integer scoreResult, Integer scoreTermid) {
  
        this.term = term;
        this.courses = courses;
        this.test = test;
        this.students = students;
        this.scoreResult = scoreResult;
        this.scoreTermid = scoreTermid;
    }

   


    public Term getTerm() {
        return this.term;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }

    public Courses getCourses() {
        return this.courses;
    }
    
    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }

    public Students getStudents() {
        return this.students;
    }
    
    public void setStudents(Students students) {
        this.students = students;
    }

    public Integer getScoreResult() {
        return this.scoreResult;
    }
    
    public void setScoreResult(Integer scoreResult) {
        this.scoreResult = scoreResult;
    }

    public Integer getScoreTermid() {
        return this.scoreTermid;
    }
    
    public void setScoreTermid(Integer scoreTermid) {
        this.scoreTermid = scoreTermid;
    }

	@Override
	public String toString() {
		return "{'term':'" + term + "', 'courses':'" + courses + "', 'test':'"
				+ test + "', 'students':'" + students + "', 'scoreResult':'"
				+ scoreResult + "', 'scoreTermid':'" + scoreTermid + "'}";
	}
   








}