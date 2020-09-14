package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_noticeapp_sub")
public class NoticeAppSub implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="noticeapp_sub_seq")
	private Integer noticeAppSubSeq;

	@Column(name="notice_seq")
	private Integer noticeSeq;
	
	@Column(name="inapp_seq")
	private Integer inAppSeq;
	
	@Column(name="store_bundle_id")
	private String storeBundleId;
}
