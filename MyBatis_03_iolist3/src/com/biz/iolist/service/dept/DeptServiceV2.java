package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1{

	public void deptMenu() {
		System.out.println("=============================================");
		System.out.println("거래처 정보 관리");
		System.out.println("=============================================");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
		System.out.println("---------------------------------------------");
		System.out.print("업무 선택 >> ");
		String strMenu = scanner.nextLine();
		try {
			int intMenu = Integer.valueOf(strMenu);
			
			/*
			 * else를 사용하지 않으면
			 * 모든 if 문을 다 검사하는 방식으로 코드가 실행되지만
			 * else를 사용하면
			 * 해당하는 코드에서 true가 나오면 이후 코드는 건너뛴다. 
			 */
			if(intMenu == 0) return;
			else if(intMenu == 1) {
				this.viewNameList();
				this.deptInsert();
			} else if(intMenu == 2) {
				
				this.viewNameList();
				
				this.deptUpdate();
			} else if(intMenu == 3) {
				// 상호로 검색을 해서 리스트를 보여주고
				this.viewNameList();
				
				// 보여준 리스트 중에서
				// 거래처 코드를 입력받아서 삭제
				this.deptDelete();
			} else if(intMenu == 4) {
				this.viewNameAndCEOList();
			}					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void deptDelete() {
		
		while(true) {
			System.out.print("삭제할 거래처 코드(-Q:quit) >> ");
			String strDCode = scanner.nextLine();
			
			if(strDCode.equals("-Q")) break;
			
			DeptDTO deptDTO = deptDao.findById(strDCode);
			if(deptDTO == null) {
				System.out.println("삭제할 거래처 코드가 존재하지 않음");
				continue;
			}
			this.viewDetail(deptDTO);
			
			System.out.print("정말 삭제?? (Enter:실행) >> ");
			String strYesNo = scanner.nextLine();
			if(strYesNo.trim().isEmpty()) {
				int ret = deptDao.delete(strDCode);
				if(ret > 0) {
					System.out.println("삭제 완료");
					break;
				}
				else System.out.println("삭제 실패");
			}
			
		}
		
	}// end delete
	
	public void deptUpdate() {
		System.out.print("수정할 코드 입력 (-Q:quit) >> ");
		String strDCode = scanner.nextLine();
		
		if(strDCode.equals("-Q")) return;
		DeptDTO deptDTO = deptDao.findById(strDCode);
		if(deptDTO == null) {
			System.out.println("수정할 거래처 코드가 존재하지 않음");
			return;
		}
		this.viewDetail(deptDTO);
		System.out.printf("수정할 상호(%s) >> ", deptDTO.getD_name());
		String strName = scanner.nextLine();
		
		// 그냥 Enter를 눌렀는지 아니면
		// 값(거래처명)을 입력하고 Enter를 눌렀는지에 따라
		// 다른 행동을 하기
		
		if(strName.trim().isEmpty()) {
			// 그냥 Enter만 눌렀으면 건너 뛰기
		} else {
			
			// 값을 입력했으면 입력한 값으로 변경하기
			deptDTO.setD_name(strName);
		}
		
		System.out.printf("수정할 대표(%s) >> ", deptDTO.getD_ceo());
		String strCEO = scanner.nextLine();
		if(!strCEO.trim().isEmpty()) {
			deptDTO.setD_ceo(strCEO);
		}
		
		System.out.printf("수정할 전화(%s) >> ", deptDTO.getD_tel());
		String strTel = scanner.nextLine();
		if(!strTel.trim().isEmpty()) {
			deptDTO.setD_tel(strTel);
		}
		
		System.out.printf("수정할 주소(%s) >> ", deptDTO.getD_addr());
		String strAddr = scanner.nextLine();
		if(!strAddr.trim().isEmpty()) {
			deptDTO.setD_addr(strAddr);
		}

		
		
		
		
		System.out.print("변경?? (Enter:YES) >> ");
		String strYesNo = scanner.nextLine();
		if(strYesNo.trim().isEmpty()) {
			int ret = deptDao.update(deptDTO);
			if(ret > 0 ) {
				System.out.println("수정 완료");
			} else {
				System.out.println("수정 실패");
			}
		}
		System.out.println(deptDao.findById(strDCode).toString());
	}
	
	public void deptInsert() {
		
	}
	
}
