package com.biz.rent.service.user;

import java.util.List;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.UserDTO;

public class UserServiceV2 extends UserServiceV1{

	@Override
	protected void insert() {
		// TODO 회원정보 등록
		System.out.println("=============================");
		System.out.println("회원정보 등록");
		System.out.println("=============================");
		
		UserDTO userDTO = new UserDTO();
		
		String strUCode ;
		while(true) {
			
			System.out.print("등록할 회원코드 입력(Enter : 자동생성, -Q:quit) >> ");
			strUCode = scanner.nextLine();
			if(strUCode.equals("-Q")) break;
			if(strUCode.trim().isEmpty()) {
				//코드 자동생성
				String strTMUCode = userDao.getMaxUCode();
				int intUCode = Integer.valueOf(strTMUCode.substring(1));
				intUCode ++;
				
				strUCode = strTMUCode.substring(0,1);
				strUCode += String.format("%05d", intUCode);
				System.out.println("생성된 코드 : " + strUCode);
				System.out.println("사용하시겠습니까?(Enter:Yes)");
				String strYesNo = scanner.nextLine();
				userDTO.setU_code(strUCode);
				if(strYesNo.trim().isEmpty()) break;
				else continue;
			}
				 
			if(strUCode.length() != 6) {
				System.out.println("회원코드의 길이가 규칙에 맞지 않음");
				// 조치취하기
				continue;
			}
			
			strUCode = strUCode.toUpperCase();
			if(!strUCode.substring(0,1).equalsIgnoreCase("S")) {
				System.out.println("상품코드는 첫글자가 S로 시작되어야 함");
				continue;
			}
			
			try {
				Integer.valueOf(strUCode.substring(2));
			} catch (Exception e) {
				System.out.println("상품코드3번째 이후는 숫자만 올 수 있음");
				continue;
			}
			
			if(userDao.findById(strUCode) != null) {
				System.out.println("이미 등록된(사용중인) 코드!!");
				continue;
			}
			
			break;
			
		}
		// 회원명
		while(true) {
			System.out.print("등록할 회원명 입력(-Q:quit) >> ");
			String strUName = scanner.nextLine();
			if(strUName.equals("-Q")) break;
			
			List<UserDTO> userList = userDao.findByNameSearch(strUName);
			if(userList != null & userList.size() > 0) {
				System.out.println("회원명 중복!!");
			} else {
				userDTO.setU_name(strUName);
				System.out.println("회원명 등록 완료");
				break;
			}
		}
		while(true) {
			System.out.print("등록할 전화번호 입력(-Q:quit) >> ");
			String strUTel = scanner.nextLine();
			if(strUTel.equals("-Q")) break;
			
			List<UserDTO> userList = userDao.findByTelSearch(strUTel);
			if(userList != null & userList.size() > 0) {
				System.out.println("전화번호 중복!!");
			} else {
				userDTO.setU_tel(strUTel);
				System.out.println("전화번호 등록 완료");
				break;
			}
			
			
		}
		while(true) {
			System.out.print("등록할 주소 입력(-Q:quit) >> ");
			String strUTel = scanner.nextLine();
			if(strUTel.equals("-Q")) break;

			userDTO.setU_tel(strUTel);
			System.out.println("주소 등록 완료");
			break;
		}
	
		int ret = userDao.insert(userDTO);
		if(ret > 0) System.out.println("회원정보 추가 완료");
		else System.out.println("회원정보 추가 실패");
		
		
		
	
	
	}
	
	

}
