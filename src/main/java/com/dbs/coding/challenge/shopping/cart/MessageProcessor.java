package com.dbs.coding.challenge.shopping.cart;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor implements Listener {

	@Autowired
	CartItemRepository repository;
	@Autowired
	MessageProducer producer;
	@Autowired
	CartItemNotification notification;
	@Autowired
	HealthEndpoint healthEndpoint;

	Integer processCountMax = 3;

	@Override
	public CartItem process(CartItem cartItem) {

		cartItem.increaseProcessCount();

		// Send cart item to notification service if process count is greater than max
		// process count
		if (cartItem.getProcessCount() > processCountMax)
			notification.handleCartItemReachMaxRetry(cartItem);
		else {
			System.out.println("Try to save cart item processCount = " + cartItem.getProcessCount());
			repository.save(cartItem);
		}

		return cartItem;
	}

	@Override
	public CartItem recover(CartItem cartItem) {
		// Send cart item to notification service if process count is greater than max
		// process count
		if (cartItem.getProcessCount() > processCountMax)
			notification.handleCartItemReachMaxRetry(cartItem);
		else {
			// put message back to queue to retry if process count is less than max
			producer.add(cartItem);
			// wait until service becomes health
			while (healthEndpoint.health().getStatus() != Status.UP) {
				try {
					// sleep for 5 seconds and check health status again
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}

		return cartItem;
	}

}
