package com.biz.iolist.config.service;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {

	
	public void menuProduct() {
		System.out.println("===================================");
		System.out.print("대한쇼핑몰 상품관리 시스템 v3");
		System.out.println("===================================");
		System.out.println("1.등록 2.수정 3.삭제 4.상품검색 0.끝");
		System.out.print("업무선택 >> ");
		String strMenu = scanner.nextLine();
	}
	
	/*
	 * 상품정보들을 입력받아서 insert 수행
	 * 상품코드를 입력받아서
	 * 있으면 다시 입력하도록
	 * 없으면 다음으로 진행
	 * 
	 */
	public void insertProduct() {

		System.out.print("상품코드 입력 >> ");
		String strCode = scanner.nextLine();
		if(strCode.trim().isEmpty()) {
			System.out.println("코드를 입력하세요");
		}
		proDao.findById(strCode);
		
		System.out.print("상품명 입력 >> ");
		String strPName = scanner.nextLine();
		if(strPName.trim().isEmpty()) {
			System.out.println("이름을 입력하세요");
		}
		
		System.out.print("매입가격 입력 >> ");
		String strIPrice = scanner.nextLine();
		int intIPrice = Integer.valueOf(strIPrice);
		
		System.out.print("매출가격 입력 >> ");
		String strOPrice = scanner.nextLine();
		
		ProductDTO productDTO = ProductDTO.builder()
				.p_code(strCode)
				.p_name(strPName)
				.p_iprice(intIPrice)
//				.p_oprice(p_oprice)
//				.p_vat
				.build();
				
		
	}
	
	/*
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {
		
	}
	
}
