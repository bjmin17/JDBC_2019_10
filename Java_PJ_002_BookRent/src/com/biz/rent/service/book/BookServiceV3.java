package com.biz.rent.service.book;

import java.util.List;

import com.biz.rent.persistence.BookDTO;

public class BookServiceV3 extends BookServiceV2{

	@Override
	protected void update() {
		// TODO 도서정보 수정
		System.out.println("========================");
		System.out.println("도서정보 수정");
		System.out.println("========================");
		
		// 도서명 검색해서 도서코드정보 수정
		System.out.print("도서명 입력(Enter:전체) >> ");
		String strBName = scanner.nextLine();
		if(strBName.trim().isEmpty()) {
			List<BookDTO> bookList = bookDao.selectAll();
			this.viewT(bookList);
		} else {
			List<BookDTO> bookList = bookDao.findByName(strBName);
			this.viewT(bookList);
		}
		
		
		
		System.out.print("수정하고 싶은 도서명의 도서코드 입력 >> ");
		String strBCode = scanner.nextLine();

		BookDTO bookDTO = bookDao.findById(strBCode);
		System.out.println("DTO에 담음");
//		while(true) {
//			
//			System.out.print("수정할 도서코드 입력(Enter : 자동생성, -Q:quit) >> ");
//			strBCode = scanner.nextLine();
//			if(strBCode.equals("-Q")) break;
//
//			break;
//			
//		}
		// 도서명
		while(true) {
			System.out.print("변경할 도서명 입력(Enter:그대로, -Q:quit) >> ");
			String strName = scanner.nextLine();
			if(strName.equals("-Q")) break;
			
			if(strName.trim().isEmpty()) {
				break;
			} else {
				List<BookDTO> bookList = bookDao.findByNameSearch(strName);
				
				if(bookList != null & bookList.size() > 0) {
					System.out.println("도서명 중복!!");
				} else {
					bookDTO.setB_name(strName);
					System.out.println("도서명 수정 완료");
					break;
				}
			}

		}
		while(true) {
			System.out.print("변경할 저자 입력(Enter:그대로, -Q:quit) >> ");
			String strBAuth = scanner.nextLine();
			if(strBAuth.equals("-Q")) break;
			
			if(strBAuth.trim().isEmpty()) {
				break;
				
			} else {
				bookDTO.setB_auther(strBAuth);
				System.out.println("저자 수정 완료");
				break;
				
			}
		}
		while(true) {
			System.out.print("변경할 출판사 입력(Enter:그대로, -Q:quit) >> ");
			String strBComp = scanner.nextLine();
			if(strBComp.equals("-Q")) break;

			if(strBComp.trim().isEmpty()) {
				break;
			} else {
				bookDTO.setB_comp(strBComp);
				System.out.println("출판사 수정 완료");
				break;
				
			}
		}
		
		while(true) {
			System.out.println("YYYYMMDD");
			System.out.print("변경할 출판연도 입력(Enter:그대로, -Q:quit) >> ");
			
			String strBYear = scanner.nextLine();
			if(strBYear.equals("-Q")) break;
			
			if(strBYear.trim().isEmpty()) {
				break;
			} else {
				try {
					int intYear = Integer.valueOf(strBYear);
					bookDTO.setB_year(intYear);
				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요");
				}
				
				System.out.println("출판연도 수정 완료");
				break;
				
			}

		}
		while(true) {
			System.out.print("변경할 도서가격 입력(Enter:그대로, -Q:quit) >> ");
			String strIPrice = scanner.nextLine();
			if(strIPrice.equals("-Q")) break;

			if(strIPrice.trim().isEmpty()) {
				break;
			} else {
				try {
					int intIPrice = Integer.valueOf(strIPrice);
					bookDTO.setB_iprice(intIPrice);
				} catch (Exception e) {
					System.out.println("도서가격은 숫자만");
				}
				
				
				System.out.println("도서가격 수정 완료");
				break;
				
			}
		}
		while(true) {
			System.out.print("변경할 대여료 입력(Enter:그대로, -Q:quit) >> ");
			String strRPrice= scanner.nextLine();
			if(strRPrice.equals("-Q")) break;

			if(strRPrice.trim().isEmpty()) {
				break;
			} else {
				try {
					int intRPrice = Integer.valueOf(strRPrice);
					bookDTO.setB_rprice(intRPrice);
				} catch (Exception e) {
					System.out.println("대여료는 숫자만");
				}
				
				System.out.println("대여료 수정 완료");
				break;
				
			}
		}
		int ret = bookDao.update(bookDTO);
		if(ret > 0) System.out.println("도서정보 수정 완료");
		else System.out.println("도서정보 수정 실패");
		
		
		
	}	// 등록 구현

	
}
