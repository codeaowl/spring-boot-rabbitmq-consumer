package com.microservice.consumermicroservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322432303657657201L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private long id;
	@Column(name = "productName")//not mandatory
	private String productName;
	@Column(name = "productDescription")
	private String productDescription;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "messageReceived")
	private boolean messageReceived;
	@Column(name = "messageCount")
	private Integer messageCount = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived(boolean messageReceived) {
		this.messageReceived = messageReceived;
	}

	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}

}
