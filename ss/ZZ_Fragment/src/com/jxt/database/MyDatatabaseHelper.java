package com.jxt.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASENAME="jxtp.db";
	private static final int DATABASEVERSION=1;
	private static final String t_parents="parents";
	private static final String t_students="students";
	private static final String t_classes="classes";
	private static final String t_teachers="teachers";
	private static final String t_test="test";
	private static final String t_grade="grade";
	

	public MyDatatabaseHelper(Context context) {
		super(context, DATABASENAME, null, DATABASEVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		String parsql="create table IF NOT EXISTS " +
				"parents(parents_id INTEGER primary key," +
				"parents_name VARCHAR(50) NOT NULL," +
				"parents_tel VARCHAR(20)," +
				"parents_childrenid INTEGER," +
				"parents_job VARCHAR(20)," +
				"parents_pwd VARCHAR(50) NOT NULL," +
				"parents_address VARCHAR(50))";//创建家长表
		String stuSql="CREATE TABLE IF NOT EXISTS  `students` (`student_id` int(11) NOT NULL," +
				"`student_name` varchar(255) NOT NULL," +
				"`student_birth` date NOT NULL," +
				"`student_sex` varchar(255) NOT NULL DEFAULT '男', " +
				"`student_classid` int(11) NOT NULL," +
				"`student_qq` varchar(20) DEFAULT NULL," +
				"`student_pwd` varchar(255) NOT NULL, " +
				"PRIMARY KEY (`student_id`))";
		String teacherSql="CREATE TABLE IF NOT EXISTS `teachers` (`teacher_id` int(10) NOT NULL,`teacher_Name` varchar(20) NOT NULL," +
				"`teacher_pwd` varchar(20) NOT NULL DEFAULT '123456'," +
				"`teacher_courseid` int(20) NOT NULL," +
				" `teacher_sex` varchar(20) NOT NULL DEFAULT '男'," +
				"`teacher_tel` int(20) NOT NULL," +
				"`teacher_classid` int(10) DEFAULT NULL," +
				"`teacher_self` varchar(200) DEFAULT NULL," +
				"`teacher_address` varchar(255) DEFAULT NULL," +
				"`teacher_worktime` date DEFAULT NULL,`" +
				"teacher_position` varchar(255) NOT NULL," +
				"PRIMARY KEY (`teacher_id`))";
		String classsql="CREATE TABLE IF NOT EXISTS `classes` (" +
				"`class_id` int(11)," +
				"`class_Name` varchar(255) DEFAULT NULL," +
				"`class_gradeId` int(255) DEFAULT NULL," +
				"`class_number` varchar(255) DEFAULT NULL," +
				"`class_total` int(11) DEFAULT NULL," +
				"`class_gradename` int(11) DEFAULT NULL," +
				"`class_url` varchar(255) DEFAULT NULL," +
				"PRIMARY KEY (`class_id`))";
		String testsql="CREATE TABLE IF NOT EXISTS `test` (" +
				"`test_id` int(11) NOT NULL," +
				"`test_name` varchar(255) NOT NULL," +
				"PRIMARY KEY (`test_id`))";
		String gradeSql="CREATE TABLE IF NOT EXISTS `grade` (`grade_id` int(11) NOT NULL," +
				"`grade_name` varchar(255) NOT NULL," +
				"`grade_max` int(3) NOT NULL," +
				"PRIMARY KEY (`grade_id`))";
		
		arg0.execSQL(parsql);
		arg0.execSQL(stuSql);
		arg0.execSQL(teacherSql);
		arg0.execSQL(gradeSql);
		arg0.execSQL(testsql);
		arg0.execSQL(classsql);
		
		}

	/*
	 * 当数据库进行升级会调用此方法，一班可以再此方法中将一些数据删除
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String drop_p="drop table if exists"+t_parents;
		String drop_t="drop table if exists"+t_test;
		String drop_s="drop table if exists"+t_students;
		db.execSQL(drop_p);
		db.execSQL(drop_s);
		db.execSQL(drop_t);
	}

}
