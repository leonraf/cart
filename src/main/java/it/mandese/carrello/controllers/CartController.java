package it.mandese.carrello.controllers;

import it.mandese.carrello.dto.CartDto;
import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.Cart;
import it.mandese.carrello.entities.CartItem;
import it.mandese.carrello.exceptions.CartItemNotFoundException;
import it.mandese.carrello.exceptions.CartNotFoundException;
import it.mandese.carrello.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController
{

  @Autowired
  CartService cartService;

  @PostMapping(value = "/create")
  public ResponseEntity<String> createCart() {

    Cart cart = cartService.createCart();
    return new ResponseEntity<String>(cart.getId(), HttpStatus.OK);
  }

  @GetMapping(value = "")
  public ResponseEntity<CartDto> getCart(@RequestParam(name = "cart-token", required = true) String cartToken) throws CartNotFoundException
  {

    CartDto cart = cartService.getCartFromToken(cartToken);

    return new ResponseEntity<CartDto>(cart, HttpStatus.OK);
  }

  @PostMapping(value = "/add")
  public ResponseEntity<CartDto> addToCart(@RequestParam(name = "cart-token", required = true) String cartToken,
                                           @RequestBody CartItemDto cartItem) throws CartNotFoundException
  {

    CartDto cart = cartService.addToCart(cartToken, cartItem);

    return new ResponseEntity<CartDto>(cart, HttpStatus.OK);
  }

  @PostMapping(value = "/cart-item/{id}/update")
  public ResponseEntity<CartDto> updateCartItem(@RequestParam(name = "cart-token", required = true) String cartToken,
                                           @PathVariable(name = "id") Long cartItemId,
                                           @RequestBody CartItemDto cartItem) throws Exception
  {
    CartDto cart = cartService.updateCartItem(cartToken, cartItemId, cartItem);

    return new ResponseEntity<CartDto>(cart, HttpStatus.OK);
  }

}
