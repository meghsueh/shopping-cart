package com.dbs.coding.challenge.shopping.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CART_ITEM")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ITEM_ID", unique = true, nullable = false)
	private Long cartItemId;

	@Column(name = "CART_ID", nullable = false)
	private Integer cartId;

	@Column(name = "ITEM_ID", nullable = false)
	private Integer itemId;
	
	@Transient
	private Integer processCount = 0;

	public Integer getProcessCount() {
		return processCount;
	}

	public Integer increaseProcessCount() {
		return ++this.processCount;
	}

	public CartItem(Integer cartId, Integer itemId) {
		this.cartId = cartId;
		this.itemId = itemId;
	}
	
	public CartItem() {
		
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	@Override
	public String toString() {
		return String.format("CartItem[cartItemId=%d, cartId=%d, itemId=%d]", this.cartItemId, this.cartId,
				this.itemId);
	}

}
