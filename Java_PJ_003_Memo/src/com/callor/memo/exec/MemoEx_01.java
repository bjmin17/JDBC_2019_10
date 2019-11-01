package com.callor.memo.exec;

import com.callor.memo.service.MemoServiceV1;

public class MemoEx_01 {

	public static void main(String[] args) {
		
		MemoServiceV1 ms = new MemoServiceV1();
		
		ms.viewAllList();
		ms.insert();
		
	}
}
