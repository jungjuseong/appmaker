package com.clbee.appmaker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_market")
public class Market implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="market_seq")
	private int marketSeq;

	@Column(name="app_isiap")
	private String appIsiap;

	@Column(name="market_url")
	private String marketUrl;

	@Column(name="ver_num")
	private String verNum;

	public void setMarketVO(Market updated) {
		this.appIsiap = updated.getAppIsiap();
		this.marketUrl = updated.getMarketUrl();
		this.verNum = updated.getVerNum();
	}
}