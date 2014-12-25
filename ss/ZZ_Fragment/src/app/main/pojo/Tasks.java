package app.main.pojo;

import java.util.Date;


/**
 * Tasks entity. @author MyEclipse Persistence Tools
 */

public class Tasks  implements java.io.Serializable {


    // Fields    

     private Integer taskId;
     private Teachers teachers;
     private Classes classes;
     private String taskName;
     private Date taskDate;
     private Date taskUp;


    // Constructors

    /** default constructor */
    public Tasks() {
    }

	/** minimal constructor */
    public Tasks(Integer taskId) {
        this.taskId = taskId;
    }
    
    /** full constructor */
    public Tasks(Integer taskId, Teachers teachers, Classes classes, String taskName, Date taskDate, Date taskUp) {
        this.taskId = taskId;
        this.teachers = teachers;
        this.classes = classes;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskUp = taskUp;
    }

   
    // Property accessors

    public Integer getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Teachers getTeachers() {
        return this.teachers;
    }
    
    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public Classes getClasses() {
        return this.classes;
    }
    
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getTaskName() {
        return this.taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskDate() {
        return this.taskDate;
    }
    
    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getTaskUp() {
        return this.taskUp;
    }
    
    public void setTaskUp(Date taskUp) {
        this.taskUp = taskUp;
    }

	@Override
	public String toString() {
		return "{\'taskDate\':\'" + taskDate + "\', \'taskId\':\'" + taskId
				+ "\', \'taskName\':\'" + taskName + "\', \'taskUp\':\'"
				+ taskUp + "\', \'teachers\':" + teachers + "}";
	}


   








}