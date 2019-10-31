package com.biz.rent.service.user;

import java.util.List;

import com.biz.rent.persistence.UserDTO;

public class UserServiceV3 extends UserServiceV2{

	@Override
	protected void update() {
		// TODO 회원정보 수정
		System.out.println("========================");
		System.out.println("회원정보 수정");
		System.out.println("========================");
		
		// 회원명 검색해서 회원코드정보 수정
		System.out.print("회원명 입력(Enter:전체) >> ");
		String strUName = scanner.nextLine();
		if(strUName.trim().isEmpty()) {
			List<UserDTO> userList = userDao.selectAll();
			this.viewT(userList);
		} else {
			List<UserDTO> userList = userDao.findByName(strUName);
			this.viewT(userList);
		}
		
		
		
		System.out.print("수정하고 싶은 회원명의 회원코드 입력 >> ");
		String strUCode = scanner.nextLine();

		UserDTO UserDTO = userDao.findById(strUCode);
//		System.out.println("DTO에 담음");
//		while(true) {
//			
//			System.out.print("수정할 회원코드 입력(Enter : 자동생성, -Q:quit) >> ");
//			strBCode = scanner.nextLine();
//			if(strBCode.equals("-Q")) break;
//
//			break;
//			
//		}
		// 회원명
		while(true) {
			System.out.print("변경할 회원명 입력(Enter:그대로, -Q:quit) >> ");
			String strName = scanner.nextLine();
			if(strName.equals("-Q")) break;
			
			if(strName.trim().isEmpty()) {
				break;
			} else {
				List<UserDTO> userList = userDao.findByNameSearch(strName);
				
				if(userList != null & userList.size() > 0) {
					System.out.println("회원명 중복!!");
				} else {
					UserDTO.setU_name(strName);
					System.out.println("회원명 수정 완료");
					break;
				}
			}

		}
		while(true) {
			System.out.print("변경할 전화번호 입력(Enter:그대로, -Q:quit) >> ");
			String strUTel = scanner.nextLine();
			if(strUTel.equals("-Q")) break;
			
			if(strUTel.trim().isEmpty()) {
				break;
				
			} else {
				List<UserDTO> userList = userDao.findByTelSearch(strUTel);
				
				if(userList != null & userList.size() > 0) {
					System.out.println("회원명 중복!!");
				} else {
					UserDTO.setU_tel(strUTel);
					System.out.println("전화번호 수정 완료");
					break;
				}
				
				
			}
		}
		while(true) {
			System.out.print("변경할 주소 입력(Enter:그대로, -Q:quit) >> ");
			String strUAdrr = scanner.nextLine();
			if(strUAdrr.equals("-Q")) break;

			if(strUAdrr.trim().isEmpty()) {
				break;
			} else {
				
				UserDTO.setU_addr(strUAdrr);
				System.out.println("주소 수정 완료");
				break;
				
			}
		}
		int ret = userDao.update(UserDTO);
		if(ret > 0) System.out.println("회원정보 수정 완료");
		else System.out.println("회원정보 수정 실패");
		
	}
}
