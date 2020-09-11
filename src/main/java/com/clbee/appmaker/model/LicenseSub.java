package com.clbee.appmaker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class LicenseSub implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="licensesub_seq")
	private Integer licensesubSeq;
	
	@Column(name="license_seq")
	private Integer licenseSeq;

	@Column(name="license_user_seq")
	private Integer licenseUserSeq;
	
	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="device_os")
	private String deviceOs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "use_start_dt")
	private Date useStartDt;
	
	private String userId;
}
