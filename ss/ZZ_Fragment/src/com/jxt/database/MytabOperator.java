package com.jxt.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import app.main.pojo.Parents;

public class MytabOperator {
	private SQLiteDatabase db = null;
	public static final String t_parents="parents";
	public MytabOperator(SQLiteDatabase db) {
		super();
		this.db = db;
	};

	public void insert(Parents p) {
		String insertP="insert into(parents_id," +
				"parents_name,parents_tel," +
				"parents_childrenid,parents_job," +
				"parents_pwd,parents_address)"+t_parents+"values("
				+p.getParentsId()+","+p.getParentsName()
				+","+p.getParentsTel()+","
				+p.getStudents().getStudentId()+","+
				p.getParentsJob()+","+p.getParentsPwd()+","+p.getParentsAddress()+");";
		db.execSQL(insertP);
	}







}
