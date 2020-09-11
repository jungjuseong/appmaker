package com.clbee.appmaker.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="tb_group_menu")
@DynamicInsert(true)
public class GroupMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_seq")
	private Integer menuSeq;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="menu_description")
	private String menuDescription;
	
	@Column(name = "menu_type")
	private String menuType;
	
	@Column(name="connect_menu")
	private String connectMenu;
	
	@Column(name = "page_url")
	private String pageUrl;
	
	@Column(name="must_yn")
	private String mustYn;

}
