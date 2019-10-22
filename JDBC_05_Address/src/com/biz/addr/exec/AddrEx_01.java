package com.biz.addr.exec;

import com.biz.addr.persistence.dao.AddrDaoImp;
import com.biz.addr.service.AddrCUDServiceV1;
import com.biz.addr.service.AddrServiceV1;

public class AddrEx_01 {

	public static void main(String[] args) {
		
		AddrServiceV1 as = new AddrServiceV1();
		AddrCUDServiceV1 aC = new AddrCUDServiceV1();
		
//		as.viewAddrList();
//		as.findByName();
//		as.findByTel();
//		as.findByChain();
		
//		aC.inputAddr();
//		aC.deleteAddr();
		aC.updateAddr();
		
		
		
	}
}
