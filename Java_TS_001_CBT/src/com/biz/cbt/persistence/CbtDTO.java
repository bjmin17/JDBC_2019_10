package com.biz.cbt.persistence;

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
public class CbtDTO {

	private int cb_num;//	number
	private String cb_pcode;
	private String cb_prob;//	nvarchar2(100 char)
	private String cb_fir;//	nvarchar2(100 char)
	private String cb_sec;//	nvarchar2(100 char)
	private String cb_thi;//	nvarchar2(100 char)
	private String cb_fou;//	nvarchar2(100 char)
	private String cb_ans;//	nvarchar2(100 char)
}
