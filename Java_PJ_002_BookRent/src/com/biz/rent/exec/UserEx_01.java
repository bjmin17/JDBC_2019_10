package com.biz.rent.exec;

import com.biz.rent.service.user.UserServiceV1;

public class UserEx_01 {

	public static void main(String[] args) {
		
		UserServiceV1 us = new UserServiceV1();
		us.viewAllList();
		us.viewNameList();
		us.viewNameAndTel();
		
		
	}
}
