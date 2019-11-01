package com.biz.rent.service.rent;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.dao.RentDao;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.RentDTO;

public class RentServiceV1 {

	protected BookDao bookDao;
	protected RentDao rentDao;
	protected UserDao userDao;
	
	protected Scanner scanner;
	
	public RentServiceV1() {
		
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		this.bookDao = sqlSession.getMapper(BookDao.class);
		this.rentDao = sqlSession.getMapper(RentDao.class);
		this.userDao = sqlSession.getMapper(UserDao.class);
		
		scanner = new Scanner(System.in);
		
	}
	
	public void rentMenu() {
		while(true) {
			System.out.println("=======================================");
			System.out.println("도서 대출 관리 V1");
			System.out.println("=======================================");
			System.out.println("1.등록 2.반납 3.삭제 4.검색 0.종료");
			System.out.println("---------------------------------------");
			System.out.print("업무선택 >> ");
			String strMenu = scanner.nextLine();
			
			int intMenu = -1;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				continue;
			}
			if(intMenu == 0) break;
			else if(intMenu==1) this.insert();
			else if(intMenu==2) this.update();
			else if(intMenu==3) this.delete();
			else if(intMenu==4) this.viewAllList();
			
		}
		System.out.println("도서 대출 관리 종료~!~!~!~!");

	}

	protected void viewList(List<RentDTO> rentList) {
		System.out.println("==============================================");
		System.out.println("도서 대출 리스트");
		System.out.println("==============================================");
		System.out.println("일련번호\t대출일\t반납예정일\t도서코드\t회원코드\t도서반납여부\t포인트");
		System.out.println("----------------------------------------------");
		for(RentDTO vo : rentList) {
			this.viewItem(vo);
		}
		System.out.println("==============================================");

	}
	protected void viewItem(RentDTO vo) {
		System.out.print(vo.getRent_seq() + "\t");
		System.out.print(vo.getRent_date() + "\t");
		System.out.print(vo.getRent_return_date() + "\t");
		System.out.print(vo.getRent_bcode() + "\t");
		System.out.print(vo.getRent_ucode() + "\t");
		System.out.print(vo.getRent_retur_yn() + "\t");
		System.out.print(vo.getRent_point() + "\n");

	}
	public void viewAllList() {
		List<RentDTO> iolist = rentDao.selectAll();
		if(iolist != null && iolist.size() > 0) {
			this.viewList(iolist);
		}
	}

	
	protected void search() {
		// TODO Auto-generated method stub
		
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
	
	
}
