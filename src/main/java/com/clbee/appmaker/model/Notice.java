package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_notice")
public class Notice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notice_seq")
	private Integer noticeSeq;

	@Column(name="company_seq")
	private Integer companySeq;
	
	@Column(name="reg_user_seq")
	private Integer regUserSeq;
	
	@Column(name="notice_name")
	private String noticeName;

	@Lob
	@Column(name="notice_text")
	private String noticeText;

	@Column(name="notice_start_dt")
	private Date noticeStartDt;

	@Column(name="notice_end_dt")
	private Date noticeEndDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;
	
	@Column(name="app_gb")
	private String appGb;
	
	@Column(name="use_user_gb")
	private String useUserGb;
	
	@Column(name="public_gb")
	private String publicGb;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(  optional = true)
	@JoinColumn( name="reg_user_seq",  referencedColumnName="user_seq", insertable=false, updatable=false )
	private Member member;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(  optional = true)
	@JoinColumn( name="notice_seq",  referencedColumnName="notice_seq", insertable=false, updatable=false )
	private NoticeAppSub noticeAppSub;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany
	@JoinColumn( nullable=true,  name="notice_seq",  referencedColumnName="notice_seq", insertable=false, updatable=false )
	private List<NoticeSub> noticeSub;

	@Override
	public String toString() {
		return "NoticeVO [noticeSeq=" + noticeSeq + ", companySeq="
				+ companySeq + ", regUserSeq=" + regUserSeq + ", noticeName="
				+ noticeName + ", noticeText=" + noticeText
				+ ", noticeStartDt=" + noticeStartDt + ", noticeEndDt="
				+ noticeEndDt + ", regDt=" + regDt + ", appGb=" + appGb
				+ ", useUserGb=" + useUserGb + ", publicGb=" + publicGb
				+ ", memberVO=" + member + ", noticeappSubVO="
				+ noticeAppSub + ", noticeSubVO=" + noticeSub + "]";
	}

}
