package it.mandese.carrello.converters;

import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.CartItem;
import org.springframework.core.convert.converter.Converter;

public class CartItemConverter implements Converter<CartItem, CartItemDto>
{

  @Override
  public CartItemDto convert(final CartItem cartItem)
  {
    CartItemDto cartItemDto = new CartItemDto();
    cartItemDto.setDescription(cartItem.getDescription());
    cartItemDto.setName(cartItem.getName());
    cartItemDto.setPrice(cartItem.getPrice());
    cartItemDto.setProductId(cartItem.getProductId());
    cartItemDto.setQuantity(cartItem.getQuantity());
    cartItemDto.setId(cartItem.getId());
    return cartItemDto;
  }
}
