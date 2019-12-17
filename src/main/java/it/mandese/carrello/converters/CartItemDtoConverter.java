package it.mandese.carrello.converters;

import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.CartItem;
import org.springframework.core.convert.converter.Converter;

public class CartItemDtoConverter implements Converter<CartItemDto, CartItem>
{

  @Override
  public CartItem convert(final CartItemDto cartItemDto)
  {
    CartItem cartItem = new CartItem();
    cartItem.setDescription(cartItemDto.getDescription());
    cartItem.setName(cartItemDto.getName());
    cartItem.setPrice(cartItemDto.getPrice());
    cartItem.setProductId(cartItemDto.getProductId());
    cartItem.setQuantity(cartItemDto.getQuantity());
    cartItem.setId(cartItemDto.getId());
    return cartItem;
  }
}
