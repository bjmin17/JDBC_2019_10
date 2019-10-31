package com.biz.rent.service.book;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.persistence.BookDTO;

public class BookServiceV1 {

	protected BookDao bookDao;
	Scanner scanner;
	
	public BookServiceV1() {
		bookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
		
		scanner = new Scanner(System.in);
	}
	public void BookMenu() {
		while(true) {
			System.out.println("=================================");
			System.out.println("도서정보 메뉴 V1");
			System.out.println("=================================");
			System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
			System.out.println("---------------------------------");
			System.out.print("업무선택 >> ");
			String strMenu = scanner.nextLine();
			
			int intMenu = -1;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(intMenu == 0) break;
			else if(intMenu == 1) this.insert();
			else if(intMenu == 2) this.update();
			else if(intMenu == 3) this.delete();
			else if(intMenu == 4) this.viewNameList();
		}
		System.out.println("도서정보 종료");
	}
	
	protected void delete() {
		// TODO Auto-generated method stub
		
	}
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	protected void insert() {
		// TODO Auto-generated method stub
		
	}
	protected void viewT(List<BookDTO> bookList){
		System.out.println("=================================");
		System.out.println("도서 목록");
		System.out.println("=================================");
		System.out.println("도서코드\t도서명\t저자\t출판사\t발행연도\t가격\t대여료");
		System.out.println("---------------------------------");
		System.out.println();
		for(BookDTO dto : bookList) {
			this.viewT(dto);
		}
		System.out.println("=================================");
	}
	
	protected void viewT(BookDTO dto) {
		System.out.print(dto.getB_code() + "\t");
		System.out.print(dto.getB_name() + "\t");
		System.out.print(dto.getB_auther() + "\t");
		System.out.print(dto.getB_comp() + "\t");
		System.out.print(dto.getB_year() + "\t");
		System.out.print(dto.getB_iprice() + "\t");
		System.out.print(dto.getB_rprice() + "\n");
	}
	
	
	public void viewAllList() {
		//TODO 전체 리스트 보여주기
		List<BookDTO> bookList = bookDao.selectAll();
		if(bookList == null || bookList.size() < 1) {
			System.out.println("찾는 정보가 없음");
		}
		this.viewT(bookList);
	}
	
	public void viewNameList() {
		//TODO 도서명으로 검색
		System.out.println("=================================");
		System.out.println("도서명으로 검색");
		System.out.println("=================================");
		System.out.print("찾고자 하는 도서명(Enter:전체) >> ");
		String strBName = scanner.nextLine();
		
		List<BookDTO> bookList = bookDao.findByName(strBName);
		
		if(strBName.trim().isEmpty()) {
			bookList = bookDao.selectAll();
		} else {
			bookList = bookDao.findByName(strBName);
		}
		this.viewT(bookList);
	} // 도서명 검색 끝
	
	public void viewNameAndAuth() {
		System.out.println("=================================");
		System.out.println("도서명과 저자로 검색");
		System.out.println("=================================");
		System.out.print("찾고자 하는 도서명(Enter:전체) >> ");
		String strBName = scanner.nextLine();
		System.out.print("찾고자 하는 저자(Enter:전체) >> ");
		String strBAuth = scanner.nextLine();
		System.out.println("---------------------------------");
		
		List<BookDTO> bookList = bookDao.findByNameAndAuth(strBName,strBAuth);
		// 도서명, 저자 둘다 입력하지 않았을 때
		if(strBName.trim().isEmpty() && strBAuth.trim().isEmpty()) {
			bookList = bookDao.selectAll();

		// 저자만 입력했을 때, 저자로 검색
		} else if(strBName.trim().isEmpty()) {
			bookList = bookDao.findByAuth(strBAuth);
		
		// 도서명만 입력했을 때, 도서명으로 검색
		} else if(strBAuth.trim().isEmpty()) {
			bookList = bookDao.findByName(strBName);
		// 둘다 입력하면 도서명과 저자로 검색
		} else {
			bookList = bookDao.findByNameAndAuth(strBName, strBAuth);
		}
		this.viewT(bookList);
	}// 도서명,저자 검색 끝
	
	
	
	
	
	
	
	
}
