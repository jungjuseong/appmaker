package com.clbee.appmaker.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_provision")
public class Provision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="prov_seq")
	private int provSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="chg_dt")
	private Date chgDt;

	@Column(name="chg_user_gb")
	private String chgUserGb;

	@Column(name="chg_user_id")
	private String chgUserId;

	@Column(name="chg_user_seq")
	private int chgUserSeq;

	@Column(name="distr_profile")
	private String distrProfile;

	@Column(name="distr_profile_name")
	private String distrProfileName;
	
	@Column(name="distr_profile_save_name")
	private String distrProfileSaveName;

	@Column(name="prov_id")
	private String provId;

	@Column(name="prov_name")
	private String provName;

	@Column(name="prov_pw")
	private String provPw;

	@Override
	public String toString() {
		return "Provision [provSeq=" + provSeq + ", chgDt=" + chgDt
				+ ", chgUserGb=" + chgUserGb + ", chgUserId=" + chgUserId
				+ ", chgUserSeq=" + chgUserSeq + ", distrProfile="
				+ distrProfile + ", distrProfileName=" + distrProfileName
				+ ", distrProfileSaveName=" + distrProfileSaveName
				+ ", provId=" + provId + ", provName=" + provName + ", provPw="
				+ provPw + ", provTestGb=" + provTestGb + ", regDt=" + regDt
				+ ", regUserGb=" + regUserGb + ", regUserId=" + regUserId
				+ ", regUserSeq=" + regUserSeq + ", regCompanySeq="
				+ regCompanySeq + "]";
	}

	@Column(name="prov_test_gb")
	private String provTestGb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Column(name="reg_user_gb")
	private String regUserGb;

	@Column(name="reg_user_id")
	private String regUserId;

	@Column(name="reg_user_seq")
	private int regUserSeq;

	@Column(name="reg_company_seq")
	private int regCompanySeq;

	public void setProvisionVO(Provision updatedVO) {
		this.chgDt = updatedVO.getChgDt();
		this.chgUserGb = updatedVO.getChgUserGb();
		this.chgUserId = updatedVO.getChgUserId();
		this.chgUserSeq = updatedVO.getChgUserSeq();
		this.distrProfile = updatedVO.getDistrProfile();
		this.distrProfileName = updatedVO.getDistrProfileName();
		this.distrProfileSaveName = updatedVO.getDistrProfileSaveName();
		this.provId = updatedVO.getProvId();
		this.provName = updatedVO.getProvName();
		this.provPw = updatedVO.getProvPw();
		this.provTestGb = updatedVO.getProvTestGb();
		this.regDt = updatedVO.getRegDt();
		this.regUserGb = updatedVO.getRegUserGb();
		this.regUserId = updatedVO.getRegUserId();
		this.regUserSeq = updatedVO.getRegUserSeq();
		this.regCompanySeq = updatedVO.getRegCompanySeq();
	}
}