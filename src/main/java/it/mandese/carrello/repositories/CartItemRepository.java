package it.mandese.carrello.repositories;

import it.mandese.carrello.entities.Cart;
import it.mandese.carrello.entities.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long>
{
  @Query("select cartItem from CartItem cartItem where cartItem.cart.id = :cartToken and cartItem.id = :id")
  Optional<CartItem> findCartItemByCartAndId(@Param("cartToken") String cartToken, @Param("id") Long id);
}
