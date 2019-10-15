package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCServiceV3;

public class JdbcEx_06 {

	public static void main(String[] args) {
		
		ScoreJDBCServiceV3 sc = new ScoreJDBCServiceV3();
		
		List<ScoreVO> scoreList = sc.findById(30);
		for(ScoreVO s : scoreList) {
			System.out.println(s); //ScoreVO(s_id=30, s_std=아동은, s_score=95, s_rem=null)
		}
		
		
	}
}
