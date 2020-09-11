package com.clbee.appmaker.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_member")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_seq")
	private int userSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="chg_dt")
	private Date chgDt;

	@Column(name="chg_ip")
	private String chgIp;

	@Column(name="company_gb")
	private String companyGb;

	@Column(name="company_seq")
	private int companySeq;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="email_chk_dt")
	private Date emailChkDt;

	@Column(name="email_chk_gb")
	private String emailChkGb;

	@Column(name="email_chk_session")
	private String emailChkSession;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="login_dt")
	private Date loginDt;

	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Column(name="reg_ip")
	private String regIp;

	@Column(name="user_gb")
	private String userGb;

	@Column(name="user_id")
	private String userId;

	@Column(name="user_pw")
	private String userPw;

	@Column(name="user_status")
	private String userStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="withdrawal_dt")
	private Date withdrawalDt;
	
	@Column(name="onedepartment_seq")
	private Integer onedepartmentSeq;
	
	@Column(name="twodepartment_seq")
	private Integer twodepartmentSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_start_dt")
	private Date userStartDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_end_dt")
	private Date userEndDt;
	
	@Column(name="date_gb")
	private String dateGb;
	
	@Column(name="session_id")
	private String sessionId;
	
	@Column(name="login_status")
	private String loginStatus;
	
	@Column(name="login_deviceuuid")
	private String loginDeviceuuid;
	
	@Column(name="group_name")
	private String groupName;
	
	@NotFound(action = NotFoundAction.IGNORE )
	@ManyToOne
	@JoinColumn(name="company_seq",insertable=false,updatable=false )
	private Company company;

	@Formula("(select tb_departmentcategory.department_name from tb_departmentcategory where tb_departmentcategory.department_seq = onedepartment_seq limit 1)")
	private String onedepartmentName;

	@Formula("(select tb_departmentcategory.department_name from tb_departmentcategory where tb_departmentcategory.department_seq = twodepartment_seq limit 1)")
	private String twodepartmentName;
	
	@Formula("(select tb_group_user.group_name from tb_group_user where tb_group_user.group_seq=group_name limit 1)")
	private String userGroup;
}