package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.Company;
import com.clbee.appmaker.model.Member;

public interface CompanyDao {
	int insertCompanyInfoWithProcedure(Company companyVO);
	Company findByCustomInfo(String DBName, String Value);
	Company findByCustomInfo(String DBName, int Value);
	String id_overlap_chk(String id);
	String selectIdByUserNameAndEmail(String lastName, String firstName, String email);
	String send_pw_mail(String myId, String myMail);
	Company getComInfo(String companyID);
	int updateCompanyInfo(Company company, int companySeq);
	String changePwChk(Member m, String userID, String inputPW);
	Company selectByCompanyId(String companyId );
}
