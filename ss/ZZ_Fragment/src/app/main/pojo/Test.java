package app.main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Test entity. @author MyEclipse Persistence Tools
 */

public class Test  implements java.io.Serializable {


    // Fields    

     private Integer testId;
     private String testName;
     private Set scores = new HashSet(0);
     private Set scores_1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public Test() {
    }

	/** minimal constructor */
    public Test(String testName) {
        this.testName = testName;
    }
    
    /** full constructor */
    public Test(String testName, Set scores, Set scores_1) {
        this.testName = testName;
        this.scores = scores;
        this.scores_1 = scores_1;
    }

   
    // Property accessors

    public Integer getTestId() {
        return this.testId;
    }
    
    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return this.testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
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