package com.biz.iolist.exec;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.service.IolistServiceV1;

public class IolistEx_02 {

	public static void main(String[] args) {
		
		IolistServiceV1 ioService = new IolistServiceV1();
		
		ioService.viewAllList();
		
		
		
		
	}
}
