package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

/*
 * 1. 학생이름을 입력받아서
 * 2. 성적리스트를 보여주고 --- 여기선 1,2는 생략하고 3번부터 진행한것
 * 3. 학번 입력 받고
 * 4. 학번에 해당하는 성적리스트 보여주고
 * 5. 리스트를 보고 ID를 입력받아서 
 * 6. 해당 ID의 score 값을 삭제 
 * 
 */
public class ScoreDelete_01 {

	public static void main(String[] args) {
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		
		Scanner scanner = new Scanner(System.in);
		
		String strStNum = null;
		List<ScoreVO> scoreList = null;
		/*
		 * 학번을 입력받아서
		 * 학번이 학생테이블에 있으면 리스트를 보여주고
		 * 없으면 다시 입력받기
		 */
		while(true) {
			
			System.out.print("학번 (-Q:quit) >> ");
			strStNum = scanner.nextLine();
			if(strStNum.equals("-Q")) {
				//
				break;
			}
			
			// 학번조회
			scoreList = sc.findByStNum(strStNum);
			if(scoreList == null || scoreList.size() < 1) {
				System.out.println("학생정보에 학번이 없습니다");
				System.out.println("학생정보를 먼저 등록해야 합니다");
				continue;
			}
			break;
		}
		if(strStNum.equals("-Q")) return; // Q가 나오면 종료,, 아니면 다음으로 진행
		
		while(true) {
			System.out.println("=========================");
			System.out.println("ID\t학생이름\t과목\t점수");
			System.out.println("=========================");
			for(ScoreVO vo : scoreList) {
				System.out.print(vo.getS_id() + "\t");
				System.out.print(vo.getSt_name() + "\t");
				System.out.print(vo.getSb_name() + "\t");
				System.out.print(vo.getS_score() + "\n");
			}
			System.out.println("=========================");
			System.out.print("삭제할 ID (-Q:quit) >> ");
			String strID = scanner.nextLine();
			if(strID.equals("-Q")) {
				System.out.println("종료!!");
				break;
			}
			
			try {
				
				long longID = Long.valueOf(strID);
				int ret = sc.delete(longID);
				if( ret > 0 ) {
					System.out.println("삭제 완료");
					scoreList = sc.findByStNum(strStNum);
							
				} else {
					System.out.println("삭제 실패");
				}
				continue;
				
			} catch (Exception e) {
				System.out.println("ID는 숫자만 가능!!!");
				continue;
			}
		}
	}
}
