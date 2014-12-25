package app.main.pojo;

import java.io.Serializable;

public class Position implements Serializable {
private double clatitude;//Î³¶È
private double longtitude;//¾­¶È
public double getClatitude() {
	return clatitude;
}
public void setClatitude(double clatitude) {
	this.clatitude = clatitude;
}
public double getLongtitude() {
	return longtitude;
}
public void setLongtitude(double longtitude) {
	this.longtitude = longtitude;
}


public Position() {
	super();
}
public Position(double clatitude, double longtitude) {
	super();
	this.clatitude = clatitude;
	this.longtitude = longtitude;
}

}
