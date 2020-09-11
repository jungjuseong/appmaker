package com.clbee.appmaker.service;

import com.clbee.appmaker.model.Company;
import com.clbee.appmaker.model.Member;

public interface CompanyService {
	int insertCompanyInfoWithProcedure(Company company);
	int updateCompanyInfo( Company updated, int companySeq );
	Company findByCustomInfo( String DBName, String value );
	Company findByCustomInfo( String DBName, int Value);
	String id_overlap_chk(String id);
	String sendEmailForId(String lastName, String firstName, String email);
	String send_pw_mail_service(String myId, String myMail);
	Company getCompanyInfo(String companyId);
	String changePwChk(Member m, String userID, String inputPW);
	Company selectByCompanyId( String companyId );
}
