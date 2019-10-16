package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

/*
 * 추상클래스 선언
 * 추상클래스
 * 		일부는 구현된 method
 * 		일부는 형태만 갖는 method
 */
public abstract class StudentService {

	protected Connection dbConn = null;
	
	/*
	 * dbConn을 설정하여 DBMS에 접속할 수 있는 통로 설정
	 */
	// 인터페이스 효과도 내면서 상속도 받기 위해 abstract
	protected void dbConnection() {
		try {
			Class.forName(DBContract.DbConn.JdbcDriver);
			dbConn = DriverManager.getConnection(
					DBContract.DbConn.URL, 
					DBContract.DbConn.USER, 
					DBContract.DbConn.PASSWORD
			);
			System.out.println("DBConnection OK!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC 드라이버를 찾지 못함!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DBMS 접속 오류!!");
		}
	}
	
	// CRUD
	public abstract int insert(StudentDTO studentDTO) ;
	
	// 모든 레코드
	// 1개 이상의 레코드
	public abstract List<StudentDTO> selectAll(); //1개 이상이라 데이터를 가져오면 무조건 리스트
	
	// id 값을 매개변수로 받아서
	// 1개의레코드만 조회하는 method
	public abstract StudentDTO findById(String num); //PK로 1개만 가져오기 때문에 ScoreVO
	
	public abstract List<StudentDTO> findByName(String name); //동명이인이 있으면 여러개를 가져옴
	// 다수의 값이 올 수 있으므로 , 따라서 ScoreDTO가 아닌 List로 return
	
	// 과목별로 점수리스트를
	public abstract List<StudentDTO> findBySubject(String subject);
	
	public abstract int update(StudentDTO studentDTO) ; //VO로 가져오면서 id 값 가져오기 때문에 int 
	
	public abstract int delete(String num) ; // ※ 1개의 데이터만 수정하도록 PK로

	
	
	
	
}
