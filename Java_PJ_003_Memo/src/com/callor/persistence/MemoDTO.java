package com.callor.persistence;

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
public class MemoDTO {

	private int id;//	number
	private String m_auth;//	nvarchar2(30 char)
	private String m_date;//	varchar2(10 byte)
	private String m_subject;//	nvarchar2(50 char)
	private String m_text;//	nvarchar2(255 char)
	private String m_photo;//	nvarchar2(125 char)
}
