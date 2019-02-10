package com.dbs.coding.challenge.shopping.cart;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public interface Listener {

	@Retryable(value = { Exception.class }, maxAttempts = 2, backoff = @Backoff(delay = 5000))
	public CartItem process(CartItem cartItem);

	@Recover
	public CartItem recover(CartItem cartItem);
}
