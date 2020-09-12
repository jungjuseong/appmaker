package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_device")
public class Device implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="device_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int deviceSeq;

	@Column(name="company_seq")
	private Integer companySeq;

	@Column(name="device_uuid")
	private String deviceUuid;

	@Column(name="device_info")
	private String deviceInfo;

	@Column(name="device_type")
	private String deviceType;

	@Column(name="use_gb")
	private String useGb;

	@Column(name="reg_user_seq")
	private Integer regUserSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_dt")
	private Date regDt;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(name="reg_user_seq",  referencedColumnName="user_seq", insertable=false, updatable=false )
	private Member member;
}
