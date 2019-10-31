package com.biz.rent.service.user;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.UserDTO;

public class UserServiceV1 {

	protected UserDao userDao;
	Scanner scanner;
	
	public UserServiceV1() {
		userDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);

		scanner = new Scanner(System.in);
	}
	
	public void UserMenu() {
		
		while(true) {
			System.out.println("=================================");
			System.out.println("회원정보 메뉴 V1");
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
		System.out.println("회원정보 종료");
		
		
		
	}

	protected void viewT(UserDTO dto) {
		System.out.print(dto.getU_code() + "\t");
		System.out.print(dto.getU_name() + "\t");
		System.out.print(dto.getU_tel() + "\t");
		System.out.print(dto.getU_addr() + "\n");
	}
	
	protected void viewT(List<UserDTO> userList) {
		System.out.println("=================================");
		System.out.println("회원 목록");
		System.out.println("=================================");
		System.out.println("회원코드\t회원이름\t전화번호\t주소");
		System.out.println("---------------------------------");
		System.out.println();
		for(UserDTO dto : userList) {
			this.viewT(dto);
		}
		System.out.println("=================================");

	}
	
	
	public void viewAllList() {
		//TODO 리스트 전체 보기
		List<UserDTO> userList = userDao.selectAll();
		if(userList == null || userList.size() < 1) {
			System.out.println("찾는 정보가 없음");
		}
		this.viewT(userList);
		
		
	}
	
	public void viewNameList() {
		//TODO 이름으로 회원정보 검색
		System.out.println("=================================");
		System.out.println("회원이름으로 검색");
		System.out.println("=================================");
		System.out.print("찾고자 하는 회원이름(Enter:전체) >> ");
		String strUName = scanner.nextLine();

		List<UserDTO> userList = userDao.findByName(strUName);
		
		if(strUName.trim().isEmpty()) {
			userList = userDao.selectAll();
		} else {
			userList = userDao.findByName(strUName);
		}
		this.viewT(userList);
	}
	
	public void viewNameAndTel() {
		System.out.println("=================================");
		System.out.println("회원이름과 전화번호로 검색");
		System.out.println("=================================");
		System.out.print("찾고자 하는 회원이름(Enter:전체) >> ");
		String strUName = scanner.nextLine();
		System.out.print("찾고자 하는 전화번호(Enter:전체) >> ");
		String strUTel = scanner.nextLine();
		System.out.println("---------------------------------");
		
		List<UserDTO> userList = userDao.findByNameAndTel(strUName,strUTel);
		// 회원이름, 전화번호 둘다 입력하지 않았을 때
		if(strUName.trim().isEmpty() && strUTel.trim().isEmpty()) {
			userList = userDao.selectAll();

		// 전화번호만 입력했을 때, 전화번호로 검색
		} else if(strUName.trim().isEmpty()) {
			userList = userDao.findByTel(strUTel);
		
		// 회원이름만 입력했을 때, 회원이름으로 검색
		} else if(strUTel.trim().isEmpty()) {
			userList = userDao.findByName(strUName);
		// 둘다 입력하면 회원이름과 전화번호 검색
		} else {
			userList = userDao.findByNameAndTel(strUName, strUTel);
		}
		this.viewT(userList);
	}
	
	protected void delete() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

	protected void insert() {
		// TODO 회원정보 등록
		
	}
	
}
