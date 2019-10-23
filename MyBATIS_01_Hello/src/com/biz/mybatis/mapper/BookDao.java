package com.biz.mybatis.mapper;

import java.util.List;

import com.biz.mybatis.persistence.BookDTO;

public interface BookDao {

	public List<BookDTO> selectAll();
	
	public BookDTO findById(String b_code);
	public List<BookDTO> findByName(String b_name);
	public int insert(BookDTO bookDTO);
	
	public int update(BookDTO bookDTO);
	public int delete(String b_code);
//	public int delete(BookDTO b_code); -- mybatis에서는 delete가 id값으로 PK이라서 이 부분에서는 다형성을 지원하지 않는다.
	
}
