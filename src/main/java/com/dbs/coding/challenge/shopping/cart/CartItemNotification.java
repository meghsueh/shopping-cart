package com.dbs.coding.challenge.shopping.cart;

import org.springframework.stereotype.Service;

@Service
public class CartItemNotification {

	public boolean handleCartItemReachMaxRetry(CartItem cartItem) {
		//There should be an external notification service to notify user that 
		//adding cart item action was failed.
		//Notification service should also smart enough to create a ticket with system support team with
		//CartItemId to trace the log for details.
		//Skip actual implementation and return true
		return true;
	}
}
