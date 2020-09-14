package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_departmentcategory")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="department_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer departmentSeq;
	
	@Column(name="department_name")
	private String departmentName;

	@Column(name="department_parent")
	private Integer departmentParent;

	@Column(name="company_seq")
	private Integer companySeq;
	
	@Column(name="depth")
	private String depth;
	
	@Column(name="reg_user_seq")
	private Integer regUserSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;
	
	@Column(name="use_gb")
	private String useGb;
}
