package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.ProductDTO;

public class IolistServiceV2 extends IolistServiceV1{

	/*
	 * 매입매출 등록
	 * 	날짜는 현재날짜로 자동등록
	 *  거래처 검색(이름으로)
	 *  거래처 코드 입력
	 *  입력한 코드 검증
	 *  
	 *  상품검색
	 *  상품코드 입력
	 *  입력한코드 검증
	 *  
	 *  매입,매출 구분 입력
	 *  
	 *  매입, 매출에 따라서
	 *  매입단가, 매출단가 가져오기(세팅)
	 *  
	 *  수량입력
	 *  
	 *  단가 * 수량
	 *  
	 *  매입합계 또는 매출합계 계산하기
	 *  
	 *  
	 *  DTO만들어서
	 *  insert
	 *  
	 *  추가된 데이터 보여주기
	 */
	Scanner scanner;
	
	public IolistServiceV2() {
		scanner = new Scanner(System.in);
	}
	public void iolistInsert() {
		System.out.println("===============================");
		System.out.println("거래처 검색");
		System.out.println("===============================");
		System.out.print("거래처명 검색(Enter : 전체) >> ");
		String strDName = scanner.nextLine();
		
		List<DeptDTO> deptList = deptDao.findByName(strDName);
		
		if(strDName.trim().isEmpty()) {
			deptList = deptDao.selectAll();
		} else {
			deptList = deptDao.findByName(strDName);
		}
		
		if(deptList == null || deptList.size() < 1) {
			System.out.println("입력한 거래처가 없음");
		}
		this.viewDList(deptList);
		
		System.out.println("===============================");
		System.out.print("매입매출 등록할 거래처코드 입력 >> ");
		String strDCode = scanner.nextLine();
		System.out.println("===============================");
		
		DeptDTO deptDTO = deptDao.findById(strDCode);
		if(deptDTO == null) {
			System.out.println("거래처 코드가 맞지 않음");
//			return;
		} else {
			
		}
		
		// 상품검색하는 부분
		System.out.println("===============================");
		System.out.println("상품명 검색");
		System.out.println("===============================");
		System.out.print("상품명 검색(Enter : 전체) >> ");
		String strPName = scanner.nextLine();
		List<ProductDTO> proList = proDao.findByName(strPName);
		
		if(strPName.trim().isEmpty()) {
			proList = proDao.selectAll();
		} else {
			proList = proDao.findByName(strPName);
		}
		
		if(proList == null || proList.size() < 1) {
			System.out.println("입력한 상품명이 없음");
		}
		this.viewPList(proList);
		
		System.out.println("===============================");
		System.out.print("매입매출 등록할 상품코드 입력 >> ");
		String strICode = scanner.nextLine();
		System.out.println("===============================");
		long io_seq = Long.valueOf(strICode);
		
		IolistDTO ioDTO = iolistDao.findById(io_seq);
//		ProductDTO proDTO = proDao.findById(strPCode);
		if(ioDTO == null) {
			System.out.println("거래처 코드가 맞지 않음");
//			return;
		} else {
			
		}
		
		// 매입, 매출 구분 입력
		int price = 0;
//		int iprice = 0;
//		int oprice = 0;
		int total = 0;
		System.out.println("===============================");
		System.out.print("매입,매출 등록>> ");
		String strInout = scanner.nextLine();
		System.out.println("===============================");
		if(strInout.equals("매입")) {
			price = ioDTO.getIo_price();
			total = ioDTO.getIo_qty() * price;
		} else if(strInout.equals("매출")) {
			price = ioDTO.getIo_price();
			total = ioDTO.getIo_qty() * price;
		}
		
		System.out.println("INSERT");
		
		ioDTO.setIo_price(price);
		ioDTO.setIo_inout(strInout);
		ioDTO.setIo_total(total);
		
		
		iolistDao.update(ioDTO);
		// tostring으로 추가된 데이터 보여주기
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void viewDList(DeptDTO dto) {
		System.out.print(dto.getD_code() + "\t");
		System.out.print(dto.getD_name() + "\t");
		System.out.print(dto.getD_ceo() + "\t");
		System.out.print(dto.getD_tel() + "\t");
		System.out.print(dto.getD_addr() + "\n");
	}
	
	protected void viewDList(List<DeptDTO> deptList) {
		System.out.println("=====================================");
		System.out.println("거래처 리스트");
		System.out.println("-------------------------------------");
		System.out.println("코드\t상호\t대표\t전화\t주소");
		System.out.println("-------------------------------------");
		for(DeptDTO dto : deptList) {
			this.viewDList(dto);
		}
	}
	
	protected void viewPList(ProductDTO dto) {
			System.out.print(dto.getP_code() + "\t");
			System.out.print(dto.getP_name() + "\t");
			System.out.print(dto.getP_iprice() + "\t");
			System.out.print(dto.getP_oprice() + "\t");
			System.out.print(dto.getP_vat() + "\n");
	}
	protected void viewPList(List<ProductDTO> proList) {
		System.out.println("=====================================");
		System.out.println("상품 리스트");
		System.out.println("-------------------------------------");
		System.out.println("코드\t상품명\t매입단가\t매출단가\t과세여부");
		System.out.println("-------------------------------------");
		for(ProductDTO dto : proList) {
			this.viewPList(dto);
		}

	}
	
	
}
