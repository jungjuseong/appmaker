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
@Table(name="tb_inapphistory")
public class InAppHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="inapp_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer inappSeq;

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
	private String inappName;

	@Column(name="inapp_org_file")
	private String inappOrgFile;

	@Column(name="inapp_save_file")
	private String inappSaveFile;

	@Column(name="inapp_size")
	private String inappSize;

	@Column(name="inapp_url")
	private String inappUrl;

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

	//20180403 : lsy - add column complet_dt, chg_contents_dt
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "complet_dt")
	private Date completDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "chg_contents_dt")
	private Date chgContentsDt;
	
	@Column(name="distribute_accept_id")
	private String distributeAcceptId;
	
	@Column(name="distribute_request_id")
	private String distributeRequestId;
	//20180403 : lsy - add column complet_dt, chg_contents_dt - end


	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(nullable=true, name="chg_user_seq",  referencedColumnName="user_seq", insertable=false, updatable=false)
	private Member chgMember;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(optional=true)
	@JoinColumn(nullable=true, name="reg_user_seq", referencedColumnName="user_seq", insertable=false, updatable=false)
	private Member regMember;
	
	@NotFound(action=NotFoundAction.IGNORE )
	@OneToMany 
	@Cascade(CascadeType.DELETE)
	@JoinColumn(nullable=true, name="inapp_seq", referencedColumnName="inapp_seq", insertable=false, updatable=false )
	private List<InAppSub> inappSubVO;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@Cascade(CascadeType.DELETE)
	@JoinColumn(nullable=true, name="inapp_seq",  referencedColumnName="inapp_seq", insertable=false, updatable=false)
	private InAppMeta inappMeta;

	public void setInappVO(InAppHistory updatedVO) {

		this.storeBundleId = updatedVO.getStoreBundleId();
		this.categoryName = updatedVO.getCategoryName();
		this.categorySeq = updatedVO.getCategorySeq();
		this.chgDt = updatedVO.getChgDt();
		this.chgUserGb = updatedVO.getChgUserGb();
		this.chgUserId = updatedVO.getChgUserId();
		this.chgUserSeq = updatedVO.getChgUserSeq();
		this.completGb = updatedVO.getCompletGb();
		this.couponGb = updatedVO.getCouponGb();
		this.couponNum = updatedVO.getCouponNum();
		this.descriptionText = updatedVO.getDescriptionText();
		this.distrGb = updatedVO.getDistrGb();
		this.iconOrgFile = updatedVO.getIconOrgFile();
		this.iconSaveFile = updatedVO.getIconSaveFile();
		this.inappName = updatedVO.getInappName();
		this.inappOrgFile = updatedVO.getIconOrgFile();
		this.inappSaveFile = updatedVO.getIconSaveFile();
		this.inappSize = updatedVO.getInappSize();
		this.inappUrl = updatedVO.getInappUrl();
		this.limitDt = updatedVO.getLimitDt();
		this.limitGb = updatedVO.getLimitGb();
		this.memDownAmt = updatedVO.getMemDownAmt();
		this.memDownCnt = updatedVO.getMemDownCnt();
		this.memDownEndDt = updatedVO.getMemDownEndDt();
		this.memDownGb = updatedVO.getMemDownGb();
		this.memDownStartDt = updatedVO.getMemDownStartDt();
		this.nonmemDownAmt = updatedVO.getNonmemDownAmt();
		this.nonmemDownCnt = updatedVO.getNonmemDownCnt();
		this.nonmemDownEndDt = updatedVO.getNonmemDownEndDt();
		this.nonmemDownGb = updatedVO.getNonmemDownGb();
		this.nonmemDownStarDt = updatedVO.getNonmemDownStarDt();
		this.regDt = updatedVO.getRegDt();
		this.regUserGb = updatedVO.getRegUserGb();
		this.regUserId = updatedVO.getRegUserId();
		this.regUserSeq = updatedVO.getRegUserSeq();
		this.useAvailDt = updatedVO.getUseAvailDt();
		this.useDisableDt = updatedVO.getUseDisableDt();
		this.useGb = updatedVO.getUseGb();
		this.verNum = updatedVO.getVerNum();
		this.useUserGb = updatedVO.getUseUserGb();
		this.screenType = updatedVO.getScreenType();

		this.completDt = updatedVO.getCompletDt();
		this.chgContentsDt = updatedVO.getChgContentsDt();
		this.distributeAcceptId = updatedVO.getDistributeAcceptId();
		this.distributeRequestId = updatedVO.getDistributeRequestId();
	}
}