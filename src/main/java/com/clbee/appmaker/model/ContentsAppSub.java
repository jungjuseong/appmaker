package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_contentsapp_sub")
public class ContentsAppSub implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contentsapp_sub_seq")
	private Integer contentsAppSubSeq;

	@Column(name="contents_seq")
	private Integer contentsSeq;

	@Column(name="app_seq")
	private String appSeq;
	
	@Column(name="inapp_seq")
	private Integer inAppSeq;
}
