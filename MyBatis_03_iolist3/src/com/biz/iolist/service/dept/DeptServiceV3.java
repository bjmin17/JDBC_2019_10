package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2{

	/*
	 * 키보드에서 정보를 입력받아 DB에 추가하기
	 * 1. 거래처코드 : 자동생성
	 * 		기존 코드가 있으면 추가 금지
	 * 2. 상호는 같은 상호가 있더라도
	 * 		대표자명이 다르면 입력 가능하도록
	 */
	public void deptInsert() {
		System.out.println("===========================");
		System.out.println("등록하기");
		System.out.println("===========================");
		String strDCode;
		System.out.print("등록할 거래처 코드(Enter : 자동생성, Q:quit) >> ");
		strDCode = scanner.nextLine();
		if(strDCode.equals("-Q")) return;
		if(strDCode.trim().isEmpty()) {
			
			
			String strTMDCode = deptDao.getMaxDCode();
			
			int intDCode = Integer.valueOf(strTMDCode.substring(1));
			intDCode ++;
			
			strDCode = strTMDCode.substring(0,1);
			strDCode += String.format("%04d", intDCode);
			
			System.out.println("생성된 코드 : " + strDCode);
			System.out.println("사용하시겠습니까?(Enter:Yes)");
			String strYesNo = scanner.nextLine();
			
			if(strYesNo.trim().isEmpty()) {
				
			}
			else return;
			
		}
		
		
		
		
		if(strDCode.length() != 5) {
			System.out.println("거래처코드의 길이는 5자이어야 함");
			return;
		}
		strDCode = strDCode.toUpperCase();
		if(!strDCode.substring(0,1).equalsIgnoreCase("D")) {
			System.out.println("거래처코드 첫글자는 D로 시작되어야 함");
			return;
		}
		
		try {
			Integer.valueOf(strDCode.substring(1));
		} catch (Exception e) {
			System.out.println("거래처코드 2번째 이후는 숫자만 입력");
//			e.printStackTrace();
			return;
		}
		if(deptDao.findById(strDCode) != null) {
			System.out.println("이미 등록된 코드!!");
			return;
		}// dcode입력 끝
		if(strDCode.equals("-Q")) return;	
		
		String strDName;
		while(true) {
			
			System.out.print("거래처이름(-Q:quit) >> ");
			strDName = scanner.nextLine();
			if(strDName.equals("-Q")) break;
			if(strDName.trim().isEmpty()) {
				System.out.println("거래처이름은 반드시 입력해야 함");
				continue;
			}
			
			List<DeptDTO> deptList = deptDao.findByName(strDName);
			if(deptList.size() > 0) {
				this.viewList(deptList);
				
				System.out.print("사용하시겠습니까?(Enter:사용,NO:다시입력");
				String yesNo = scanner.nextLine();
				if(yesNo.trim().isEmpty())break;
				continue;
			}
			break;
		}
			
			System.out.print("대표 입력(-Q:quit) >> ");
			String strDCEO = scanner.nextLine();
			List<DeptDTO> deptList = deptDao.findByNameAndCEO(strDName,strDCEO);
			if(deptList.size() > 0 ) {
				System.out.println("거래처,대표 이름 중복");
				return;
			} 
			
			if(strDCEO.equals("-Q")) return;
			
			System.out.print("전화 입력(-Q:quit) >> ");
			String strDTel = scanner.nextLine();
			if(strDTel.equals("-Q")) return;
			
			System.out.print("주소 입력(-Q:quit) >> ");
			String strDAddr = scanner.nextLine();
			if(strDAddr.equals("-Q")) return;
			
			DeptDTO dDTO = DeptDTO.builder()
					.d_code(strDCode)
					.d_name(strDName)
					.d_ceo(strDCEO)
					.d_tel(strDTel)
					.d_addr(strDAddr)
					.build();
			
			int ret = deptDao.insert(dDTO);
			if(ret>0) System.out.println("거래처 등록 성공");
			else System.out.println("거래처 등록 실패");
			
			
	}
	
} 
