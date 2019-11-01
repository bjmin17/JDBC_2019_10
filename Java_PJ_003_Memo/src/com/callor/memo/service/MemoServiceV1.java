package com.callor.memo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.callor.memo.config.DBConnection;
import com.callor.memo.dao.MemoDao;
import com.callor.persistence.MemoDTO;

public class MemoServiceV1 {

	protected MemoDao memoDao;
	Scanner scanner;
	
	public MemoServiceV1() {
		memoDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(MemoDao.class);
		scanner = new Scanner(System.in);
	}
	
	protected void viewList(MemoDTO dto) {
		System.out.print(dto.getId() + "\t");
		System.out.print(dto.getM_auth() + "\t");
		System.out.print(dto.getM_date() + "\t");
		System.out.print(dto.getM_subject() + "\t");
		System.out.print(dto.getM_text() + "\t");
		System.out.print(dto.getM_photo() + "\n");		
	}
	
	protected void viewList(List<MemoDTO> memoList) {
		for(MemoDTO dto : memoList) {
			this.viewList(dto);
		}
	}
	
	public void memoMenu() {
		
		while(true) {
			System.out.println("==================================");
			System.out.println("메뉴 선택");
			System.out.println("==================================");
			System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
			System.out.println("----------------------------------");
			System.out.print("업무선택 >> ");
			String strMenu = scanner.nextLine();
			int intMenu = Integer.valueOf(strMenu);
			
			if(intMenu == 0) break;
			else if(intMenu == 1) this.insert();
			else if(intMenu == 2) this.update();
			else if(intMenu == 3) this.delete();
			else if(intMenu == 4) {
				this.viewAllList();
				this.searchTitleList();
				this.searchTextList();
				this.searchDateList();
			}
			
			
		}
			
	}
	
	
	
	protected void delete() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

	public void viewAllList() {
		System.out.println("==================================");
		System.out.println("메모 전체 보기");
		System.out.println("==================================");
		System.out.println("일련번호\t작성자\t작성일자\t제목\t사진파일");
		System.out.println("----------------------------------");
		
		List<MemoDTO> memoList = memoDao.selectAll();
		this.viewList(memoList);
		System.out.println("==================================");
	}
	
	public void searchTitleList() {
		System.out.println("==================================");
		System.out.println("글 제목으로 검색");
		System.out.println("==================================");
		System.out.println("일련번호\t작성자\t작성일자\t제목\t사진파일");
		System.out.println("----------------------------------");
		System.out.print("찾고자 하는 제목 검색 >> ");
		String strSub = scanner.nextLine();
		
		List<MemoDTO> memoList = memoDao.findBySubject(strSub);
		this.viewList(memoList);
		System.out.println("==================================");
		
	}
	
	public void searchTextList() {
		System.out.println("==================================");
		System.out.println("글 내용으로 검색");
		System.out.println("==================================");
		System.out.println("일련번호\t작성자\t작성일자\t제목\t사진파일");
		System.out.println("----------------------------------");
		System.out.print("찾고자 하는 내용 검색 >> ");
		String strText = scanner.nextLine();
		
		List<MemoDTO> memoList = memoDao.findByText(strText);
		this.viewList(memoList);
		System.out.println("==================================");
		
	}
	
	public void searchDateList() {
		System.out.println("==================================");
		System.out.println("글 작성일자로 검색");
		System.out.println("==================================");
		System.out.println("일련번호\t작성자\t작성일자\t제목\t사진파일");
		System.out.println("----------------------------------");
		System.out.println("");
		System.out.print("찾고자 하는 시작 작성일자(YYYY-MM-DD) 검색 >> ");
		String strSDate = scanner.nextLine();
		
		System.out.print("찾고자 하는 끝 작성일자(YYYY-MM-DD) 검색 >> ");
		String strEDate = scanner.nextLine();
		
		List<MemoDTO> memoList = memoDao.findByDate(strSDate, strEDate);
		this.viewList(memoList);
		System.out.println("==================================");
		
	}
	
	public void insert() {
		System.out.println("==================================");
		System.out.println("메모 등록");
		System.out.println("==================================");
		
		MemoDTO memoDTO = new MemoDTO();
		
		while(true) {
			
			System.out.print("작성자 >> ");
			String strAuth = scanner.nextLine();
			if(strAuth.trim().isEmpty()) {
				System.out.println("작성자는 반드시 입력해야합니다");
				continue;
			}
			memoDTO.setM_auth(strAuth);
			break;
		}
		
		// 작성일자
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sf.format(date);
		memoDTO.setM_date(curDate);
		
		System.out.print("제목 >> ");
		String strSubject = scanner.nextLine();
		memoDTO.setM_subject(strSubject);
		
		System.out.print("내용 >> ");
		String strText = scanner.nextLine();
		memoDTO.setM_text(strText);
		
//		System.out.print("사진파일 >> ");
//		String strPhoto = scanner.nextLine();
//		memoDTO.setM_photo(strPhoto);
		
		int ret = memoDao.insert(memoDTO);
		if(ret > 0) System.out.println("메모 등록 완료");
		else System.out.println("메모 등록 실패");
	}
}
