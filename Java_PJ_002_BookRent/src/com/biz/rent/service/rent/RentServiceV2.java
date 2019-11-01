package com.biz.rent.service.rent;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;
import com.biz.rent.persistence.UserDTO;
import com.biz.rent.service.book.BookServiceV3;
import com.biz.rent.service.user.UserServiceV3;

public class RentServiceV2 extends RentServiceV1{

	protected BookServiceV3 bookService = new BookServiceV3();
	protected UserServiceV3 userService = new UserServiceV3();
	
	String rent_date;
	String rent_due_date;
	
	public RentServiceV2() {
		rent_date = null;
		rent_due_date = null;
	}
	@Override
	protected void insert() {
		System.out.println();
		System.out.println("============================");
		System.out.println("도서 대출 등록");
		System.out.println("============================");
		
		RentDTO rentDTO = new RentDTO();
		
//		while(true) {
//			System.out.print("빌릴 책 일련번호 >> ");
//		}
		
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String strCurDate = sf.format(date);
		String rent_date = strCurDate;
		rentDTO.setRent_date(strCurDate);
		
		// 현재(시스템 날짜) 에서 14일 후 날짜 구하기
		// 1.7 이하에서
//		Calendar calendar = new Calendar();
		
		// 1.8 이상
		// TODO 반납예정일
		
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusDays(14);
		strCurDate = localDate.toString();
		String rent_due_date = strCurDate;
		rentDTO.setRent_return_date(strCurDate);
//		String rent_due_date = "2019-10-30";
//		String rent_date = "2019-10-31";
//		
//		// 반납예정일 < 반납일 : 포인트 0
//		// 반납예정일 >= 반납일 : 포인트 5
//		
//		int diff = rent_date.compareTo(rent_due_date);
//		System.out.println(diff);
//		// 1이 나오면 ,, 날짜가 같으면 0
//		if(diff > 0) { // 1 이상이면 지연
//			
//			// 반납일 이후에 반납하기
//			System.out.println("지연반납");
//		} else { // 0이거나 -1이면 정상
//			// 정상반납
//			System.out.println("정상반납");
//		}
		
//		while(true) {
//			System.out.print("빌릴 책 반납예정일 >> ");
//			break;
//		}
		
		while(true) {
			System.out.print("빌릴 도서명 검색(-Q:quit) >> ");
			String BName = scanner.nextLine();
			if(BName.equals("-Q")) break;
			
			List<BookDTO> bookList = bookDao.findByName(BName);
			if(bookList != null && bookList.size() > 0) {
				for(BookDTO dto : bookList) {
					System.out.println(dto.toString());
				}
				
				System.out.print("도서코드 입력 >> ");
				String strBCode = scanner.nextLine();
				
				RentDTO rDTO = rentDao.findByBCode(strBCode);
				if(rDTO != null) {
					System.out.println("빌려간 도서코드 입니다");
					break;
				}
				
				// 여기까지
				if(!strBCode.trim().isEmpty()) {
					BookDTO bookDTO = bookDao.findById(strBCode);
					if(bookDTO == null) {
						System.out.println("도서코드 없음");
						continue;
					} else {
						rentDTO.setRent_bcode(strBCode);
					}
				}
				
			} else {
				System.out.println("입력한 도서명이 없음");
				continue;
			}
			break;
		}
//		if(rentDTO.getRent_bcode().isEmpty()) return;
		if(rentDTO.getRent_bcode() == null) return;
		if(rentDTO.getRent_bcode().isEmpty()) {
			return;
		}
//		} else if(rentDTO.getRent_bcode() == null) return;
		
		while(true) {
			System.out.print("빌릴 회원의 이름 검색(-Q:quit) >> ");
			String UName = scanner.nextLine();
			if(UName.equals("-Q")) break;
			
			List<UserDTO> userList = userDao.findByName(UName);
			if(userList != null && userList.size() > 0) {
				for(UserDTO dto : userList) {
					System.out.println(dto.toString());
				}
				
				System.out.print("회원코드 입력 >> ");
				String strUCode = scanner.nextLine();
				if(!strUCode.trim().isEmpty()) {
					UserDTO userDTO = userDao.findById(strUCode);
					if(userDTO == null) {
						System.out.println("회원코드 없음");
						continue;
					} else {
						rentDTO.setRent_ucode(strUCode);
					}
				}
				
			} else {
				System.out.println("입력한 회원의 이름이 없음");
				continue;
			}
			break;
		}
		if(rentDTO.getRent_ucode().isEmpty()) return;
		
		
		rentDTO.setRent_point(rentDTO.getRent_point());
		
		int ret = rentDao.insert(rentDTO);
		if(ret > 0) System.out.println("대출 등록 완료");
		else System.out.println("대출 등록 실패");
		
	}
	@Override
	protected void update() {
		//TODO 수정
		// No목록 보여주기
		List<RentDTO> rentList = rentDao.selectNoList();
		this.viewList(rentList);
		System.out.print("대출 정보 수정할 SEQ(-Q:quit) >> ");
		String strSEQ = scanner.nextLine();
		if(strSEQ.equals("-Q")) return;
		int intSEQ = Integer.valueOf(strSEQ);
		
		RentDTO rentDTO = rentDao.findById(intSEQ);
		while(true) {

			System.out.print("반납하시겠습니까? (Y/N, -Q:quit) >> ");
			String strYN = scanner.nextLine();
			if(strYN.equals("-Q")) break;
			
			strYN = strYN.toUpperCase();
			if(!strYN.equalsIgnoreCase("Y")) {
				continue;
			} else {
				rentDTO.setRent_retur_yn(strYN);
				break;
			}

		}
//		String rent_due_date = "2019-10-30";
//		String rent_date = "2019-10-31";
//		
//		// 반납예정일 < 반납일 : 포인트 0
//		// 반납예정일 >= 반납일 : 포인트 5
//		
//		int diff = rent_date.compareTo(rent_due_date);
//		System.out.println(diff);
//		// 1이 나오면 ,, 날짜가 같으면 0
//		if(diff > 0) { // 1 이상이면 지연
//			
//			// 반납일 이후에 반납하기
//			System.out.println("지연반납");
//		} else { // 0이거나 -1이면 정상
//			// 정상반납
//			System.out.println("정상반납");
//		}
		while(true) {
//			System.out.println("기간 내 반납 시 5포인트 적립");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String strCurDate = sf.format(date);
			String rent_date = strCurDate;
			
			// 현재(시스템 날짜) 에서 14일 후 날짜 구하기
			// 1.7 이하에서
//			Calendar calendar = new Calendar();
			
			// 1.8 이상
			// TODO 반납예정일
			
			LocalDate localDate = LocalDate.now();
			localDate = localDate.plusDays(14);
			strCurDate = localDate.toString();
			String rent_due_date = strCurDate;
			
			int diff = rent_date.compareTo(rent_due_date);
			
			if(diff > 0) {
				// 1 이상이면 반납일 이후에 반납, 지연반납
				System.out.println("지연반납 diff" + diff);
				System.out.println("지연반납");
			} else {
				// 0이거나 0보다 작으면 기간 내 반납, 정상반납
				System.out.println("정상반납 diff" + diff);
				System.out.println("정상반납");
				int point = rentDTO.getRent_point();
				point += 5;
				rentDTO.setRent_point(point);
			}
			
			break;
		}
		int ret = rentDao.update(rentDTO);
		if(ret > 0)System.out.println("대출 정보 수정 완료");
		else System.out.println("대출 정보 수정 실패");
	}
}
