package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.biz.cbt.persistence.CbtDTO;

public class CbtServiceV2 extends CbtServiceV1{

	// 내가 입력한 오답을 기록할 리스트
	List<String> wrongList;
	// 문제마다 입력한 답을 기록할 리스트
	List<String> ansList;
	
	public CbtServiceV2() {
		wrongList = new ArrayList<String>();
		ansList = new ArrayList<String>();
	}
	@Override
	protected void cbtSolve() {
		// TODO 문제풀이
		
		int num = 0;
		
		// P0001의 형태로 코드를 만들 때, seq를 숫자에 넣으며 생성하기 위한 준비단계
		int seq =0;
		String seqs = null;
		Random rnd = new Random();

		// 점수를 입력할 변수 생성
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
		// 문제를 섞어주는 부분
		Collections.shuffle(couList);
		
		// 리스트들 생성
		// 정답을 기록할 리스트
		List<CbtDTO> yesList = new ArrayList<CbtDTO>();
		
		// 오답을 기록할 리스트
		List<CbtDTO> noList = new ArrayList<CbtDTO>();
		
		// 5문제씩 보여줄 때 사용할 리스트 생성
		List<CbtDTO> repList = new ArrayList<CbtDTO>();
		while(true) {
			// 문제를 틀리면 한번의 기회를 더 제공하기 위해서 생성
			int intChance = 0;
			// 새로운 문제를 낼 때마다 정답(strAns)과 다음문제로 넘어갈지를 묻는 변수(strYN)를 선언하고 초기화 수행
			String strAns = null;
			String strYN = null;
			// num는 문제번호로
			// num가 0일 때, 5문제마다 문제리스트를 보여주는 코드를 피하기 위해 만든 if문
			if(num < 1) {
				
			} else {
				// 5문제마다 문제리스트를 보여준다.
				if(num % 5 ==0) {

					System.out.println("\n\n");
					this.repsList(repList);
					
					// 5문제마다 보여줄 리스트
					repList = new ArrayList<CbtDTO>();
				}
				
			}
			// 기회(intChance)가 1회 이하일때 까지만 다시 문제를 풀 수 있는 기회 제공
			while(intChance < 2) {
				
				// P0001 형식으로 찾기,, 숫자는 num로 값을 입력
				CbtDTO cbtDTO = cbtdao.findById(couList.get(num));
				
				// 보기를 담을 리스트 생성
				List<String> SolList = new ArrayList<String>();
				SolList.add(cbtDTO.getCb_fir());
				SolList.add(cbtDTO.getCb_sec());
				SolList.add(cbtDTO.getCb_thi());
				SolList.add(cbtDTO.getCb_fou());
				
				// 보기를 섞기 위한 코드
				Collections.shuffle(SolList);
				
				// 1. 문제~~~~로 옳은 것은?
				// ------------------------
				// 보기 1번
				// 보기 2번
				// 보기 3번
				// 보기 4번
				System.out.println("=================================");
				System.out.println((num+1) + ". " + cbtDTO.getCb_prob());
				cbtDTO.setCb_num(num+1);
				System.out.println("---------------------------------");
				System.out.println("① " + SolList.get(0));
				// 매번 보기를 보여줄때마다
				// 랜덤으로 보여주는 보기를 DTO에 담아서 데이터로 저장하기 위한 코드
				cbtDTO.setCb_fir(SolList.get(0));
				System.out.println("② " + SolList.get(1));
				cbtDTO.setCb_sec(SolList.get(1));
				System.out.println("③ " + SolList.get(2));
				cbtDTO.setCb_thi(SolList.get(2));
				System.out.println("④ " + SolList.get(3));
				cbtDTO.setCb_fou(SolList.get(3));
				System.out.println("---------------------------------");
				System.out.print("정답(-Q:quit) >> ");
				strAns = scanner.nextLine();
				
				// 정답이 비었다면 다시 입력하게 continue;
				if(strAns.trim().isEmpty()) continue;
				
				// -Q를 입력했다면 끝내기
				else if(strAns.trim().equals("-Q")) break;
				int intAns = Integer.valueOf(strAns);
				
				// 문제마다 입력한 정답을 기록할 리스트
				ansList.add(intAns+"");
				
				// 정답이 1이면 정답(strAns)에 보기 1번의 값을 입력하고 
				if(intAns == 1) strAns = SolList.get(0);
				else if(intAns == 2) strAns = SolList.get(1);
				else if(intAns == 3) strAns = SolList.get(2);
				else if(intAns == 4) strAns = SolList.get(3);
				
				// 입력한 정답(strAns)과 기존에 저장되어 있던 정답(cbtDTO.getCb_ans)를 비교하며
				// 정답인지 오답인지를 보여주는 부분
				if(intAns > 4 || intAns <1) {
					System.out.println("정답은 1부터 4까지 입력하세요");
					continue;
				}
				if(strAns.equals(cbtDTO.getCb_ans())) {
					System.out.println("※ 정답입니다(Enter:다음문제풀기,-Q:종료)");
					// intChance는 처음엔 0, 한번 더 풀면 1로 늘어나며
					// 처음에 문제를 맞추면 정답리스트에 담고, 틀리면 오답리스트에 담기 위해서 조건을 생성
					if(intChance == 0) {
						// 정답리스트를 생성하기 위해서 yesList에 담는다.
						yesList.add(cbtDTO);
						// 5문제씩 보여주기 위해 repList에 담는다.
						repList.add(cbtDTO);
					}
					
					// 맞으면 점수를 5점 더해준다.
					score += 5;
					// 다음문제를 풀지, 종료할지 strYN에 입력
					strYN = scanner.nextLine();
					if(strYN.trim().isEmpty()) {
						
					} else if(strYN.equals("-Q")) break;
				} else {
					System.out.println("※ 틀렸습니다(R:다시풀기, Enter:다음문제풀기)");
					if(intChance == 0)	{
						// 틀리면 오답리스트에 담고
						noList.add(cbtDTO);
						// 5문제마다 보여주기 위한 리스트(repList)에 담으며
						repList.add(cbtDTO);
						// 내가 입력한 답을 모아두는 리스트(wrongList)에도 값을 저장
						wrongList.add(intAns+"");
					}
					
					// 다시풀지, 다음문제를 풀지 입력하는 부분
					strYN = scanner.nextLine();
					if(strYN.equals("R")) {
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
			
			// 정답(strAns) 입력 부분에서, 반복여부(strYN)에서, 또는 문제가 20문제가 된다면
			// break문으로 반복문을 종료하고
			// 그동안 입력했던 정답리스트와 오답리스트를 보여준다.
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
		// 기존에 내가 입력한 답을 가져오기 위한 리스트(ansList)의 인덱스(inputNum)
		int inputNum = 0;
		// 지금 보여주는 문제의 답을 집어넣을 변수
		int ansNum = 0;

		System.out.println("***5문제 마다 풀었던 문제 다시 보여주기***");
		for(CbtDTO dto: repsList) {
			System.out.println("=================================");
			System.out.println(dto.getCb_num() + "번 문제 : ");
			System.out.println(dto.getCb_prob());
			System.out.println("---------------------------------");
			System.out.println("① " + dto.getCb_fir());
			System.out.println("② " + dto.getCb_sec());
			System.out.println("③ " + dto.getCb_thi());
			System.out.println("④ " + dto.getCb_fou());
			System.out.println("---------------------------------");
			if(dto.getCb_ans().equals(dto.getCb_fir()))	{
				System.out.println("정답 : 1번");
				ansNum = 1;
			}
			else if(dto.getCb_ans().equals(dto.getCb_sec())) {
				System.out.println("정답 : 2번" );
				ansNum = 2;
			}
			else if(dto.getCb_ans().equals(dto.getCb_thi())) {
				System.out.println("정답 : 3번");
				ansNum = 3;
			}
			else if(dto.getCb_ans().equals(dto.getCb_fou())) {
				System.out.println("정답 : 4번");
				ansNum = 4;
			}
			
			// 앞에서 주입된 정답(ansNum)과 기존에 내가 입력했던 정답들(ansList)의 값을 비교하고
			// 맞았는지 틀렸는지를 알려주는 코드
			if(ansList.get(inputNum).equals(ansNum+"")) {
				System.out.println("맞았다");
			} else {
				System.out.println("틀렸다");
			}
			System.out.printf("내가 입력한 답 >> %s\n", ansList.get(inputNum++));
			
			System.out.println("=================================");
		}
	}
	protected void yesList(List<CbtDTO> yesList) {
		System.out.println("***정답 리스트 공개***");
		for(CbtDTO dto: yesList) {
			System.out.println("=================================");
			System.out.println("문제 번호 : " + dto.getCb_num());
			System.out.println(dto.getCb_prob());
			System.out.println("---------------------------------");
			System.out.println("① " + dto.getCb_fir());
			System.out.println("② " + dto.getCb_sec());
			System.out.println("③ " + dto.getCb_thi());
			System.out.println("④ " + dto.getCb_fou());
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
			System.out.println("문제 번호 : " + dto.getCb_num());
			System.out.println(dto.getCb_prob());
			System.out.println("---------------------------------");
			System.out.println("① " + dto.getCb_fir());
			System.out.println("② " + dto.getCb_sec());
			System.out.println("③ " + dto.getCb_thi());
			System.out.println("④ " + dto.getCb_fou());
			System.out.println("---------------------------------");
			if(dto.getCb_ans().equals(dto.getCb_fir()))
			System.out.println("정답 : 1번");
			else if(dto.getCb_ans().equals(dto.getCb_sec()))
			System.out.println("정답 : 2번" );
			else if(dto.getCb_ans().equals(dto.getCb_thi()))
				System.out.println("정답 : 3번");
			else if(dto.getCb_ans().equals(dto.getCb_fou()))
				System.out.println("정답 : 4번");
			System.out.printf("내가 입력한 오답 >> %s\n", wrongList.get(wrongNum++));
			
			System.out.println("=================================");
//			wrongNum++;
		}
	}
}