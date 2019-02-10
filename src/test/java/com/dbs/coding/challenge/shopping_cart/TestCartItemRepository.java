package com.dbs.coding.challenge.shopping_cart;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dbs.coding.challenge.shopping.cart.AppMain;
import com.dbs.coding.challenge.shopping.cart.CartItem;
import com.dbs.coding.challenge.shopping.cart.CartItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class)
public class TestCartItemRepository {

	@Autowired
	CartItemRepository repository;

	private Random rand = new Random();

	@Test
	public void testSaveCartItem() {
		CartItem cartItem = new CartItem(rand.nextInt(Integer.MAX_VALUE), rand.nextInt(Integer.MAX_VALUE));
		repository.save(cartItem);

		List<CartItem> list = repository.findByCartItemId(cartItem.getCartItemId());
		assertEquals(list.size(), 1);
		CartItem savedCartItem = list.get(0);
		assertEquals(savedCartItem.getCartItemId(), cartItem.getCartItemId());
	}
}
