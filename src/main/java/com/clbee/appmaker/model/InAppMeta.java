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
@Table(name="tb_inapp_meta")
public class InAppMeta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inappmeta_seq")
	private Integer inAppMetaSeq;

	@Column(name="inapp_seq")
	private Integer inAppSeq;

	@Column(name="inappmeta_subtitle")
	private String inAppMetaSubtitle;

	@Column(name="inappmeta_author")
	private String inAppMetaAuthor;

	@Column(name="inappmeta_translator")
	private String inAppMetaTranslator;

	@Column(name="inappmeta_page")
	private String inAppMetaPage;

	@Column(name="inappmeta_ISBN")
	private String inAppMetaISBN;

	@Column(name="inappmeta_status")
	private String inAppMetaStatus;

	@Column(name="inappmeta_price")
	private String inAppMetaPrice;

	@Column(name="inappmeta_distributor")
	private String inAppMetaDistributor;

	@Column(name="inappmeta_size")
	private String inAppMetaSize;

	@Column(name="inappmeta_description")
	private String inAppMetaDescription;

	@Column(name="inappmeta_buildflag")
	private String inAppMetaBuildflag;

	@Column(name="inappmeta_cover1")
	private String inAppMetaCover1;

	@Column(name="inappmeta_cover2")
	private String inAppMetaCover2;

	@Column(name="inappmeta_cover3")
	private String inAppMetaCover3;

	@Column(name="inappmeta_cover4")
	private String inAppMetaCover4;

	@Column(name="inappmeta_body")
	private String inAppMetaBody;

	@Override
	public String toString() {
		return "InAppMeta [inAppMetaSeq=" + inAppMetaSeq + ", inAppSeq="
				+ inAppSeq
				+ ", inAppMetaSubtitle=" + inAppMetaSubtitle
				+ ", inAppMetaAuthor=" + inAppMetaAuthor
				+ ", inAppMetaTranslator=" + inAppMetaTranslator
				+ ", inAppMetaPage=" + inAppMetaPage + ", inAppMetaISBN="
				+ inAppMetaISBN + ", inAppMetaStatus=" + inAppMetaStatus
				+ ", inAppMetaPrice=" + inAppMetaPrice
				+ ", inAppMetaDistributor=" + inAppMetaDistributor
				+ ", inAppMetaSize=" + inAppMetaSize
				+ ", inAppMetaDescription=" + inAppMetaDescription
				+ ", inAppMetaBuildflag=" + inAppMetaBuildflag
				+ ", inAppMetaCover1=" + inAppMetaCover1 + ", inAppMetaCover2="
				+ inAppMetaCover2 + ", inAppMetaCover3=" + inAppMetaCover3
				+ ", inAppMetaCover4=" + inAppMetaCover4 + ", inAppMetaBody="
				+ inAppMetaBody + "]";
	}
}
