package com.callor.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.callor.persistence.MemoDTO;

public interface MemoDao {

	public List<MemoDTO> selectAll();
	public MemoDTO findById(int id);
	public List<MemoDTO> findBySubject(String m_subject);
	public List<MemoDTO> findByDate(@Param("m_sdate") String m_sdate, @Param("m_edate") String m_edate);
	public List<MemoDTO> findByText(String m_text);
	
	
	public int insert(MemoDTO memoDTO);
	public int update(MemoDTO memoDTO);
	public int delete(int id);
}
