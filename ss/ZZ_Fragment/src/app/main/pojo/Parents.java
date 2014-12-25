package app.main.pojo;



/**
 * Parents entity. @author MyEclipse Persistence Tools
 */

public class Parents  implements java.io.Serializable {


    // Fields    

     private Integer parentsId;
     private Students students;
     private String parentsName;
     private String parentsTel;
     private String parentsJob;
     private String parentsPwd;
     private String parentsAddress;


    // Constructors

    /** default constructor */
    public Parents() {
    }

	/** minimal constructor */
    public Parents(Integer parentsId) {
        this.parentsId = parentsId;
    }
    
    /** full constructor */
    public Parents(Integer parentsId, Students students, String parentsName, String parentsTel, String parentsJob, String parentsPwd, String parentsAddress) {
        this.parentsId = parentsId;
        this.students = students;
        this.parentsName = parentsName;
        this.parentsTel = parentsTel;
        this.parentsJob = parentsJob;
        this.parentsPwd = parentsPwd;
        this.parentsAddress = parentsAddress;
    }

   
    // Property accessors

    public Integer getParentsId() {
        return this.parentsId;
    }
    
    public void setParentsId(Integer parentsId) {
        this.parentsId = parentsId;
    }

    public Students getStudents() {
        return this.students;
    }
    
    public void setStudents(Students students) {
        this.students = students;
    }

    public String getParentsName() {
        return this.parentsName;
    }
    
    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getParentsTel() {
        return this.parentsTel;
    }
    
    public void setParentsTel(String parentsTel) {
        this.parentsTel = parentsTel;
    }

    public String getParentsJob() {
        return this.parentsJob;
    }
    
    public void setParentsJob(String parentsJob) {
        this.parentsJob = parentsJob;
    }

    public String getParentsPwd() {
        return this.parentsPwd;
    }
    
    public void setParentsPwd(String parentsPwd) {
        this.parentsPwd = parentsPwd;
    }

    public String getParentsAddress() {
        return this.parentsAddress;
    }
    
    public void setParentsAddress(String parentsAddress) {
        this.parentsAddress = parentsAddress;
    }

	@Override
	public String toString() {
		return "{\'parentsAddress\':\'" + parentsAddress
				+ "\', \'parentsId\':\'" + parentsId + "\', \'parentsJob\':\'"
				+ parentsJob + "\', \'parentsName\':\'" + parentsName
				+ "\', \'parentsPwd\':\'" + parentsPwd
				+ "\', \'parentsTel\':\'" + parentsTel + "\', \'students\':"
				+ students + "}";
	}
   








}