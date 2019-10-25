package com.biz.iolist.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class IolistDTO {

	private int io_seq;
	private String io_date;
	private String io_inout;
	private int io_qty;
	private int io_price;
	private int io_total;
	private String io_pcode;
	private String io_dcode;
	
	
}

