package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.DeptDTO;
import com.biz.grade.persistence.domain.StudentDTO;

public abstract class DeptDao {

	protected Connection dbConn = null;
	public DeptDao() {
		this.dbConn = DBConnection.getDBConnection();
	}
	
	// 조건없이 모든 데이터를 조회하는 method
	// List<> 형태의 데이터를 return
	public abstract List<DeptDTO> selectAll();
	
	// PK를 조건으로 데이터를 조회하는 method
	// PK로 조회한다는 것은 출력되는 데이터가 1개 Record다
	// VO(DTO) 형태의 데이터를 return
	public abstract DeptDTO findById(String d_num);
	
	// 학과이름으로 조회해서 결과를 return method
	public abstract List<DeptDTO> findByName(String d_name); // 호환성을 위해 PK를 제외한 나머지는 List로 return하는게 좋음
	
	// DTO에 학과 데이터를 저장하여 method에 주입한 후
	// insert를 수행
	public abstract int insert(DeptDTO deptDTO);
	public abstract int update(DeptDTO deptDTO);
	
	// 매개변수로 일반적으로 findById() method의
	// 매개변수와 형, 값이 같다.
	public abstract int delete(String d_num); // PK로 삭제하기 때문에 findById와 같은 type
	

}
