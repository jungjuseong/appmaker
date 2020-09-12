package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_contents", catalog="pbcms_new")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contents_seq")
	private Integer contentsSeq;

	@Column(name="app_name")
	private String appName;
	
	@Column(name="app_type")
	private String appType;
	
	@Column(name="company_seq")
	private Integer companySeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="chg_dt")
	private Date chgDt;

	@Column(name="chg_user_gb")
	private String chgUserGb;

	@Column(name="chg_user_id")
	private String chgUserId;

	@Column(name="chg_user_seq")
	private Integer chgUserSeq;

	@Column(name="complet_gb")
	private String completGb;

	@Column(name="contents_name")
	private String contentsName;

	@Column(name="contents_size")
	private String contentsSize;

	@Column(name="contents_url")
	private String contentsUrl;

	@Column(name="contents_type")
	private String contentsType;

	@Column(name="coupon_gb")
	private String couponGb;

	@Column(name="coupon_num")
	private String couponNum;

	@Lob
	@Column(name="description_text")
	private String descriptionText;

	@Column(name="distr_gb")
	private String distrGb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="limit_dt")
	private Date limitDt;

	@Column(name="limit_gb")
	private String limitGb;

	@Column(name="mem_down_amt")
	private String memDownAmt;

	@Column(name="mem_down_cnt")
	private String memDownCnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mem_down_end_dt")
	private Date memDownEndDt;

	@Column(name="mem_down_gb")
	private String memDownGb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mem_down_start_dt")
	private Date memDownStartDt;

	@Column(name="nonmem_down_amt")
	private String nonmemDownAmt;

	@Column(name="nonmem_down_cnt")
	private String nonmemDownCnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="nonmem_down_end_dt")
	private Date nonmemDownEndDt;

	@Column(name="nonmem_down_gb")
	private String nonmemDownGb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="nonmem_down_star_dt")
	private Date nonmemDownStarDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Column(name="reg_user_gb")
	private String regUserGb;

	@Column(name="reg_user_id")
	private String regUserId;

	@Column(name="reg_user_seq")
	private Integer regUserSeq;

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

	@Column(name="use_gb")
	private String useGb;

	@Column(name="ver_num")
	private String verNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "distribute_req_dt")
	private Date distributeReqDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "chg_contents_dt")
	private Date chgContentsDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "distribute_complet_dt")
	private Date distributeCompletDt;
	
	@Column(name="distribute_accept_id")
	private String distributeAcceptId;
	
	@Column(name="distribute_request_id")
	private String distributeRequestId;

	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(name="contents_seq",  referencedColumnName="contents_seq", insertable=false, updatable=false )
	private ContentsAppSub contentsAppSub;

	public void setContent( Content updated) {
		
		this.appName = updated.getAppName();
		this.companySeq = updated.getCompanySeq();
		this.chgDt = updated.getChgDt();
		this.chgUserGb = updated.getChgUserGb();
		this.chgUserId = updated.getChgUserId();
		this.chgUserSeq = updated.getChgUserSeq();
		this.completGb = updated.getCompletGb();
		this.contentsName = updated.getContentsName();
		this.contentsSize = updated.getContentsSize();
		this.contentsUrl = updated.getContentsUrl();
		this.contentsType = updated.getContentsType();
		this.couponGb = updated.getCouponGb();
		this.couponNum = updated.getCouponNum();
		this.descriptionText = updated.getDescriptionText();
		this.distrGb = updated.getDistrGb();
		this.limitDt = updated.getLimitDt();
		this.limitGb = updated.getLimitGb();
		this.memDownAmt = updated.getMemDownAmt();
		this.memDownCnt = updated.getMemDownCnt();
		this.memDownEndDt = updated.getMemDownEndDt();
		this.memDownGb = updated.getMemDownGb();
		this.memDownStartDt = updated.getMemDownStartDt();
		this.nonmemDownAmt = updated.getNonmemDownAmt();
		this.nonmemDownCnt = updated.getNonmemDownCnt();
		this.nonmemDownEndDt = updated.getNonmemDownEndDt();
		this.nonmemDownGb = updated.getNonmemDownGb();
		this.nonmemDownStarDt = updated.getNonmemDownStarDt();
		this.regDt = updated.getRegDt();
		this.regUserGb = updated.getRegUserGb();
		this.regUserId = updated.getRegUserId();
		this.regUserSeq = updated.getRegUserSeq();
		this.uploadOrgFile = updated.getUploadOrgFile();
		this.uploadSaveFile = updated.getUploadSaveFile();
		this.useAvailDt = updated.getUseAvailDt();
		this.useDisableDt = updated.getUseDisableDt();
		this.useGb = updated.getUseGb();
		this.verNum = updated.getVerNum();

		this.distributeReqDt = updated.getDistributeReqDt();
		this.chgContentsDt = updated.getChgContentsDt();
		this.distributeCompletDt = updated.getDistributeCompletDt();
		this.distributeAcceptId = updated.getDistributeAcceptId();
		this.distributeRequestId = updated.getDistributeRequestId();
	}
}