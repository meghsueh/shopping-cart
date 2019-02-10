package com.dbs.coding.challenge.shopping.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	List<CartItem> findByCartItemId(Long cartId);

}
