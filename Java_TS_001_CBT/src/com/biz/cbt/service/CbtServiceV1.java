package com.biz.cbt.service;

import java.util.List;
import java.util.Scanner;

import com.biz.cbt.config.DBConnection;
import com.biz.cbt.dao.CbtDao;
import com.biz.cbt.persistence.CbtDTO;

public class CbtServiceV1 {
	protected CbtDao cbtdao;
	protected Scanner scanner;
	
	public CbtServiceV1() {
		cbtdao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(CbtDao.class);
		scanner = new Scanner(System.in);
	}
	
	public void viewAll() {
		System.out.println("====================================");
		System.out.println("문제 전체 조회");
		System.out.println("====================================");
		System.out.println("문제번호\t문제\t1번\t2번\t3번\t4번\t정답");
		System.out.println("-----------------------------------");
		List<CbtDTO> cbtList = cbtdao.selectAll();
		this.viewToStringList(cbtList);
		System.out.println("====================================");
	}
	
	private void viewList(List<CbtDTO> cbtList) {
		for(CbtDTO dto : cbtList) {
			this.viewList(dto);
		}
	}
	private void viewList(CbtDTO dto) {
		System.out.println("문제번호 : " + dto.getCb_num());
		System.out.println("문제 : " + dto.getCb_prob());
		System.out.println("------------------------------------");
		System.out.println("1번 : " + dto.getCb_fir());
		System.out.println("2번 : " + dto.getCb_sec());
		System.out.println("3번 : " + dto.getCb_thi());
		System.out.println("4번 : " + dto.getCb_fou());
		System.out.println("------------------------------------");
		System.out.println("정답 : " + dto.getCb_ans());
		System.out.println("------------------------------------");
	}
	
	private void viewToStringList(List<CbtDTO> cbtList) {
		for(CbtDTO dto : cbtList) {
			System.out.print(dto.getCb_pcode() +"\t");
			System.out.println(dto.getCb_prob());
		}
	}
	
	
	public void viewMenu() {
		// TODO 문제 메뉴를 선택하는 부분
		while(true) {
			
			System.out.println("==============================");
			System.out.println("정보처리기사 필기 문제은행V1 메뉴선택");
			System.out.println("==============================");
			System.out.println("1.문제입력 2.문제풀이 0.종료");
			System.out.println("------------------------------");
			System.out.print("선택 >> ");
			String strMenu = scanner.nextLine();
			
			try {
				int intMenu = Integer.valueOf(strMenu);
				if(intMenu == 0) break;
				else if(intMenu == 1) this.cbtInsert();
				else if(intMenu == 2) this.cbtSolve();
				
			} catch (Exception e) {

				System.out.println("번호를 입력해주세요");
				continue;
			}
			
		}
		
	}
	
	public void cbtInsert() {
		//TODO 문제등록 메뉴
		while(true) {
			
			System.out.println("==============================");
			System.out.println("문제입력 메뉴");
			System.out.println("==============================");
			System.out.println("1.문제등록 2.문제수정 3.문제삭제 0.종료");
			System.out.println("------------------------------");
			System.out.print("선택 >> ");
			String strMenu = scanner.nextLine();
			
			try {
				int intMenu = Integer.valueOf(strMenu);
				
				if(intMenu == 0) break;
				else if(intMenu == 1) this.insert();
				else if(intMenu == 2) this.update();
				else if(intMenu == 3) this.delete();
				
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요");
				continue;
			}
		}
		
		
		
	}
	public void insert() {
		//TODO 문제 등록
		CbtDTO cbtDTO = new CbtDTO();
		System.out.println("=================================");
		System.out.println("문제등록");
		System.out.println("=================================");
		String strStop = "Stop";
		
		// 문제 등록 중 문제 코드 작성 부분
		while(true) {
			System.out.print("문제코드 자동생성 (Enter:계속 진행, Q:quit)>> ");
			String strPCode = scanner.nextLine();
			if(strPCode.equals("Q")) {
				strStop = "Q";
				break;
			}
			String strTMPCode = cbtdao.getMaxPCode();
			int intPCode = Integer.valueOf(strTMPCode.substring(1));
			intPCode++;
			strPCode = strTMPCode.substring(0,1);
			strPCode += String.format("%04d", intPCode);
			
			System.out.println("생성된 코드 : " + strPCode);
			cbtDTO.setCb_pcode(strPCode);
			
			break;
		}
		if(strStop.trim().equals("Q")) return;

		// 문제 등록 중 문제명 작성 부분
		while(true) {
			System.out.print("문제입력 (Q:quit)>> ");
			String strProb = scanner.nextLine();
			if(strProb.equals("Q")) {
				strStop = "Q";
				break;
			}
			else if(strProb.trim().isEmpty()) continue;
			cbtDTO.setCb_prob(strProb);
			break;
		}
		if(strStop.trim().equals("Q")) return;
		
		// 문제 등록 중 답안1번 작성 부분
		while(true) {
			System.out.print("답안1번 (Q:quit)>> ");
			String strFir = scanner.nextLine();
			if(strFir.equals("Q")) {
				strStop = "Q";
				break;
			}
			else if(strFir.trim().isEmpty()) continue;
			cbtDTO.setCb_fir(strFir);
			break;
		}
		if(strStop.trim().equals("Q")) return;

		// 문제 등록 중 답안2번 작성 부분
		while(true) {
			System.out.print("답안2번 (Q:quit)>> ");
			String strSec = scanner.nextLine();
			if(strSec.equals("Q")) {
				strStop = "Q";
				break;
			}
			else if(strSec.trim().isEmpty()) continue;
			cbtDTO.setCb_sec(strSec);
			break;
		}
		if(strStop.trim().equals("Q")) return;
		
		// 문제 등록 중 답안3번 작성 부분
		while(true) {
			System.out.print("답안3번 (Q:quit)>> ");
			String strThi = scanner.nextLine();
			if(strThi.equals("Q")) {
				strStop = "Q";
				break;
			}
			else if(strThi.trim().isEmpty()) continue;
			cbtDTO.setCb_thi(strThi);
			break;
		}
		if(strStop.trim().equals("Q")) return;
		
		// 문제 등록 중 답안4번 작성 부분
		while(true) {
			System.out.print("답안4번 (Q:quit)>> ");
			String strFou = scanner.nextLine();
			if(strFou.equals("Q")) {
				strStop = "Q";
				break;
			}
			else if(strFou.trim().isEmpty()) continue;
			cbtDTO.setCb_fou(strFou);
			break;
		}
		if(strStop.trim().equals("Q")) return;
		
		// 문제 등록 중 정답 문항 작성 부분
		while(true) {
			System.out.print("정답 문항(번호만) (Q:quit)>> ");
			String strAns = scanner.nextLine();
			if(strAns.equals("Q")) {
				strStop = "Q";
				break;
			}
			else if(strAns.trim().isEmpty()) continue;
			
			int intAns = Integer.valueOf(strAns);
			if(intAns == 1) strAns = cbtDTO.getCb_fir();
			else if(intAns == 2) strAns = cbtDTO.getCb_sec();
			else if(intAns == 3) strAns = cbtDTO.getCb_thi();
			else if(intAns == 4) strAns = cbtDTO.getCb_fou();
			cbtDTO.setCb_ans(strAns);
			break;
		}
		if(strStop.trim().equals("Q")) return;
		
		int ret = cbtdao.insert(cbtDTO);
		if(ret > 0 )System.out.println("※ 등록되었습니다.");
		else System.out.println("문제 등록 실패");
	}
	public void update() {
		//TODO 문제 수정
		String strStop = "";
		while(true) {
			System.out.println("=================================");
			System.out.println("문제수정");
			System.out.println("=================================");
			System.out.println("수정하고 싶은 문제 코드(Enter:문제 전체보기) >> ");
			String strPCode = scanner.nextLine();
			if(strPCode.trim().isEmpty()) {
				// 문제 전체를 조회하는 메서드
				this.viewAll();
				// 그리고 다시 반복문 시작
				continue;
			} else if(strPCode.equals("Q")) {
				strStop = "Q";
				break;
			} else if(cbtdao.findById(strPCode.toUpperCase()) == null) {
				System.out.println("입력한 문제 코드에 일치하는 데이터가 없음");
				continue;
			}
			strPCode = strPCode.toUpperCase();
			
			// 위에서 입력한 문제코드로, DB에 저장되어 있는 문제를 DTO형식으로 가져온다.
			CbtDTO cbtDTO = cbtdao.findById(strPCode);
			System.out.println("=================================");
			System.out.println("문제수정");
			System.out.println("=================================");
			
			while(true) {
				System.out.printf("변경할 문제입력(%s) (Enter:유지, Q:quit)>> ",cbtDTO.getCb_prob());
				String strProb = scanner.nextLine();
				if(strProb.trim().isEmpty()) {
					break;
				}else if(strProb.equals("Q")) {
					strStop = "Q";
					break;
				}
				
				cbtDTO.setCb_prob(strProb);
				break;
			}
			if(strStop.trim().equals("Q")) return;
			
			while(true) {
				System.out.printf("변경할 1번 입력(%s) (Enter:유지, Q:quit)>> ",cbtDTO.getCb_fir());
				String strFir = scanner.nextLine();
				if(strFir.trim().isEmpty()) {
					break;
				}else if(strFir.equals("Q")) {
					strStop = "Q";
					break;
				}
				cbtDTO.setCb_fir(strFir);
				break;
			}
			if(strStop.trim().equals("Q")) return;
			
			while(true) {
				System.out.printf("변경할 2번 입력(%s) (Enter:유지, Q:quit)>> ",cbtDTO.getCb_sec());
				String strSec = scanner.nextLine();
				if(strSec.trim().isEmpty()) {
					break;
				}else if(strSec.equals("Q")) {
					strStop = "Q";
					break;
				}
				cbtDTO.setCb_sec(strSec);
				break;
			}
			if(strStop.trim().equals("Q")) return;
			
			while(true) {
				System.out.printf("변경할 3번 입력(%s) (Enter:유지, Q:quit)>> ",cbtDTO.getCb_thi());
				String strThi = scanner.nextLine();
				if(strThi.trim().isEmpty()) {
					break;
				}else if(strThi.equals("Q")) {
					strStop = "Q";
					break;
				}
				cbtDTO.setCb_thi(strThi);
				break;
			}
			if(strStop.trim().equals("Q")) return;
			
			while(true) {
				System.out.printf("변경할 4번 입력(%s) (Enter:유지, Q:quit)>> ",cbtDTO.getCb_fou());
				String strFou = scanner.nextLine();
				if(strFou.trim().isEmpty()) {
					break;
				}else if(strFou.equals("Q")) {
					strStop = "Q";
					break;
				}
				cbtDTO.setCb_fou(strFou);
				break;
			}
			if(strStop.trim().equals("Q")) return;
			
			while(true) {
				System.out.printf("변경할 정답 입력(%s) (Enter:유지, Q:quit)>> ",cbtDTO.getCb_ans());
				String strAns = scanner.nextLine();
				if(strAns.trim().isEmpty()) {
					break;
				}else if(strAns.equals("Q")) {
					strStop = "Q";
					break;
				}
				cbtDTO.setCb_ans(strAns);
				break;
			}
			if(strStop.trim().equals("Q")) return;
			
			int ret = cbtdao.update(cbtDTO);
			if(ret > 0 )System.out.println("※ 수정되었습니다.");
			else System.out.println("문제 수정 실패");
			break;
		}
		
			
		
	}
	public void delete() {
		//TODO 문제 삭제
		while(true) {
			
			System.out.println("=================================");
			System.out.println("문제삭제");
			System.out.println("=================================");
			System.out.print("삭제하고 싶은 문제 코드(Enter:문제 전체보기) >> ");
			String strPCode = scanner.nextLine();
			strPCode = strPCode.toUpperCase();
			if(strPCode.trim().isEmpty()) {
				this.viewAll();
				continue;
			} else if(cbtdao.findById(strPCode) == null) {
				System.out.println("입력한 문제 코드에 일치하는 데이터가 없음");
				continue;
			}
			
			System.out.println("정말 삭제하시겠습니까?(Enter:Yes, 아니면 아무키나 입력)");
			String strYesNo = scanner.nextLine();
			if(strYesNo.trim().isEmpty()) {
				int ret = cbtdao.delete(strPCode);
				if(ret > 0 )System.out.println("※ 삭제되었습니다");
				else System.out.println("문제 삭제 실패");
				break;
			}
		}

		
	}
	protected void cbtSolve() {
		
	}
	
}
