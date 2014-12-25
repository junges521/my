package app.main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category  implements java.io.Serializable {


    // Fields    

     private Integer categoryId;
     private String categoryName;
     private Set newses = new HashSet(0);


    // Constructors

    /** default constructor */
    public Category() {
    }

	/** minimal constructor */
    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    /** full constructor */
    public Category(Integer categoryId, String categoryName, Set newses) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.newses = newses;
    }

   
    // Property accessors

    public Integer getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set getNewses() {
        return this.newses;
    }
    
    public void setNewses(Set newses) {
        this.newses = newses;
    }

	@Override
	public String toString() {
		return "{\'categoryId\':\'" + categoryId + "\', \'categoryName\':\'"
				+ categoryName + "\', \'newses\':" + newses + "}";
	}
   








}