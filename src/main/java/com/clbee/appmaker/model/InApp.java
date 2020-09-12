package com.clbee.appmaker.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_inapp")
public class InApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="inapp_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer inAppSeq;

	@Column(name = "store_bundle_id")
	private String storeBundleId;
	
	@Column(name="category_name")
	private String categoryName;

	@Column(name="category_seq")
	private Integer categorySeq;

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

	@Column(name="coupon_gb")
	private String couponGb;

	@Column(name="coupon_num")
	private String couponNum;

	@Lob
	@Column(name="description_text")
	private String descriptionText;

	@Column(name="distr_gb")
	private String distrGb;

	@Column(name="icon_org_file")
	private String iconOrgFile;

	@Column(name="icon_save_file")
	private String iconSaveFile;

	@Column(name="inapp_name")
	private String inAppName;

	@Column(name="inapp_org_file")
	private String inAppOrgFile;

	@Column(name="inapp_save_file")
	private String inAppSaveFile;

	@Column(name="inapp_size")
	private String inAppSize;

	@Column(name="inapp_url")
	private String inAppUrl;

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
	
	@Column(name="use_user_gb")
	private String useUserGb;
	
	@Column(name="screen_type")
	private String screenType;

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
	@JoinColumn(nullable=true, name="chg_user_seq",  referencedColumnName="user_seq", insertable=false, updatable=false)
	private Member chgMember;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(nullable=true, name="reg_user_seq", referencedColumnName="user_seq", insertable=false, updatable=false)
	private Member regMember;

	@NotFound(action = NotFoundAction.IGNORE )
	@OneToMany 
	@Cascade(CascadeType.DELETE)
	@JoinColumn(nullable=true, name="inapp_seq", referencedColumnName="inapp_seq", insertable=false, updatable=false )
	private List<InAppSub> inAppSub;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@Cascade(CascadeType.DELETE)
	@JoinColumn(nullable=true, name="inapp_seq",  referencedColumnName="inapp_seq", insertable=false, updatable=false)
	private InAppMeta inAppMeta;

	public void setInApp(InApp inApp) {
		this.storeBundleId = inApp.getStoreBundleId();
		this.categoryName = inApp.getCategoryName();
		this.categorySeq = inApp.getCategorySeq();
		this.chgDt = inApp.getChgDt();
		this.chgUserGb = inApp.getChgUserGb();
		this.chgUserId = inApp.getChgUserId();
		this.chgUserSeq = inApp.getChgUserSeq();
		this.completGb = inApp.getCompletGb();
		this.couponGb = inApp.getCouponGb();
		this.couponNum = inApp.getCouponNum();
		this.descriptionText = inApp.getDescriptionText();
		this.distrGb = inApp.getDistrGb();
		this.iconOrgFile = inApp.getIconOrgFile();
		this.iconSaveFile = inApp.getIconSaveFile();
		this.inAppName = inApp.getInAppName();
		this.inAppOrgFile = inApp.getIconOrgFile();
		this.inAppSaveFile = inApp.getIconSaveFile();
		this.inAppSize = inApp.getInAppSize();
		this.inAppUrl = inApp.getInAppUrl();
		this.limitDt = inApp.getLimitDt();
		this.limitGb = inApp.getLimitGb();
		this.memDownAmt = inApp.getMemDownAmt();
		this.memDownCnt = inApp.getMemDownCnt();
		this.memDownEndDt = inApp.getMemDownEndDt();
		this.memDownGb = inApp.getMemDownGb();
		this.memDownStartDt = inApp.getMemDownStartDt();
		this.nonmemDownAmt = inApp.getNonmemDownAmt();
		this.nonmemDownCnt = inApp.getNonmemDownCnt();
		this.nonmemDownEndDt = inApp.getNonmemDownEndDt();
		this.nonmemDownGb = inApp.getNonmemDownGb();
		this.nonmemDownStarDt = inApp.getNonmemDownStarDt();
		this.regDt = inApp.getRegDt();
		this.regUserGb = inApp.getRegUserGb();
		this.regUserId = inApp.getRegUserId();
		this.regUserSeq = inApp.getRegUserSeq();
		this.useAvailDt = inApp.getUseAvailDt();
		this.useDisableDt = inApp.getUseDisableDt();
		this.useGb = inApp.getUseGb();
		this.verNum = inApp.getVerNum();
		this.useUserGb = inApp.getUseUserGb();
		this.screenType = inApp.getScreenType();

		this.distributeReqDt = inApp.getDistributeReqDt();
		this.chgContentsDt = inApp.getChgContentsDt();
		this.distributeCompletDt = inApp.getDistributeCompletDt();
		this.distributeAcceptId = inApp.getDistributeAcceptId();
		this.distributeRequestId = inApp.getDistributeRequestId();
	}
}