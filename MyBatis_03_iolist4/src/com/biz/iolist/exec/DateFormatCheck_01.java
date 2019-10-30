package com.biz.iolist.exec;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatCheck_01 {

	public static void main(String[] args) {

		/*
		 * 안드로이드에서 써먹는 트릭
		 * SimpleDateFormat과 try{} 을 사용한 방법으로 검증
		 * 
		 * 입력하는 날짜형식을 지정하고 싶을 때
		 * 입력하는 사람이 지정된 형식대로 입력하지 않으면
		 * 메시지를 보여주고 다시 입력받도록 할 수 있다.
		 */
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			sd.parse("20190101");
		} catch (ParseException e) {
			System.out.println("날짜 형식이 잘못되었습니다");
		}

	}
	
}
