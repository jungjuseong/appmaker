package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_download_log")
public class DownloadLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="download_seq")
	private int downloadSeq;

	@Column(name="board_seq")
	private int boardSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="download_dt")
	private Date downloadDt;

	@Column(name="download_gb")
	private String downloadGb;

	@Column(name="download_type")
	private String downloadType;

	@Column(name="user_seq")
	private int userSeq;

	public void setDownloadLog(DownloadLog updated) {
		
		this.boardSeq = updated.getBoardSeq();
		this.downloadDt = updated.getDownloadDt();
		this.downloadGb = updated.getDownloadGb();
		this.downloadType = updated.getDownloadType();
		this.userSeq = updated.getUserSeq();
	}
}