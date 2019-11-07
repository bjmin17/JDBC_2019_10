package com.biz.cbt.dao;

import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public interface CbtDao {

	public String getMaxPCode();
	public List<CbtDTO> selectAll();
	public CbtDTO findById(String cb_pcode);
	
	public int insert(CbtDTO cbtDTO);
	public int update(CbtDTO cbtDTO);
	public int delete(String cb_pcode);
}
