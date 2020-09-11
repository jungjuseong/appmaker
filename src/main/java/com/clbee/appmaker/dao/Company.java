package com.clbee.appmaker.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_company")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_seq")
	private int companySeq;

	@Column(name="addr_detail")
	private String addrDetail;

	@Column(name="addr_first")
	private String addrFirst;

	@Column(name="company_name")
	private String companyName;

	@Column(name="company_status")
	private String companyStatus;

	@Column(name="company_tel")
	private String companyTel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="withdrawal_dt")
	private Date withdrawalDt;

	private String zipcode;

	public void setCompanyVO(Company company) {
		this.addrDetail = company.getAddrDetail();
		this.addrFirst = company.getAddrFirst();
		this.companyName = company.getCompanyName();
		this.companyStatus = company.getCompanyStatus();
		this.companyTel = company.getCompanyTel();
		this.regDt = company.getRegDt();
		this.withdrawalDt = company.getWithdrawalDt();
		this.zipcode = company.getZipcode();
	}
}