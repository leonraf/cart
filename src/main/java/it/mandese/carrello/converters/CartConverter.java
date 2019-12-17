package it.mandese.carrello.converters;

import it.mandese.carrello.dto.CartDto;
import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.Cart;
import it.mandese.carrello.entities.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class CartConverter implements Converter<Cart, CartDto>
{

  ApplicationContext applicationContext;

  @Override
  public CartDto convert(final Cart cart)
  {
    ConversionService conversionService = applicationContext.getBean(ConversionService.class);
    CartDto cartDto = new CartDto();
    cartDto.setId(cart.getId());
    for (CartItem cartItem : cart.getCartItems())
    {
      cartDto.getCartItems().add(conversionService.convert(cartItem, CartItemDto.class));
    }

    return cartDto;
  }
}
