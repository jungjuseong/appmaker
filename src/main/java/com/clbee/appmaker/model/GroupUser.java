package com.clbee.appmaker.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="tb_group_user")
public class GroupUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_seq")
	private Integer groupSeq;
	
	@Column(name="group_name")
	private String groupName;
	
	@Column(name="member_seq")
	private Integer memberSeq;
	
	@Column(name="company_seq")
	private Integer companySeq;
	
	@Column(name = "menu_large")
	private String menuLarge;
	
	@Column(name="menu_medium")
	private String menuMedium;
	
	@Column(name="menu_function")
	private String menuFunction;
	
	@Column(name="member_gb")
	private String memberGb;
	
}
