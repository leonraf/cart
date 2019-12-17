package it.mandese.carrello.services;

import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.Cart;
import it.mandese.carrello.entities.CartItem;
import it.mandese.carrello.exceptions.CartItemNotFoundException;
import it.mandese.carrello.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartItemService
{
  @Autowired
  CartItemRepository cartItemRepository;


  @Transactional
  public CartItem update(CartItemDto cartItemDto, final String cartToken) throws CartItemNotFoundException
  {
    Optional<CartItem> cartItemPersisted = cartItemRepository.findCartItemByCartAndId(cartToken, cartItemDto.getId());
    if (!cartItemPersisted.isPresent()) {
      throw new CartItemNotFoundException();
    }

    CartItem cartItem = cartItemPersisted.get();

    if (cartItemDto.getQuantity() == 0) {
      cartItemRepository.delete(cartItem);
      return null;
    }

    cartItem.setProductId(cartItemDto.getProductId());
    cartItem.setPrice(cartItemDto.getPrice());
    cartItem.setDescription(cartItemDto.getDescription());
    cartItem.setName(cartItemDto.getName());
    cartItem.setQuantity(cartItemDto.getQuantity());

    return cartItemRepository.save(cartItem);
  }
}
