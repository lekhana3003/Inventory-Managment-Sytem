package com.example.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name="inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invId", updatable = false, nullable = false)

	private Long invId;
	@NotNull
    @Size(max = 50)
	private String invName;
	@NotNull
    @Size(max = 250)
	private String invDesc;
	
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public Long getInvId() {
		return invId;
	}
	public void setInvId(Long invId) {
		this.invId = invId;
	}
	public String getInvDesc() {
		return invDesc;
	}
	public void setInvDesc(String invDesc) {
		this.invDesc = invDesc;
	}
	
	public Inventory(String name,String desc) {
		// TODO Auto-generated constructor stub
		this.invName=name;
		this.invDesc=desc;
	}
	public Inventory() {
		
	}
	@Override
	public String toString() {
		return "Inventory [invId=" + invId + ", invName=" + invName + ", invDesc=" + invDesc + "]";
	}
}
