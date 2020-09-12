package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_distribute_restore")
@DynamicInsert(true)
public class DistributeRestore implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="restore_seq")
	private Integer restoreSeq;
	
	@Column(name="type")
	private String type;

	@Column(name="exist_seq")
	private Integer existSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="distribute_req_dt")
	private Date distributeReqDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="distribute_restore_dt")
	private Date distributeRestoreDt;
	
	@Column(name="restore_user_id")
	private String restoreUserId;
	
	@Lob
	@Column(name = "restore_text")
	private String restoreText;
	
	@Column(name="distribute_requeset_id")
	private String distributeRequestId;
	
}
