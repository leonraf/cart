package it.mandese.carrello.services;

import it.mandese.carrello.dto.CartDto;
import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.Cart;
import it.mandese.carrello.entities.CartItem;
import it.mandese.carrello.exceptions.CartItemNotFoundException;
import it.mandese.carrello.exceptions.CartNotFoundException;
import it.mandese.carrello.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class CartService
{
  @Autowired
  CartRepository cartRepository;

  @Autowired
  ConversionService conversionService;

  @Autowired
  CartItemService cartItemService;

  @Autowired
  EntityManager entityManager;


  @Transactional
  public Cart createCart()
  {
    return save(new Cart());
  }

  @Transactional
  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }


  public Cart getCartFromId(final String cartToken) throws CartNotFoundException
  {
    Optional<Cart> cart = cartRepository.findById(cartToken);
    if (!cart.isPresent()) {
      throw new CartNotFoundException();
    }

    return cart.get();
  }

  @Transactional
  public CartDto addToCart(final String cartToken, final CartItemDto cartItem) throws CartNotFoundException
  {
    Cart cart = getCartFromId(cartToken);


    CartItem cartItemToPersist = conversionService.convert(cartItem, CartItem.class);

    cartItemToPersist.setCart(cart);

    cart.getCartItems().add(cartItemToPersist);

    cart = save(cart);

    CartDto cartDto = conversionService.convert(cart, CartDto.class);

    return cartDto;
  }

  public CartDto getCartFromToken(final String cartToken) throws CartNotFoundException
  {
    Cart cart = getCartFromId(cartToken);
    return conversionService.convert(cart, CartDto.class);
  }



  @Transactional(rollbackFor = Exception.class)
  public CartDto updateCartItem(final String cartToken, final Long cartItemId, final CartItemDto cartItem) throws Exception
  {
    cartItem.setId(cartItemId);
    cartItemService.update(cartItem, cartToken);
    entityManager.flush();
    Cart cart = getCartFromId(cartToken);

    return conversionService.convert(cart, CartDto.class);
  }
}
