package com.biz.iolist.exec;

import com.biz.iolist.service.dept.DeptServiceV2;
import com.biz.iolist.service.dept.DeptServiceV3;

public class DeptEx_02 {

	public static void main(String[] args) {
		DeptServiceV2 ds = new DeptServiceV2();
//		ds.deptMenu();
		
		DeptServiceV3 da = new DeptServiceV3();
//		da.viewNameList();
//		da.deptInsert();
		da.deptMenu();
		
		
	}
	
}
