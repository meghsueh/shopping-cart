package com.dbs.coding.challenge.shopping.cart;

import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncListener {

	@Autowired
	MessageProducer producer;
	@Autowired
	MessageProcessor processer;
	
	@Async
	public void onMessage() {
		System.out.println(Thread.currentThread().getName() + " - Run AsyncListener.onMessage().");
		BlockingQueue<CartItem> queue = producer.getQueue();
		while (true) {
			try {
				processer.process(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
