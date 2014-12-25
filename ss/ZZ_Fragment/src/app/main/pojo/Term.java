package app.main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Term entity. @author MyEclipse Persistence Tools
 */

public class Term  implements java.io.Serializable {


    // Fields    

     private Integer termId;
     private String termName;
     private Set scores = new HashSet(0);


    // Constructors

    /** default constructor */
    public Term() {
    }

	/** minimal constructor */
    public Term(Integer termId) {
        this.termId = termId;
    }
    
    /** full constructor */
    public Term(Integer termId, String termName, Set scores) {
        this.termId = termId;
        this.termName = termName;
        this.scores = scores;
    }

   
    // Property accessors

    public Integer getTermId() {
        return this.termId;
    }
    
    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return this.termName;
    }
    
    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Set getScores() {
        return this.scores;
    }
    
    public void setScores(Set scores) {
        this.scores = scores;
    }
   








}