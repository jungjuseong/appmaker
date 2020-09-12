package com.clbee.appmaker.service;

import com.clbee.appmaker.dao.CompanyDao;
import com.clbee.appmaker.model.Company;
import com.clbee.appmaker.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyDao companyDao;

	@Override
	public String id_overlap_chk(String id) {
		// TODO Auto-generated method stub
		return companyDao.id_overlap_chk(id);
	}

	@Override
	public Company findByCustomInfo(String DBName, String value) {
		
		return companyDao.findByCustomInfo(DBName, value);
	}

	@Override
	public Company findByCustomInfo(String DBName, int value) {
		
		return companyDao.findByCustomInfo(DBName, value);
	}
	
	@Override
	public String sendEmailForId(String lastName, String firstName, String email){
		 return companyDao.selectIdByUserNameAndEmail(lastName, firstName, email);
	}
	
	@Override
	public String send_pw_mail_service(String myId, String myMail){
		return companyDao.send_pw_mail(myId, myMail);
	}
	
	@Override
	public Company getCompanyInfo(String companyID) {
		return companyDao.getComInfo(companyID);
	}

	@Override
	public int updateCompanyInfo(Company company, int companySeq) {
		return companyDao.updateCompanyInfo(company , companySeq );
	}

	@Override
	public String changePwChk(Member m, String userID, String inputPW) {
		return companyDao.changePwChk(m, userID, inputPW);
	}
	
	@Override
	public Company selectByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return companyDao.selectByCompanyId(companyId);
	}

	@Override
	public int insertCompanyInfoWithProcedure(Company company) {
		// TODO Auto-generated method stub
		return companyDao.insertCompanyInfoWithProcedure(company);
	}
}
