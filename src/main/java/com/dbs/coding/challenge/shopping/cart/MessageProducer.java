package com.dbs.coding.challenge.shopping.cart;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

	BlockingQueue<CartItem> queue = new LinkedBlockingQueue<>();
	
	public boolean add(CartItem cartItem) {
		return queue.offer(cartItem);
	}
	
	public BlockingQueue<CartItem> getQueue() {
		return queue;
	}
}
