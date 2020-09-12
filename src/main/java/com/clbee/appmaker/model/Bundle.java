package com.clbee.appmaker.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_bundle")
public class Bundle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bundle_seq")
	private Integer bundleSeq;

	@Column(name="app_seq")
	private Integer appSeq;

	@Column(name="bundle_name")
	private String bundleName;

	@Column(name="os_type")
	private Integer osType;

	@Column(name="prov_seq")
	private Integer provSeq;

	@Column(name="prov_test_gb")
	private String provTestGb;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(nullable=true, name="app_seq", referencedColumnName="app_seq", insertable=false, updatable=false)
	private App app;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(nullable=true, name="prov_seq", referencedColumnName="prov_seq", insertable=false, updatable=false)
	private Provision provison;

}