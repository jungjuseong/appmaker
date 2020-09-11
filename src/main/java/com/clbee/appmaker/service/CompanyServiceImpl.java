package com.clbee.appmaker.service;

import com.clbee.appmaker.repo.CompanyRepo;
import com.clbee.appmaker.model.Company;
import com.clbee.appmaker.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired CompanyRepo companyRepo;

	@Override
	public String id_overlap_chk(String id) {
		// TODO Auto-generated method stub
		return companyRepo.id_overlap_chk(id);
	}

	@Override
	public Company findByCustomInfo(String DBName, String value) {
		
		return companyRepo.findByCustomInfo(DBName, value);
	}

	@Override
	public Company findByCustomInfo(String DBName, int value) {
		
		return companyRepo.findByCustomInfo(DBName, value);
	}
	
	@Override
	public String sendEmailForId(String lastName, String firstName, String email){
		 return companyRepo.selectIdByUserNameAndEmail(lastName, firstName, email);
	}
	
	@Override
	public String send_pw_mail_service(String myId, String myMail){
		return companyRepo.send_pw_mail(myId, myMail);
	}
	
	@Override
	public Company getCompanyInfo(String companyID) {
		return companyRepo.getComInfo(companyID);
	}

	@Override
	public int updateCompanyInfo( Company company, int companySeq) {
		return companyRepo.updateCompanyInfo( company , companySeq );
	}

	@Override
	public String changePwChk(Member m, String userID, String inputPW) {
		return companyRepo.changePwChk(m, userID, inputPW);
	}
	
	@Override
	public Company selectByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return companyRepo.selectByCompanyId(companyId);
	}

	@Override
	public int insertCompanyInfoWithProcedure(Company company) {
		// TODO Auto-generated method stub
		return companyRepo.insertCompanyInfoWithProcedure(company);
	}
}
