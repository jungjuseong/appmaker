package com.clbee.appmaker.dao;

import com.clbee.appmaker.Json.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_app")
public class App implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_seq")
    private Integer appSeq;
    
    @Column(name = "app_contents_amt")
    private String appContentsAmt;
    
    @Column(name = "app_contents_gb")
    private String appContentsGb;
    
    @Column(name = "app_name")
    private String appName;
    
    private String app_resultCode;
    @Column(name = "app_size")
    
    private String appSize;
    @Temporal(TemporalType.TIMESTAMP)
    
    @Column(name = "chg_dt")
    private Date chgDt;
    
    @Lob
    @Column(name = "chg_text")    
    private String chgText;
    
    @Column(name = "chg_user_gb")    
    private String chgUserGb;
    
    @Column(name = "chg_user_id")    
    private String chgUserId;
    
    @Column(name = "chg_user_seq")    
    private Integer chgUserSeq;
    
    @Column(name = "complet_gb")    
    private String completGb;
    
    @Column(name = "coupon_gb")    
    private String couponGb;
    
    @Column(name = "coupon_num")
    private String couponNum;
    
    @Lob
    @Column(name = "description_text")    
    private String descriptionText;
    
    @Column(name = "distr_gb")  
    private String distrGb;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "icon_org_file")
    private String iconOrgFile;
    
    @Column(name = "icon_save_file")
    private String iconSaveFile;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "limit_dt")
    private Date limitDt;
    
    @Column(name = "limit_gb")
    private String limitGb;
    
    @Column(name = "mem_down_amt")
    private String memDownAmt;
    
    @Column(name = "mem_down_cnt")
    private String memDownCnt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mem_down_end_dt")
    private Date memDownEndDt;
    
    @Column(name = "mem_down_gb")
    private String memDownGb;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mem_down_start_dt")
    private Date memDownStartDt;
    
    @Column(name = "nonmem_down_amt")
    private String nonmemDownAmt;
    
    @Column(name = "nonmem_down_cnt")
    private String nonmemDownCnt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "nonmem_down_end_dt")
    private Date nonmemDownEndDt;
    
    @Column(name = "nonmem_down_gb")
    private String nonmemDownGb;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "nonmem_down_star_dt")
    private Date nonmemDownStarDt;
    
    @Column(name = "install_gb")
    private String installGb;
    private String ostype;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reg_dt")
    private Date regDt;
    
    @Column(name = "reg_gb")
    private String regGb;
    
    @Column(name = "reg_user_gb")
    private String regUserGb;
    
    @Column(name = "reg_user_id")
    private String regUserId;
    
    @Column(name = "reg_user_seq")
    private Integer regUserSeq;
    
    @Column(name = "store_bundle_id")
    private String storeBundleId;
    
    @Column(name = "provision_gb")
    private String provisionGb;
    
    @Column(name = "template_name")
    @Formula("(select tb_template.template_name from tb_template where tb_template.template_seq = template_seq limit 1)")
    private String templateName;
    
    @Column(name = "template_seq")
    private Integer templateSeq;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_avail_dt")
    private Date useAvailDt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_disable_dt")
    private Date useDisableDt;
    
    @Column(name = "use_gb")
    private String useGb;
    
    @Column(name = "ver_num")
    private String verNum;
    
    @Column(name = "version_code")
    private String versionCode;
    
    @Column(name = "use_user_gb")
    private String useUserGb;
    
    @Column(name = "login_time")
    private String loginTime;
    
    @Column(name = "logout_time")
    private String logoutTime;
    
    @Column(name = "login_gb")
    private String loginGb;
    
    @Column(name="app_schema")
    private String appSchema;
    
    @Column(name="app_host")
    private String appHost;

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
    @JoinColumn(nullable = true, name = "reg_user_seq", referencedColumnName = "user_seq", insertable = false, updatable = false)
    private Member regMember;
    
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany
    @Cascade({ org.hibernate.annotations.CascadeType.DELETE })
    @JoinColumn(nullable = true, name = "app_seq", referencedColumnName = "app_seq", insertable = false, updatable = false)
    private List<Appsub> appsub;
    
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "chg_user_seq", referencedColumnName = "user_seq", insertable = false, updatable = false)
    private Member chgMember;

    public App(LinkedHashMap<Object, Object> map) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        appSeq = Integer.valueOf(((Long) map.get("appSeq")).intValue());
        appContentsAmt = (String) map.get("appContentsAmt");
        appContentsGb = (String) map.get("appContentsGb");
        appName = (String) map.get("appName");
        app_resultCode = (String) map.get("app_resultCode");
        appSize = (String) map.get("appSize");
        if (map.get("chgDt") != null)
            chgDt = transFormat.parse((String) map.get("chgDt"));
        chgText = (String) map.get("chgText");
        chgUserGb = (String) map.get("chgUserGb");
        chgUserId = (String) map.get("chgUserId");
        chgUserSeq = Integer.valueOf(((Long) map.get("chgUserSeq")).intValue());
        completGb = (String) map.get("completGb");
        couponGb = (String) map.get("couponGb");
        couponNum = (String) map.get("couponNum");
        descriptionText = (String) map.get("descriptionText");
        distrGb = (String) map.get("distrGb");
        fileName = (String) map.get("fileName");
        iconOrgFile = (String) map.get("iconOrgFile");
        iconSaveFile = (String) map.get("iconSaveFile");
        if (map.get("limitDt") != null)
            limitDt = transFormat.parse((String) map.get("limitDt"));
        limitGb = (String) map.get("limitGb");
        memDownAmt = (String) map.get("memDownAmt");
        memDownCnt = (String) map.get("memDownCnt");
        if ( map.get("memDownEndDt") != null)
            memDownEndDt = transFormat.parse((String) map.get("memDownEndDt"));
        memDownGb = (String) map.get("memDownGb");
        if ( map.get("memDownStartDt") != null)
            memDownStartDt = transFormat.parse((String) map.get("memDownStartDt"));
        nonmemDownAmt = (String) map.get("nonmemDownAmt");
        nonmemDownCnt = (String) map.get("nonmemDownCnt");
        if ( map.get("nonmemDownEndDt") != null)
            nonmemDownEndDt = transFormat.parse((String) map.get("nonmemDownEndDt"));
        nonmemDownGb = (String) map.get("nonmemDownGb");
        if (map.get("nonmemDownStarDt") != null)
            nonmemDownStarDt = transFormat.parse((String) map.get("nonmemDownStarDt"));
        installGb = (String) map.get("installGb");
        ostype = (String) map.get("ostype");
        if ( map.get("regDt") != null)
            regDt = transFormat.parse((String) map.get("regDt"));
        regGb = (String) map.get("regGb");
        regUserGb = (String) map.get("regUserGb");
        regUserId = (String) map.get("regUserId");
        regUserSeq = Integer.valueOf(((Long) map.get("regUserSeq")).intValue());
        storeBundleId = ((String) map.get("storeBundleId"));
        provisionGb = (String) map.get("provisionGb");
        templateName = (String) map.get("templateName");
        templateSeq = Integer.valueOf(((Long) map.get("templateSeq")).intValue());
        if (map.get("useAvailDt") != null)
            useAvailDt = transFormat.parse((String) map.get("useAvailDt"));
        if (map.get("useDisableDt") != null)
            useDisableDt = transFormat.parse((String) map.get("useDisableDt"));
        useGb = (String) map.get("useGb");
        verNum = (String) map.get("verNum");
        versionCode = (String) map.get("versionCode");
        useUserGb = (String) map.get("useUserGb");
        loginGb = (String) map.get("loginGb");
        loginTime = (String) map.get("loginTime");
        logoutTime = (String) map.get("logoutTime");
        this.appSchema = (String) map.get("appSchema");
        this.appHost = (String) map.get("appHost");

        //20180403 : lsy - add column complet_dt, chg_contents_dt
        if (map.get("distributeReqDt") != null) {
            distributeReqDt = transFormat.parse((String) map.get("distributeReqDt"));
        }
        if (map.get("chgContentsDt") != null) {
            chgContentsDt = transFormat.parse((String) map.get("chgContentsDt"));
        }
        if (map.get("distributeCompletDt") != null) {
            distributeCompletDt = transFormat.parse((String) map.get("distributeCompletDt"));
        }
        distributeAcceptId = (String) map.get("distributeAcceptId");
        distributeRequestId = (String) map.get("distributeRequestId");
    }

    public void setApp(App app) {
        appContentsAmt = app.getAppContentsAmt();
        appContentsGb = app.getAppContentsGb();
        appName = app.getAppName();
        app_resultCode = app.getApp_resultCode();
        appSize = app.getAppSize();
        chgDt = app.getChgDt();
        chgText = app.getChgText();
        chgUserGb = app.getChgUserGb();
        chgUserId = app.getChgUserId();
        chgUserSeq = app.getChgUserSeq();
        completGb = app.getCompletGb();
        couponGb = app.getCouponGb();
        couponNum = app.getCouponNum();
        descriptionText = app.getDescriptionText();
        distrGb = app.getDistrGb();
        fileName = app.getFileName();
        iconOrgFile = app.getIconOrgFile();
        iconSaveFile = app.getIconSaveFile();
        limitDt = app.getLimitDt();
        limitGb = app.getLimitGb();
        memDownAmt = app.getMemDownAmt();
        memDownCnt = app.getMemDownCnt();
        memDownEndDt = app.getMemDownEndDt();
        memDownGb = app.getMemDownGb();
        memDownStartDt = app.getMemDownStartDt();
        nonmemDownAmt = app.getNonmemDownAmt();
        nonmemDownCnt = app.getNonmemDownCnt();
        nonmemDownEndDt = app.getNonmemDownEndDt();
        nonmemDownGb = app.getNonmemDownGb();
        nonmemDownStarDt = app.getNonmemDownStarDt();
        installGb = app.getInstallGb();
        ostype = app.getOstype();
        regDt = app.getRegDt();
        regGb = app.getRegGb();
        regUserGb = app.getRegUserGb();
        regUserId = app.getRegUserId();
        regUserSeq = app.getRegUserSeq();
        storeBundleId = app.getStoreBundleId();
        provisionGb = app.getProvisionGb();
        templateName = app.getTemplateName();
        templateSeq = app.getTemplateSeq();
        useAvailDt = app.getUseAvailDt();
        useDisableDt = app.getUseDisableDt();
        useGb = app.getUseGb();
        verNum = app.getVerNum();
        versionCode = app.getVersionCode();
        useUserGb = app.getUseUserGb();
        loginGb = app.getLoginGb();
        loginTime = app.getLoginTime();
        logoutTime = app.getLogoutTime();
        this.appSchema = app.getAppSchema();
        this.appHost = app.getAppHost();

        //20180403 : lsy - add column complet_dt, chg_contents_dt
        distributeReqDt = app.getDistributeReqDt();
        chgContentsDt = app.getChgContentsDt();
        distributeCompletDt = app.getDistributeCompletDt();
        distributeAcceptId = app.getDistributeAcceptId();
        distributeRequestId = app.getDistributeRequestId();
        //20180403 : lsy - add column complet_dt, chg_contents_dt - end
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getChgDt() {
        return chgDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getLimitDt() {
        return limitDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getMemDownEndDt() {
        return memDownEndDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getMemDownStartDt() {
        return memDownStartDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getNonmemDownEndDt() {
        return nonmemDownEndDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getNonmemDownStarDt() {
        return nonmemDownStarDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getRegDt() {
        return regDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getUseAvailDt() {
        return useAvailDt;
    }

    public void setUseAvailDt(Date useAvailDt) {
        this.useAvailDt = useAvailDt;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getUseDisableDt() {
        return useDisableDt;
    }

    public String toString() {
        return

                "AppVO [appSeq=" + appSeq + ", appContentsAmt=" + appContentsAmt + ", appContentsGb=" + appContentsGb
                        + ", appName=" + appName + ", app_resultCode=" + app_resultCode + ", appSize=" + appSize + ", chgDt="
                        + chgDt + ", chgText=" + chgText + ", chgUserGb=" + chgUserGb + ", chgUserId=" + chgUserId
                        + ", chgUserSeq=" + chgUserSeq + ", completGb=" + completGb + ", couponGb=" + couponGb + ", couponNum="
                        + couponNum + ", descriptionText=" + descriptionText + ", distrGb=" + distrGb + ", fileName=" + fileName
                        + ", iconOrgFile=" + iconOrgFile + ", iconSaveFile=" + iconSaveFile + ", limitDt=" + limitDt
                        + ", limitGb=" + limitGb + ", memDownAmt=" + memDownAmt + ", memDownCnt=" + memDownCnt
                        + ", memDownEndDt=" + memDownEndDt + ", memDownGb=" + memDownGb + ", memDownStartDt=" + memDownStartDt
                        + ", nonmemDownAmt=" + nonmemDownAmt + ", nonmemDownCnt=" + nonmemDownCnt + ", nonmemDownEndDt="
                        + nonmemDownEndDt + ", nonmemDownGb=" + nonmemDownGb + ", nonmemDownStarDt=" + nonmemDownStarDt
                        + ", ostype=" + ostype + ", regDt=" + regDt + ", regGb=" + regGb + ", regUserGb=" + regUserGb
                        + ", regUserId=" + regUserId + ", regUserSeq=" + regUserSeq + ", storeBundleId=" + storeBundleId
                        + ", provisionGb=" + provisionGb + ", templateName=" + templateName + ", templateSeq=" + templateSeq
                        + ", useAvailDt=" + useAvailDt + ", useDisableDt=" + useDisableDt + ", useGb=" + useGb + ", verNum="
                        + verNum + ", regMemberVO=" + regMember + ", chgMemberVO=" + chgMember + ", appSchema=" + appSchema
                        + ", appHost=" + appHost + "]";
    }
}
