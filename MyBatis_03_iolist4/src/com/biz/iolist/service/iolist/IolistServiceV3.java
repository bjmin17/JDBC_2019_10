package com.biz.iolist.service.iolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.IolistVO;
import com.biz.iolist.persistence.ProductDTO;

public class IolistServiceV3 extends IolistServiceV2{

	/*
	 * 1. 매입,매출 구분 입력
	 * 
	 * 매입매출 등록
	 * 2. 날짜는 현재날짜로 자동등록
	 * 3. 거래처 검색(이름으로)
	 *  거래처 코드 입력
	 *  입력한 코드 검증
	 *  
	 * 4. 상품검색
	 *  상품코드 입력
	 *  입력한코드 검증
	 *  
	 *  
	 *  
	 *  매입, 매출에 따라서
	 * 5. 매입단가, 매출단가 가져오기(세팅)
	 *  
	 * 6. 수량입력
	 *  
	 *  단가 * 수량
	 *  
	 *  매입합계 또는 매출합계 계산하기
	 *  
	 *  
	 *  DTO만들어서
	 *  insert
	 *  
	 *  추가된 데이터 보여주기
	 */

	@Override
	protected void update() {
		System.out.println("========================");
		System.out.println("매입매출수정");
		System.out.println("========================");
		
		// 거래처이름으로 검색해서 매입매출 수정한다는 가정
		System.out.print("거래처명(Enter:전체) >> ");
		String strDName = scanner.nextLine();
		if(strDName.trim().isEmpty()) {
			ioView.viewAllList();
		} else {
			ioView.viewListByDName(strDName);
		}
		
		
		System.out.print("수정할 SEQ >> ");
		String strSEQ = scanner.nextLine();

		long longSEQ = 0;
		try {
			longSEQ = Long.valueOf(strSEQ);
		} catch (Exception e) {
			System.out.println("SEQ 형식이 틀렸습니다");
			return;
		}
		// SEQ에 해당하는 iolist 가져오기
		IolistDTO iolistDTO = iolistDao.findById(longSEQ);
		
		while(true) {
			System.out.printf("거래구분[%s] 입력(1:매입, 2:매출, -1:종료) >> ", iolistDTO.getIo_inout());
			String strInout = scanner.nextLine();
			if(!strInout.trim().isEmpty()) {
				try {
					int intInout = Integer.valueOf(strInout);
					if(intInout < 0) return;
					
					if(intInout == 1) {
						iolistDTO.setIo_inout("매입");
					} else if(intInout == 2) {
						iolistDTO.setIo_inout("매출");
					} else {
						System.out.println("매입, 매출구분을 다시 선택하세요");
						continue;
					}
					
					
				} catch (Exception e) {
					System.out.println("매입 매출구분을 다시 입력해 주세요");
					continue;
				}
				
			}
			break;
		}

		if(iolistDTO.getIo_inout().isEmpty()) return;
		
		while(true) {
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String curDate = sd.format(date);
			System.out.println("날짜 형태는 : YYYY-MM-DD");
			System.out.printf("거래일자(%s) >> ", curDate);
			String strDate = scanner.nextLine();
			if(strDate.trim().isEmpty()) {
				iolistDTO.setIo_date(curDate);
			} else {
				
				// 트릭
				try {
					sd.parse(strDate);
				} catch (ParseException e) {
					System.out.println("날짜형식이 잘못되었습니다");
					continue;
				}
				iolistDTO.setIo_date(strDate);
			}
			
			break;
			
		}
		
		while(true) {
			System.out.print("거래처명 입력(-Q:quit) >> ");
			String DName = scanner.nextLine();
			if(strDName.equals("-Q")) break;
			
			List<DeptDTO> deptList = deptDao.findByName(DName);
			if(deptList != null && deptList.size() > 0) {
				
				for(DeptDTO dto : deptList) {
					System.out.println(dto.toString());
				}
				System.out.print("거래처코드 입력 >> ");
				String strDCode = scanner.nextLine();
				if(!strDCode.trim().isEmpty()) {
					DeptDTO deptDTO = deptDao.findById(strDCode);
					if(deptDTO == null) {
						System.out.println("거래처코드 없음");
						continue;
					} else {
						iolistDTO.setIo_dcode(strDCode);
					}

				}
			} else {
				System.out.println("거래처명이 없음");
				continue;
			}
			break;
		}
		if(iolistDTO.getIo_dcode().isEmpty()) return;
		
		while(true) {
			System.out.print("상품명 입력(-Q:quit) >> ");
			String strPName = scanner.nextLine();
			
			if(strPName.equals("-Q")) break;
			if(!strPName.trim().isEmpty()) {
				List<ProductDTO> proList = proDao.findByName(strPName);
				
				if(proList == null || proList.size() < 1) {
					System.out.println("찾는 상품이 없음");
					continue;
				} else {
					for(ProductDTO dto : proList) {
						System.out.println(dto.toString());
					}
					System.out.print("상품코드 >> ");
					String strPCode = scanner.nextLine();
					
					ProductDTO proDTO = proDao.findById(strPCode);
					if(proDTO == null) {
						System.out.println("상품코드를 확인하세요");
						continue;
					} else {
						iolistDTO.setIo_pcode(strPCode);
						int intPrice = iolistDTO.getIo_inout().equals("매입") ? proDTO.getP_iprice() : proDTO.getP_oprice();
						
						iolistDTO.setIo_price(intPrice);
					}
					
				}
			}

			
			break;
		}
		
		if(iolistDTO.getIo_pcode().isEmpty()) return;
		
		while(true) {
			System.out.printf("단가입력(%d) >> ", iolistDTO.getIo_price());
			String strPrice = scanner.nextLine();
			if(!strPrice.trim().isEmpty()) {
				try {
					int price = Integer.valueOf(strPrice);
					iolistDTO.setIo_price(price);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
			break;
		}
		
		while(true) {
			System.out.print("수량입력 >> ");
			String strQty = scanner.nextLine();
			if(!strQty.trim().isEmpty()) {
				try {
					
					int intQty = Integer.valueOf(strQty);
					iolistDTO.setIo_qty(intQty);
				} catch (Exception e) {
					System.out.println("수량은 숫자로만 입력!!");
					continue;
				}
				break;
				
			} break;
		}
		
		int intTotal = iolistDTO.getIo_price() * iolistDTO.getIo_qty();
		iolistDTO.setIo_total(intTotal);
		
		int ret = iolistDao.update(iolistDTO);
		if(ret > 0) System.out.println("데이터 변경 완료");
		else System.out.println("데이터 변경 실패");
		
		
		
	}
	
	protected void delete() {
		while(true) {
			System.out.print("삭제할 SEQ(-Q:quit) >> ");
			String strioCode = scanner.nextLine();
			long io_seq = Long.valueOf(strioCode);
			
			if(strioCode.equals("-Q")) break;
			IolistDTO iolistDTO = iolistDao.findById(io_seq);
			if(iolistDTO == null) {
				System.out.println("삭제할 SEQ가 존재하지 않음");
				continue;
			}
			
			System.out.print("삭제하시겠습니까?? (Enter:실행) >> ");
			String strYesNo = scanner.nextLine();
			if(strYesNo.trim().isEmpty()) {
				int ret = iolistDao.delete(io_seq);
				if(ret > 0) {
					System.out.println("삭제 완료");
					break;
				}
				else System.out.println("삭제 실패");
			}
			
			
			
			
		}
	}
	
	
//	public void iolistInsert() {
//		
//		Date date = new Date();
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//		String strDate = sd.format(date);
//		
//		System.out.println("===============================");
//		System.out.println("거래처 검색");
//		System.out.println("===============================");
//		System.out.print("거래처명 검색(Enter : 전체) >> ");
//		String strDName = scanner.nextLine();
//		
//		List<DeptDTO> deptList = deptDao.findByName(strDName);
//		
//		if(strDName.trim().isEmpty()) {
//			deptList = deptDao.selectAll();
//		} else {
//			deptList = deptDao.findByName(strDName);
//		}
//		
//		if(deptList == null || deptList.size() < 1) {
//			System.out.println("입력한 거래처가 없음");
//		}
//		this.viewDList(deptList);
//		
//		System.out.println("===============================");
//		System.out.print("매입매출 등록할 거래처코드 입력 >> ");
//		String strDCode = scanner.nextLine();
//		System.out.println("===============================");
//		
//		DeptDTO deptDTO = deptDao.findById(strDCode);
//		if(deptDTO == null) {
//			System.out.println("거래처 코드가 맞지 않음");
//			return;
//		} else {
//			this.viewDList(deptDTO);
//		}
//		
//		// 상품검색하는 부분
//		System.out.println("===============================");
//		System.out.println("상품명 검색");
//		System.out.println("===============================");
//		System.out.print("상품명 검색(Enter : 전체) >> ");
//		String strPName = scanner.nextLine();
//		List<ProductDTO> proList = proDao.findByName(strPName);
//		
//		if(strPName.trim().isEmpty()) {
//			proList = proDao.selectAll();
//		} else {
//			proList = proDao.findByName(strPName);
//		}
//		
//		if(proList == null || proList.size() < 1) {
//			System.out.println("입력한 상품명이 없음");
//			return;
//		} else {
//			this.viewPList(proList);
//		}
//		
//		
//		System.out.println("===============================");
//		System.out.print("매입매출 등록할 상품코드 입력 >> ");
//		String strPCode = scanner.nextLine();
//		System.out.println("===============================");
//		long io_pcode = Long.valueOf(strPCode);
//		
////		IolistDTO ioDTO = iolistDao.findById(io_seq);
//		IolistDTO ioDTO = iolistDao.findById(io_pcode);
//		if(ioDTO == null) {
//			System.out.println("거래처 코드가 없음");
//			return;
//		} else {
//			this.viewPList(proList);
//		}
//		
//		// 매입, 매출 구분 입력
//		int price = 0;
//		int iprice = 0;
//		int oprice = 0;
//		int total = 0;
//		int qty = 0;
//		qty = ioDTO.getIo_qty();
//		System.out.println("===============================");
//		System.out.print("매입,매출 등록>> ");
//		String strInout = scanner.nextLine();
//		System.out.println("===============================");
//		if(strInout.equals("매입")) {
//			iprice = ioDTO.getIo_price();
//			total = qty * iprice;
//		} else if(strInout.equals("매출")) {
//			oprice = ioDTO.getIo_price();
//			total = qty * oprice;
//		}
//		
//		System.out.println("INSERT");
//		
//		ioDTO.setIo_date(strDate);
//		ioDTO.setIo_price(price);
//		ioDTO.setIo_inout(strInout);
//		ioDTO.setIo_total(total);
//		
//		
//		iolistDao.update(ioDTO);
//		// tostring으로 추가된 데이터 보여주기
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	protected void viewDList(DeptDTO dto) {
//		System.out.print(dto.getD_code() + "\t");
//		System.out.print(dto.getD_name() + "\t");
//		System.out.print(dto.getD_ceo() + "\t");
//		System.out.print(dto.getD_tel() + "\t");
//		System.out.print(dto.getD_addr() + "\n");
//	}
//	
//	protected void viewDList(List<DeptDTO> deptList) {
//		System.out.println("=====================================");
//		System.out.println("거래처 리스트");
//		System.out.println("-------------------------------------");
//		System.out.println("코드\t상호\t대표\t전화\t주소");
//		System.out.println("-------------------------------------");
//		for(DeptDTO dto : deptList) {
//			this.viewDList(dto);
//		}
//	}
//	
//	protected void viewPList(ProductDTO dto) {
//			System.out.print(dto.getP_code() + "\t");
//			System.out.print(dto.getP_name() + "\t");
//			System.out.print(dto.getP_iprice() + "\t");
//			System.out.print(dto.getP_oprice() + "\t");
//			System.out.print(dto.getP_vat() + "\n");
//	}
//	protected void viewPList(List<ProductDTO> proList) {
//		System.out.println("=====================================");
//		System.out.println("상품 리스트");
//		System.out.println("-------------------------------------");
//		System.out.println("코드\t상품명\t매입단가\t매출단가\t과세여부");
//		System.out.println("-------------------------------------");
//		for(ProductDTO dto : proList) {
//			this.viewPList(dto);
//		}
//
//	}
	
	
}
