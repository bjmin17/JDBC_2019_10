package com.biz.rent.service.book;

import java.util.List;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;
import com.biz.rent.service.rent.RentServiceV2;

public class BookServiceV2 extends BookServiceV1{

	
	@Override
	protected void insert() {
		// TODO 등록
		System.out.println("=============================");
		System.out.println("도서정보등록");
		System.out.println("=============================");
		
		BookDTO bookDTO = new BookDTO();
		
		// 도서코드는 자동생성
//		B_NAME
//		B_AUTHER
//		B_COMP
//		B_YEAR
//		B_IPRICE
//		B_RPRICE
		// 도서코드 자동생성
		String strBCode ;
		while(true) {
			
			System.out.print("등록할 도서코드 입력(Enter : 자동생성, -Q:quit) >> ");
			strBCode = scanner.nextLine();
			if(strBCode.equals("-Q")) break;
			if(strBCode.trim().isEmpty()) {
				//코드 자동생성
				String strTMBCode = bookDao.getMaxBCode();
				int intBCode = Integer.valueOf(strTMBCode.substring(2));
				intBCode ++;
				
				strBCode = strTMBCode.substring(0,2);
				strBCode += String.format("%04d", intBCode);
				System.out.println("생성된 코드 : " + strBCode);
				System.out.println("사용하시겠습니까?(Enter:Yes)");
				String strYesNo = scanner.nextLine();
				bookDTO.setB_code(strBCode);
				if(strYesNo.trim().isEmpty()) break;
				else continue;
			}
				 
			if(strBCode.length() != 6) {
				System.out.println("상품코드의 길이 규칙에 맞지 않음");
				// 조치취하기
				continue;
			}
			
			strBCode = strBCode.toUpperCase();
			if(!strBCode.substring(0,2).equalsIgnoreCase("BK")) {
				System.out.println("상품코드는 첫 두글자가 BK로 시작되어야 함");
				continue;
			}
			
			try {
				Integer.valueOf(strBCode.substring(2));
			} catch (Exception e) {
				System.out.println("상품코드3번째 이후는 숫자만 올 수 있음");
				continue;
			}
			
			if(bookDao.findById(strBCode) != null) {
				System.out.println("이미 등록된(사용중인) 코드!!");
				continue;
			}
			
			break;
			
		}
		// 도서명
		while(true) {
			System.out.print("등록할 도서명 입력(-Q:quit) >> ");
			String strBName = scanner.nextLine();
			if(strBName.equals("-Q")) break;
			if(strBName.trim().isEmpty()) {
				System.out.println("도서명은 반드시 입력");
				continue;
			}
			
			List<BookDTO> bookList = bookDao.findByNameSearch(strBName);
			if(bookList != null & bookList.size() > 0) {
				System.out.println("도서명 중복!!");
			} else {
				bookDTO.setB_name(strBName);
				System.out.println("도서명 등록 완료");
				break;
			}
		}
		while(true) {
			System.out.print("등록할 저자 입력(-Q:quit) >> ");
			String strBAuth = scanner.nextLine();
			if(strBAuth.equals("-Q")) break;
			if(strBAuth.trim().isEmpty()) {
				System.out.println("저자는 반드시 입력");
				continue;
			}
			
			bookDTO.setB_auther(strBAuth);
			System.out.println("저자 등록 완료");
			break;
		}
		while(true) {
			System.out.print("등록할 출판사 입력(-Q:quit) >> ");
			String strBComp = scanner.nextLine();
			if(strBComp.equals("-Q")) break;

			bookDTO.setB_comp(strBComp);
			System.out.println("출판사 등록 완료");
			break;
		}
		
		while(true) {
			System.out.println("YYYY");
			System.out.print("등록할 출판연도 입력(-Q:quit) >> ");
			
			String strBYear = scanner.nextLine();
			if(strBYear.trim().isEmpty()) {
				System.out.println("출판연도는 반드시 입력");
				continue;
			}
			if(strBYear.equals("-Q")) break;
			if(strBYear.length() != 4) {
				System.out.println("4글자로 입력");
				continue;
			}

			try {
				int intYear = Integer.valueOf(strBYear);
				bookDTO.setB_year(intYear);
				
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
			}
			
			System.out.println("출판연도 등록 완료");
			break;
		}
		while(true) {
			System.out.print("등록할 도서가격 입력(-Q:quit) >> ");
			String strIPrice = scanner.nextLine();
			if(strIPrice.equals("-Q")) break;

			try {
				int intIPrice = Integer.valueOf(strIPrice);
				bookDTO.setB_iprice(intIPrice);
			} catch (Exception e) {
				System.out.println("도서가격은 숫자만");
			}
			
			
			System.out.println("도서가격 등록 완료");
			break;
		}
		while(true) {
			System.out.print("등록할 대여료 입력(-Q:quit) >> ");
			String strRPrice= scanner.nextLine();
			if(strRPrice.equals("-Q")) break;
			if(strRPrice.trim().isEmpty()) {
				System.out.println("대여료는 반드시 입력");
				continue;
			}

			try {
				int intRPrice = Integer.valueOf(strRPrice);
				bookDTO.setB_rprice(intRPrice);
			} catch (Exception e) {
				System.out.println("대여료는 숫자만");
			}
			
			System.out.println("대여료 등록 완료");
			break;
		}
		
			System.out.println("도서 대여여부");
			RentDTO rentDTO = rentDao.findByBCode(bookDTO.getB_code());
			if(rentDTO == null) {
				System.out.println("도서코드가 존재하지 않음");
			} else {
				if(rentDTO.getRent_retur_yn() == null) {
					System.out.println("대출중인 도서입니다");
				}
			}
			
				
		int ret = bookDao.insert(bookDTO);
		if(ret > 0) System.out.println("도서정보 추가 완료");
		else System.out.println("도서정보 추가 실패");
		
		
		
	}	// 등록 구현


}
