package com.biz.addr.service;

import java.util.List;
import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrServiceV1 {

	AddrDao addrDao = null;
	Scanner scanner = null;
	public AddrServiceV1() {
		addrDao = new AddrDaoImp();
		scanner = new Scanner(System.in);
	}
	
	
	public void viewAddrList() {
		List<AddrDTO> addrList = addrDao.selectAll();
		
		this.viewList(addrList);

	}
	
	private void viewList(List<AddrDTO> addrList) {
		System.out.println("======================================");
		System.out.println("주소록 전체 리스트 V1");
		System.out.println("======================================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("--------------------------------------");
		for(AddrDTO dto : addrList) {
			System.out.printf("%d\t",dto.getId());
			System.out.printf("%s\t",dto.getName());
			System.out.printf("%s\t",dto.getTel());
			System.out.printf("%s\t",dto.getAddr());
			System.out.printf("%s\n",dto.getChain());
		}
		System.out.println("======================================");
	}
	private void viewList(AddrDTO dto) {
		System.out.println("======================================");
		System.out.println("주소록 전체 리스트 V1");
		System.out.println("======================================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("--------------------------------------");
		
			System.out.printf("%d\t",dto.getId());
			System.out.printf("%s\t",dto.getName());
			System.out.printf("%s\t",dto.getTel());
			System.out.printf("%s\t",dto.getAddr());
			System.out.printf("%s\n",dto.getChain());
		System.out.println("======================================");
	}
	
	public void findById(long id) {
		
		AddrDTO dto = addrDao.findById(id);
		
		if(dto == null) {
			System.out.println("찾는 주소록이 없음");
		}
		this.viewList(dto);
	}
	
	public void findByName(boolean bConti) {
		
		while(true) {
			if(this.findByName() != null) break;
		}
		
	}
	
	public String findByName() {
		
		System.out.println("======================================");
		System.out.print("주소록 이름검색 >> ");
		
		String strName = scanner.nextLine();
		
		if(strName.equals("-Q")) return "-Q";
		
		this.findByName(strName);
//		List<AddrDTO> addrList = addrDao.findByName(name);
//		if(addrList == null || addrList.size() < 1) {
//			System.out.println("찾는 도서명이 없음!!");
//		}
		return strName;
	}
	
	public boolean findByName(String strName) {
		List<AddrDTO> addrList 
			= addrDao.findByName(strName);
		if(addrList == null || addrList.size() < 1) {
			System.out.println("찾는 도서명이 없음!!");
			return false;
		}
		this.viewList(addrList);
		return true;
	}
	

	public void findByTel(boolean bConti) {
		
		while(true) {
			if(this.findByName() != null) break;
		}
		
	}
	
	public String findByTel() {
		
		System.out.println("======================================");
		System.out.print("주소록 전화번호 검색 >> ");
		
		
		String strTel = scanner.nextLine();
		
		if(strTel.equals("-Q")) return "-Q";
		
		this.findByTel(strTel);
//		List<AddrDTO> addrList = addrDao.findByName(name);
//		if(addrList == null || addrList.size() < 1) {
//			System.out.println("찾는 도서명이 없음!!");
//		}
		return strTel;
	}
	
	public boolean findByTel(String strTel) {
		List<AddrDTO> addrList 
			= addrDao.findByTel(strTel);
		if(addrList == null || addrList.size() < 1) {
			System.out.println("찾는 도서명이 없음!!");
			return false;
		}
		this.viewList(addrList);
		return true;
	}
	public void findByChain() {
		
		while(true) {
			System.out.println("======================================");
			System.out.print("주소록 관계 검색 >> ");
			String strChain = scanner.nextLine();
			if(strChain.equals("-Q")) break;
			
			List<AddrDTO> addrList = addrDao.findByChain(strChain); 
			
			this.viewList(addrList);
//			if(strTel.equals("-Q")) return "-Q";
			
//			this.findByTel(strTel);

		}
		System.out.println("찾기 종료!!");

	}
	
	
	
}
