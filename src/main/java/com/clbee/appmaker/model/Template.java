package com.clbee.appmaker.model;

import com.clbee.appmaker.Json.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_template")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="template_seq")
	private int templateSeq;

	@Column(name="app_contents_amt")
	private String appContentsAmt;

	@Column(name="app_contents_gb")
	private String appContentsGb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="chg_dt")
	private Date chgDt;

	@Column(name="chg_user_gb")
	private String chgUserGb;

	@Column(name="chg_user_id")
	private String chgUserId;

	@Column(name="chg_user_seq")
	private int chgUserSeq;

	@Column(name="complet_gb")
	private String completGb;

	@Lob
	@Column(name="description_text")
	private String descriptionText;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="limit_dt")
	private Date limitDt;

	@Column(name="limit_gb")
	private String limitGb;

	@Column(name="ostype_gb")
	private String ostypeGb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Column(name="reg_user_gb")
	private String regUserGb;

	@Column(name="reg_user_id")
	private String regUserId;

	@Column(name="reg_user_seq")
	private int regUserSeq;

	@Column(name="template_name")
	private String templateName;

	@Column(name="template_type_gb")
	private String templateTypeGb;

	@Column(name="upload_org_file")
	private String uploadOrgFile;

	@Column(name="upload_save_file")
	private String uploadSaveFile;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="use_avail_dt")
	private Date useAvailDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="use_disable_dt")
	private Date useDisableDt;
	
	@Column(name="use_user_gb")
	private String useUserGb;

	@Column(name="use_gb")
	private String useGb;

	@Column(name="ver_num")
	private String verNum;
	
	public void setTemplate( Template updated ) {
		this.appContentsAmt = updated.getAppContentsAmt();
		this.appContentsGb = updated.getAppContentsGb();
		this.chgDt = updated.getChgDt();
		this.chgUserGb = updated.getChgUserGb();
		this.chgUserId = updated.getChgUserId();
		this.chgUserSeq = updated.getChgUserSeq();
		this.completGb = updated.getCompletGb();
		this.descriptionText = updated.getDescriptionText();
		this.limitDt = updated.getLimitDt();
		this.limitGb = updated.getLimitGb();
		this.ostypeGb = updated.getOstypeGb();
		this.regDt = updated.getRegDt();
		this.regUserGb = updated.getRegUserGb();
		this.regUserId = updated.getRegUserId();
		this.regUserSeq = updated.getRegUserSeq();
		this.templateName = updated.getTemplateName();
		this.templateTypeGb = updated.getTemplateTypeGb();
		this.uploadOrgFile = updated.getUploadOrgFile();
		this.uploadSaveFile = updated.getUploadSaveFile();
		this.useAvailDt = updated.getUseAvailDt();
		this.useDisableDt = updated.getUseDisableDt();
		this.useUserGb = updated.getUseUserGb();
		this.useGb = updated.getUseGb();
		this.verNum = updated.getVerNum();
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getChgDt() {
		return this.chgDt;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getLimitDt() {
		return this.limitDt;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getRegDt() {
		return this.regDt;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getUseAvailDt() {
		return this.useAvailDt;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getUseDisableDt() {
		return this.useDisableDt;
	}
	
}