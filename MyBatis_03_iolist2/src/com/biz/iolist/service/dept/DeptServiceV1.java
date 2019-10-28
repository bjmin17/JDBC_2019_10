package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {

	protected DeptDao deptDao;
	Scanner scanner;
	
	public DeptServiceV1() {
		deptDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(DeptDao.class);
		
		scanner = new Scanner(System.in);
	}
	
	// deptDao.selectAll() 을 호출하여 전체 리스트를
	// 보여주는 method
	
	public void viewAllList() {
		List<DeptDTO> deptList = deptDao.selectAll();
//		if(deptList == null || deptList.size() < 1) {
//			System.out.println("찾는 정보가 없음");
//		}
		this.viewList(deptList);
		
	}
	
	// 키보드에서 거래처이름을 입력하여
	// 거래처리스트를 보여주는 method
	public void viewNameList() {
		System.out.println("=====================================");
		System.out.println("거래처 이름 검색");
		System.out.println("=====================================");
		System.out.print("찾고자 하는 거래처명(Enter : 전체) >> ");
		String strName = scanner.nextLine();
		
		List<DeptDTO> deptList = deptDao.findByName(strName);

		if(strName.trim().isEmpty()) {
			deptList = deptDao.selectAll();
		} else {
			deptList = deptDao.findByName(strName);
		}
		this.viewList(deptList);
		
	}
	
	// 키보드에서 거래처명과
	// 대표이름을 입력하여
	// 거래처리스트를 보여주는 method
	public void viewNameAndCEOList() {
		System.out.println("=====================================");
		System.out.println("거래처 이름과 대표 검색");
		System.out.println("=====================================");
		System.out.print("찾고자 하는 거래처명 >> ");
		String strName = scanner.nextLine();
//		System.out.println("=====================================");
		System.out.print("찾고자 하는 대표이름 >> ");
		String strCEO = scanner.nextLine();
		System.out.println("=====================================");
		
		List<DeptDTO> deptList = deptDao.findByNameAndCEO(strName, strCEO);

		// 거래처명, 대표명 아무것도 입력하지 않았을 때
		if(strName.trim().isEmpty() && strCEO.trim().isEmpty()) {
			deptList = deptDao.selectAll();
			
		// 대표명만 입력했을 때, 대표명으로 검색
		} else if(strName.trim().isEmpty()){
			deptList = deptDao.findByCEO(strCEO);
			
		// 거래처명만 입력했을 때, 거래처명으로 검색
		} else if(strCEO.trim().isEmpty()) {
			deptList = deptDao.findByName(strName);
			
		// 둘다 입력하면 거래처명과 대표명으로 검색
		} else {
			deptList = deptDao.findByNameAndCEO(strName, strCEO);
		}
		this.viewList(deptList);
	}
	
	// 각 view에서 List를 출력할 때 사용할 method
	// List를 반복하면서 deptDTO를 매개변수로 전달 
	protected void viewList(DeptDTO dto) {
		
			System.out.print(dto.getD_code() + "\t");
			System.out.print(dto.getD_name() + "\t");
			System.out.print(dto.getD_ceo()+ "\t");
			System.out.print(dto.getD_tel()+ "\t");
			System.out.print(dto.getD_addr()+ "\n");
		
	}
	
	// List를 받아서 출력할 때 사용할 method
	protected void viewList(List<DeptDTO> deptList) {
		if(deptList == null || deptList.size() < 1) {
			System.out.println("찾는 정보가 없음");
		}
		System.out.println("=====================================");
		System.out.println("거래처 리스트");
		System.out.println("-------------------------------------");
		System.out.println("코드\t상호\t대표\t전화\t주소");
		System.out.println("-------------------------------------");
		for(DeptDTO dto : deptList) {
			this.viewList(dto);
		}
		System.out.println("=====================================");
	}
	
}
