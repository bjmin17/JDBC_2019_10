package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.biz.cbt.persistence.CbtDTO;

public class CbtServiceV2 extends CbtServiceV1{

	// 내가 입력한 오답을 기록할 리스트
	List<String> wrongList;
	
	public CbtServiceV2() {
		wrongList = new ArrayList<String>();
	}
	@Override
	protected void cbtSolve() {
		// TODO 문제풀이
		
		int num = 0;
		
		int seq =0;
		String seqs = null;
		Random rnd = new Random();
		int intSeq = 0;
		int intCount = 0;
		int score = 0;
		
		// 문제 개수를 가져오기 위한 리스트
		List<CbtDTO> cbtList = cbtdao.selectAll();
		// 문제 번호를 매기기 위한 리스트 생성
		List<String> couList = new ArrayList<String>();
		for(int i = 0 ; i < cbtList.size() ; i++) {
			++seq;
			seqs = String.format("P%04d", seq);
			couList.add(seqs);
		}
		Collections.shuffle(couList);
		
		// 리스트들 생성
		// 문제를 기록할 리스트
		List<CbtDTO> QList = new ArrayList<CbtDTO>();
		
		// 정답을 기록할 리스트
		List<CbtDTO> yesList = new ArrayList<CbtDTO>();
		
		// 오답을 기록할 리스트
		List<CbtDTO> noList = new ArrayList<CbtDTO>();
		
		List<CbtDTO> repList = new ArrayList<CbtDTO>();
		while(true) {
			int intRan = rnd.nextInt(20) + 1;
			int intChance = 0;
			String strAns = null;
			String strYN = null;
			
			if(num < 1) {
				
			} else {
				
				if(num % 5 ==0) {

					// 정답 리스트 공개
					System.out.println("\n\n");
					this.yesList(yesList);
					
					// 오답 리스트 공개
					this.noList(noList);
					System.out.println("\n\n");
					
					this.repsList(repList);
					// 5문제마다 보여줄 리스트
					repList = new ArrayList<CbtDTO>();
				}
				
			}
			while(intChance < 2) {
				
				intSeq = intRan;
				// P0001 형식으로 찾기
				CbtDTO cbtDTO = cbtdao.findById(couList.get(num));
				// 답을 담을 리스트 생성
				List<String> SolList = new ArrayList<String>();
				SolList.add(cbtDTO.getCb_fir());
				SolList.add(cbtDTO.getCb_sec());
				SolList.add(cbtDTO.getCb_thi());
				SolList.add(cbtDTO.getCb_fou());
				
				Collections.shuffle(SolList);
				System.out.println("=================================");
				System.out.println((num+1) + ". " + cbtDTO.getCb_prob());
				cbtDTO.setCb_num(num+1);
				System.out.println("---------------------------------");
				System.out.println("1번 : " + SolList.get(0));
				cbtDTO.setCb_fir(SolList.get(0));
				System.out.println("2번 : " + SolList.get(1));
				cbtDTO.setCb_sec(SolList.get(1));
				System.out.println("3번 : " + SolList.get(2));
				cbtDTO.setCb_thi(SolList.get(2));
				System.out.println("4번 : " + SolList.get(3));
				cbtDTO.setCb_fou(SolList.get(3));
				System.out.println("---------------------------------");
				System.out.print("정답(-Q:quit) >> ");
				strAns = scanner.nextLine();
				if(strAns.trim().isEmpty()) continue;
				else if(strAns.trim().equals("-Q")) break;
				int intAns = Integer.valueOf(strAns);
				
				
				if(intAns == 1) strAns = SolList.get(0);
				else if(intAns == 2) strAns = SolList.get(1);
				else if(intAns == 3) strAns = SolList.get(2);
				else if(intAns == 4) strAns = SolList.get(3);
				
				if(strAns.equals(cbtDTO.getCb_ans())) {
					System.out.println("정답입니다(Enter:다음문제풀기,-Q:종료)");
					if(intChance == 0) {
						yesList.add(cbtDTO);
						repList.add(cbtDTO);
					}
					
					score += 5;
					strYN = scanner.nextLine();
					if(strYN.trim().isEmpty()) {
						
					} else if(strYN.equals("-Q")) break;
				} else {
					System.out.println("틀렸습니다(R:다시풀기, Enter:다음문제풀기)");
					if(intChance == 0)	{
						noList.add(cbtDTO);
						repList.add(cbtDTO);
						wrongList.add(intAns+"");
					}
					
					strYN = scanner.nextLine();
					if(strYN.equals("R")) {
//						intSeq = intRan;
						intChance++;
						continue;
					}
					else if(strYN.equals("N")) {
						break;
					}
				}// if
				break;
			}// 안쪽 while문 끝
			num++;
			
			if(strAns.trim().equals("-Q") || strYN.trim().equals("-Q") || num>19) {
				System.out.println("\n\n");
				this.yesList(yesList);
				this.noList(noList);
				System.out.println("\n\n");
				System.out.println("점수 : " + score);
				break;
			}
//			if(num > 19) break;
		} // 바깥쪽 while 끝
		
		
	}// 문제풀이 메서드 끝
	private void repsList(List<CbtDTO> repsList) {

		System.out.println("***5문제 마다 풀었던 문제 다시 보여주기***");
		for(CbtDTO dto: repsList) {
			System.out.println("=================================");
			System.out.println(dto.getCb_num() + "번 문제 : ");
			System.out.println(dto.getCb_prob());
			System.out.println("---------------------------------");
			System.out.println("1번 : " + dto.getCb_fir());
			System.out.println("2번 : " + dto.getCb_sec());
			System.out.println("3번 : " + dto.getCb_thi());
			System.out.println("4번 : " + dto.getCb_fou());
			System.out.println("---------------------------------");
			if(dto.getCb_ans().equals(dto.getCb_fir()))
			System.out.println("정답 : 1번");
			else if(dto.getCb_ans().equals(dto.getCb_sec()))
			System.out.println("정답 : 2번" );
			else if(dto.getCb_ans().equals(dto.getCb_thi()))
				System.out.println("정답 : 3번");
			else if(dto.getCb_ans().equals(dto.getCb_fou()))
				System.out.println("정답 : 4번");
			System.out.println("=================================");
		}
	}
	protected void yesList(List<CbtDTO> yesList) {
		System.out.println("***정답 리스트 공개***");
		for(CbtDTO dto: yesList) {
			System.out.println("=================================");
			System.out.println(dto.getCb_num() + "번 문제 : ");
			System.out.println(dto.getCb_prob());
			System.out.println("---------------------------------");
			System.out.println("1번 : " + dto.getCb_fir());
			System.out.println("2번 : " + dto.getCb_sec());
			System.out.println("3번 : " + dto.getCb_thi());
			System.out.println("4번 : " + dto.getCb_fou());
			System.out.println("---------------------------------");
			if(dto.getCb_ans().equals(dto.getCb_fir()))
			System.out.println("정답 : 1번");
			else if(dto.getCb_ans().equals(dto.getCb_sec()))
			System.out.println("정답 : 2번" );
			else if(dto.getCb_ans().equals(dto.getCb_thi()))
				System.out.println("정답 : 3번");
			else if(dto.getCb_ans().equals(dto.getCb_fou()))
				System.out.println("정답 : 4번");
			System.out.println("=================================");
		}
	}
	
	protected void noList(List<CbtDTO> yesList) {
		System.out.println("***오답 리스트 공개***");
		int wrongNum = 0;
		for(CbtDTO dto: yesList) {
			
			System.out.println("=================================");
			System.out.println(dto.getCb_num() + "번 문제 : ");
			System.out.println(dto.getCb_prob());
			System.out.println("---------------------------------");
			System.out.println("1번 : " + dto.getCb_fir());
			System.out.println("2번 : " + dto.getCb_sec());
			System.out.println("3번 : " + dto.getCb_thi());
			System.out.println("4번 : " + dto.getCb_fou());
			System.out.println("---------------------------------");
			if(dto.getCb_ans().equals(dto.getCb_fir()))
			System.out.println("정답 : 1번");
			else if(dto.getCb_ans().equals(dto.getCb_sec()))
			System.out.println("정답 : 2번" );
			else if(dto.getCb_ans().equals(dto.getCb_thi()))
				System.out.println("정답 : 3번");
			else if(dto.getCb_ans().equals(dto.getCb_fou()))
				System.out.println("정답 : 4번");
			System.out.printf("내가 입력한 오답 >> %s\n", wrongList.get(wrongNum));
			
			System.out.println("=================================");
			wrongNum++;
		}
	}
}
