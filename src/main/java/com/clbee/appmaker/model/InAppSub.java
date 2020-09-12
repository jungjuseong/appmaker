package com.clbee.appmaker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_inapp_sub")
public class InAppSub implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="inappsub_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer inAppSubSeq;

	@Column(name="inapp_seq")
	private Integer inAppSeq;

	@Column(name="user_seq")
	private Integer userSeq;
	
	@Column(name="department_seq")
	private Integer departmentSeq;

}