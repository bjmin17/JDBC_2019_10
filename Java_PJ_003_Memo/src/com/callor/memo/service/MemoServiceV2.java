package com.callor.memo.service;

import com.callor.persistence.MemoDTO;

public class MemoServiceV2 extends MemoServiceV1{
	
	
//	public void update() {
		

	@Override
	protected void delete() {
		System.out.println("==================================");
		System.out.println("메모 삭제");
		System.out.println("==================================");
		System.out.print("SEQ 조회(Enter:전체,-Q:quit) >> ");
		String strID = scanner.nextLine();
		if(strID.equals("-Q")) return;
		
		if(strID.trim().isEmpty()) {
			this.viewAllList();
		} else {
			MemoDTO memoDTO = memoDao.findById(Integer.valueOf(strID));
			
			if(memoDTO == null) {
				System.out.println("입력한 SEQ와 일치하는 글이 없음");
			}
		}

		
		System.out.print("삭제하고 싶은 SEQ >> ");
		strID = scanner.nextLine();
		
		
		System.out.println("정말 삭제 ? (Enter:삭제, 삭제하고 싶지 않으면 아무키나 누르세요)");
		String strDYN = scanner.nextLine();
		if(strDYN.trim().isEmpty()) {
			int ret = memoDao.delete(Integer.valueOf(strID));
			if(ret > 0)System.out.println("삭제 완료");
			else System.out.println("삭제 실패");
		}
		
	}

	@Override
	protected void update() {
		System.out.println("==================================");
		System.out.println("메모 수정");
		System.out.println("==================================");
		String strID;
		while(true) {
			System.out.print("SEQ 조회(Enter:전체,-Q:quit) >> ");
			strID = scanner.nextLine();
			if(strID.equals("-Q")) break;
			
			if(strID.trim().isEmpty()) {
				this.viewAllList();
				break;
			}
			MemoDTO memoDTO = memoDao.findById(Integer.valueOf(strID));
			
			if(memoDTO == null) {
				
				System.out.println("입력한 SEQ와 일치하는 글이 없음");
				continue;
			}			
		}

		
		System.out.print("변경할 SEQ 입력(Enter:전체) >> ");
		
		strID = scanner.nextLine();
		int intID = Integer.valueOf(strID);
		MemoDTO memoDTO = memoDao.findById(intID);
		
		System.out.printf("수정할 제목 입력(%s) >> ", memoDTO.getM_subject());
		String strSubject = scanner.nextLine();
		if(strSubject.trim().isEmpty()) {
			
		} else {
			memoDTO.setM_subject(strSubject);
		}
		
		
		System.out.printf("수정할 내용 입력(%s) >> ", memoDTO.getM_text());
		String strText = scanner.nextLine();
		
		if(strText.trim().isEmpty()) {
			
		} else {
			memoDTO.setM_text(strText);
		}
		
		System.out.printf("수정할 사진파일 입력(%s) >> ", memoDTO.getM_photo());
		String strPhoto = scanner.nextLine();
		
		if(strPhoto.trim().isEmpty()) {
			
		} else {
			memoDTO.setM_photo(strPhoto);
		}
		
		
		int ret = memoDao.update(memoDTO);
		if(ret > 0) {
			System.out.println("수정 완료");
			System.out.println("======================");
			System.out.println("수정된 정보");
			System.out.println("----------------------");
			System.out.println("일련번호 : " + memoDTO.getId());
			System.out.println("작성자 : " + memoDTO.getM_auth());
			System.out.println("작성일자 : " + memoDTO.getM_date());
			System.out.println("제목 : " + memoDTO.getM_subject());
			System.out.println("내용 : " + memoDTO.getM_text());
			System.out.println("사진파일 : " + memoDTO.getM_photo());
			System.out.println("======================");
		}
		else System.out.println("수정 실패");
		
	}

	
	

}
