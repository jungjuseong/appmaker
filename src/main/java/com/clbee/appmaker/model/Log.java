package com.clbee.appmaker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.clbee.appmaker.Json.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_log")
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="log_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer logSeq;

	@Column(name="device_uuid")
	private String deviceUuid;

	@Column(name="store_bundle_id")
	private String storeBundleId;
	
	@Column(name="inapp_seq")
	private String inappSeq;

	@Column(name="reg_user_seq")
	private Integer regUserSeq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@Column(name="page_gb")
	private String pageGb;
	
	@Column(name="data_gb")
	private String dataGb;
	
	@Column(name="data")
	private String data;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true, name = "reg_user_seq", referencedColumnName = "user_seq", insertable = false, updatable = false)
	private Member regMember;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true, name = "inapp_seq", referencedColumnName = "inapp_seq", insertable = false, updatable = false)
	private InApp inApp;

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getRegDt() {
		return regDt;
	}

}