package com.biz.rent.dao;

import java.util.List;

import com.biz.rent.persistence.RentDTO;

public interface RentDao {

	public List<RentDTO> selectAll();
	public List<RentDTO> selectNoList();
	public RentDTO findById(int rent_seq);
	public RentDTO findByBCode(String rent_bcode);
	public int insert(RentDTO rentDTO);
	public int update(RentDTO rentDTO);
	public int delete(int rent_seq);
	
}
