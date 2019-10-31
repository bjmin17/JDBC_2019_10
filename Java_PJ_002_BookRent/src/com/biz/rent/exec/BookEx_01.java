package com.biz.rent.exec;

import com.biz.rent.service.book.BookServiceV1;

public class BookEx_01 {

	public static void main(String[] args) {
		
		BookServiceV1 bs = new BookServiceV1();
		
//		bs.viewAllList();
//		bs.viewNameList();
//		
		bs.viewNameAndAuth();
//		bs.BookMenu();
		
		
	}
}
