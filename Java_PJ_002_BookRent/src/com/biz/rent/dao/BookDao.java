package com.biz.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.rent.persistence.BookDTO;

public interface BookDao {

	public String getMaxBCode();
	public List<BookDTO> selectAll();
	public BookDTO findById(String b_code);
	public List<BookDTO> findByName(String b_name);
	public List<BookDTO> findByNameSearch(String b_name);
	
	public List<BookDTO> findByAuth(String b_auther);
	public List<BookDTO> findByNameAndAuth(@Param("b_name") String b_name,@Param("b_auther") String b_auther);
	
	public int insert(BookDTO bookDTO);
	public int update(BookDTO bookDTO);
	public int delete(String b_code);
	
}
