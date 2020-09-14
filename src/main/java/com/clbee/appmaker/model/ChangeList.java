package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_changelist")
public class ChangeList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="chglist_seq")
	private int chglistSeq;

	@Column(name="board_seq")
	private int boardSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="chg_dt")
	private Date chgDt;

	@Column(name="chglist_gb")
	private String chglistGb;

	@Lob
	@Column(name="chglist_text")
	private String chglistText;

	@Column(name="ostype_gb")
	private String ostypeGb;

	@Column(name="reg_user_id")
	private String regUserId;

	@Column(name="ver_num")
	private String verNum;

	public void setChangelist(ChangeList updated) {
		this.boardSeq = updated.getBoardSeq();
		this.chgDt = updated.getChgDt();
		this.chglistGb = updated.getChglistGb();
		this.chglistText = updated.getChglistText();
		this.ostypeGb = updated.getOstypeGb();
		this.regUserId = updated.getRegUserId();
		this.verNum = updated.getVerNum();
	}
}