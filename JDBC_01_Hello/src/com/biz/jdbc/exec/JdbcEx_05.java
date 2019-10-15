package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCServiceV2;

public class JdbcEx_05 {

	public static void main(String[] args) {
		
		ScoreJDBCServiceV2 sc = new ScoreJDBCServiceV2();
		
		List<ScoreVO> stdList = sc.findByName("'갈한수' OR 1 = 1"); // 모든 데이터 날릴 수 있음
		// 데이터베이스 인젝션 공격
		for(ScoreVO sVO : stdList) {
			System.out.println(sVO.toString());
			//ScoreVO(s_id=30, s_std=아동은, s_score=95, s_rem=null)
		}
	}
}
