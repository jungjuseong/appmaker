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
@Table(name="tb_template_sub")
public class TemplateSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="templatesub_seq")
	private int templatesubSeq;

	@Column(name="template_seq")
	private int templateSeq;

	@Column(name="user_seq")
	private int userSeq;
}