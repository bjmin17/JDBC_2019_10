package com.biz.addr.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.persistence.AddrDTO;

public class AddrDaoImp extends AddrDao {

	private AddrDTO rst_2_DTO(ResultSet rst) throws SQLException {
		AddrDTO dto = AddrDTO.builder()
		.id(rst.getLong("ID"))
		.name(rst.getString("NAME"))
		.tel(rst.getString("TEL"))
		.addr(rst.getString("ADDR"))
		.chain(rst.getString("CHAIN"))
		.build();
		
		
		return dto;
		
	}
	
	
	@Override
	public List<AddrDTO> selectAll() {


		PreparedStatement pStr = null;
		String sql = " SELECT ID,"
				+ " NAME, "
				+ " TEL, "
				+ " ADDR, "
				+ " CHAIN "
				+ " FROM tbl_addr ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			
			ResultSet rst = pStr.executeQuery();
			
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			
			while(rst.next()) {
				
				AddrDTO dto = AddrDTO.builder()
						.id(rst.getLong("ID"))
						.name(rst.getString("NAME"))
						.tel(rst.getString("TEL"))
						.addr(rst.getString("ADDR"))
						.chain(rst.getString("CHAIN"))
						.build();
				
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public AddrDTO findById(long id) {

		PreparedStatement pStr = null;
		String sql = " SELECT ID,"
				+ " NAME, "
				+ " TEL, "
				+ " ADDR, "
				+ " CHAIN "
				+ " FROM tbl_addr ";
		sql += " WHERE ID = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			ResultSet rst = pStr.executeQuery();
			
			AddrDTO dto = null;
			if(rst.next()) {
				dto = this.rst_2_DTO(rst);
			}
			rst.close();
			pStr.close();
			return dto;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<AddrDTO> findByName(String name) {

		PreparedStatement pStr = null;
		String sql = " SELECT ID,"
				+ " NAME, "
				+ " TEL, "
				+ " ADDR, "
				+ " CHAIN "
				+ " FROM tbl_addr ";
		sql += " WHERE NAME LIKE '%' || ? || '%' ";

		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<AddrDTO> findByTel(String tel) {
		
		PreparedStatement pStr = null;
		String sql = " SELECT ID,"
				+ " NAME, "
				+ " TEL, "
				+ " ADDR, "
				+ " CHAIN "
				+ " FROM tbl_addr ";
		sql += " WHERE TEL = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, tel);
			ResultSet rst = pStr.executeQuery();
			
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

		return null;
	}

	@Override
	public List<AddrDTO> findByChain(String chain) {
		PreparedStatement pStr = null;
		String sql = " SELECT ID,"
				+ " NAME, "
				+ " TEL, "
				+ " ADDR, "
				+ " CHAIN "
				+ " FROM tbl_addr ";
		sql += " WHERE CHAIN = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, chain);
			ResultSet rst = pStr.executeQuery();
			
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public int insert(AddrDTO addrDTO) {

		PreparedStatement pStr = null;
		String sql = " INSERT INTO tbl_addr ( ";
		sql += " ID,";
		sql += " NAME,";
		sql += " TEL,";
		sql += " ADDR,";
		sql += " CHAIN) ";
		sql += " VALUES( "
				+ " SEQ_ADDR.NEXTVAL,"
				+ " ?,"
				+ " ?,"
				+ " ?,"
				+ " ?) ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, addrDTO.getName());
			pStr.setString(2, addrDTO.getTel());
			pStr.setString(3, addrDTO.getAddr());
			pStr.setString(4, addrDTO.getChain());
			
			int ret = pStr.executeUpdate();
			pStr.close();
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int delete(long id) {

		PreparedStatement pStr = null;
		String sql = " DELETE FROM tbl_addr ";
		sql += " WHERE id = ? ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			
			int ret = pStr.executeUpdate();
			pStr.close();
			
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int update(AddrDTO addrDTO) {

		PreparedStatement pStr = null;
		String sql = " UPDATE INTO tbl_addr SET ";
//		sql += " ID = ?,";
		sql += " NAME = ?,";
		sql += " TEL = ?,";
		sql += " ADDR = ?,";
		sql += " CHAIN = ? ";
		sql += " WHERE ID = ? ";
		
		try {
			pStr = dbConn.prepareCall(sql);
			pStr.setString(1, addrDTO.getName());
			pStr.setString(2, addrDTO.getTel());
			pStr.setString(3, addrDTO.getAddr());
			pStr.setString(4, addrDTO.getChain());
			pStr.setLong(5, addrDTO.getId());
			
			int ret = pStr.executeUpdate();
			
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}


}
