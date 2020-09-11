package com.clbee.appmaker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="tb_app_sub")
public class AppSub implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="appsub_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer appsubSeq;

	@Column(name="app_seq")
	private Integer appSeq;

	@Column(name="user_seq")
	private Integer userSeq;

	@Column(name="department_Seq")
	private Integer departmentSeq;
}
