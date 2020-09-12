package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_appcategory", catalog="appmaker")
public class Appcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_seq")
	private int categorySeq;
	
	@Column(name="app_seq")
	private int appSeq;

	@Column(name="category_name")
	private String categoryName;

	@Column(name="category_parent")
	private int categoryParent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="chg_dt")
	private Date chgDt;

	@Column(name="chg_user_gb")
	private String chgUserGb;

	@Column(name="chg_user_id")
	private String chgUserId;

	@Column(name="chg_user_seq")
	private int chgUserSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Column(name="reg_user_gb")
	private String regUserGb;

	@Column(name="reg_user_id")
	private String regUserId;

	@Column(name="reg_user_seq")
	private int regUserSeq;

	public void setAppcategoryVO(Appcategory updatedVO) {
		this.appSeq = updatedVO.getAppSeq();
		this.categoryName = updatedVO.getCategoryName();
		this.categoryParent = updatedVO.getCategoryParent();
		this.chgDt = updatedVO.getChgDt();
		this.chgUserGb = updatedVO.getChgUserGb();
		this.chgUserId = updatedVO.getChgUserId();
		this.chgUserSeq = updatedVO.getChgUserSeq();
		this.regDt = updatedVO.getRegDt();
		this.regUserGb = updatedVO.getRegUserGb();
		this.regUserId = updatedVO.getRegUserId();
		this.regUserSeq = updatedVO.getRegUserSeq();
	}
}