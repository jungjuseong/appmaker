package com.clbee.appmaker.Json;

import com.clbee.appmaker.model.App;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Data
public class AppForConnection implements Serializable {
			private static final long serialVersionUID = 1L;

			private Integer appSeq;
			private String appContentsAmt;
			private String appContentsGb;
			private String appName;
			private String app_resultCode;
			private String appSize;
			private String chgDt;
			private String chgText;
			private String chgUserGb;
			private String chgUserId;
			private Integer chgUserSeq;
			private String completGb;
			private String couponGb;
			private String couponNum;
			private String descriptionText;
			private String distrGb;
			private String fileName;
			private String iconOrgFile;
			private String iconSaveFile;
			private String limitDt;
			private String limitGb;
			private String memDownAmt;
			private String memDownCnt;
			private String memDownEndDt;
			private String memDownGb;
			private String memDownStartDt;
			private String nonmemDownAmt;
			private String nonmemDownCnt;
			private String nonmemDownEndDt;
			private String nonmemDownGb;
			private String nonmemDownStarDt;
			private String installGb;
			private String ostype;
			private String regDt;
			private String regGb;
			private String regUserGb;
			private String regUserId;
			private Integer regUserSeq;
			private String storeBundleId;
			private String provisionGb;
			private String templateName;
			private Integer templateSeq;
			private String useAvailDt;
			private String useDisableDt;
			private String useGb;
			private String verNum;
			private String versionCode;
			private String useUserGb;
			private String loginTime;
			private String logoutTime;
			private String loginGb;

			public AppForConnection(App app) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//edit for the    format you need
				
				this.appSeq = app.getAppSeq();
				this.appContentsAmt = app.getAppContentsAmt();
				this.appContentsGb = app.getAppContentsGb();
				this.appName = app.getAppName();
				this.app_resultCode = app.getApp_resultCode();
				this.appSize = app.getAppSize();
				if(app.getChgDt() !=null)
				this.chgDt = format.format(app.getChgDt());
				this.chgText = app.getChgText();
				this.chgUserGb = app.getChgUserGb();
				this.chgUserId = app.getChgUserId();
				this.chgUserSeq = app.getChgUserSeq();
				this.completGb = app.getCompletGb();
				this.couponGb = app.getCouponGb();
				this.couponNum = app.getCouponNum();
				this.descriptionText = app.getDescriptionText();
				this.distrGb = app.getDistrGb();
				this.fileName = app.getFileName();
				this.iconOrgFile = app.getIconOrgFile();
				this.iconSaveFile = app.getIconSaveFile();
				if(app.getLimitDt() !=null)
				this.limitDt = format.format(app.getLimitDt());
				this.limitGb = app.getLimitGb();
				this.memDownAmt = app.getMemDownAmt();
				this.memDownCnt = app.getMemDownCnt();
				if(app.getMemDownEndDt() !=null)
				this.memDownEndDt = format.format(app.getMemDownEndDt());
				this.memDownGb = app.getMemDownGb();
				if(app.getMemDownStartDt() !=null)
				this.memDownStartDt = format.format(app.getMemDownStartDt());
				this.nonmemDownAmt = app.getNonmemDownAmt();
				this.nonmemDownCnt = app.getNonmemDownCnt();
				if(app.getNonmemDownEndDt() !=null)
				this.nonmemDownEndDt = format.format(app.getNonmemDownEndDt());
				this.nonmemDownGb = app.getNonmemDownGb();
				if(app.getNonmemDownStarDt() !=null)
				this.nonmemDownStarDt = format.format(app.getNonmemDownStarDt());
				this.installGb = app.getInstallGb();
				this.ostype = app.getOstype();
				if(app.getRegDt() !=null)
				this.regDt = format.format(app.getRegDt());
				this.regGb = app.getRegGb();
				this.regUserGb = app.getRegUserGb();
				this.regUserId = app.getRegUserId();
				this.regUserSeq = app.getRegUserSeq();
				this.storeBundleId = app.getStoreBundleId();
				this.provisionGb = app.getProvisionGb();
				this.templateName = app.getTemplateName();
				this.templateSeq = app.getTemplateSeq();
				if(app.getUseAvailDt() !=null)
				this.useAvailDt = format.format(app.getUseAvailDt());
				if(app.getUseDisableDt() !=null)
				this.useDisableDt = format.format(app.getUseDisableDt());
				this.useGb = app.getUseGb();
				this.verNum = app.getVerNum();
				this.versionCode = app.getVersionCode();
				this.useUserGb = app.getUseUserGb();
				this.loginTime = app.getLoginTime();
				this.logoutTime = app.getLogoutTime();
				this.loginGb = app.getLoginGb();
			}
}
