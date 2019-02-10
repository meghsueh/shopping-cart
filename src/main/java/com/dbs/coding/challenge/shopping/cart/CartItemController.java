package com.dbs.coding.challenge.shopping.cart;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {

	@Autowired
	CartItemRepository repository;
	@Autowired
	AsyncListener listener;
	@Autowired
	MessageProducer producer;

	private Random rand = new Random();
	
	@RequestMapping(value = "/cartItem/start")
	public String startListener() {
		listener.onMessage();
		return "Start message listener.";
	}

	@RequestMapping(value = "/cartItem/add")
	public String addCartItem() {
		CartItem cartItem = new CartItem(rand.nextInt(Integer.MAX_VALUE), rand.nextInt(Integer.MAX_VALUE));
		producer.add(cartItem);
		return cartItem.toString();
	}

	@RequestMapping("/cartItem/{cartItemId}")
	public String findByCartItemId(@PathVariable Long cartItemId) {
		return repository.findById(cartItemId).toString();
	}
}
