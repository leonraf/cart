package it.mandese.carrello;

import it.mandese.carrello.dto.CartDto;
import it.mandese.carrello.dto.CartItemDto;
import it.mandese.carrello.entities.Cart;
import it.mandese.carrello.exceptions.CartNotFoundException;
import it.mandese.carrello.services.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CarrelloApplicationTests {

	@Autowired
	CartService cartService;

	@Test
	void contextLoads() {
	}

	@Test
	void createCart() {
		Cart cart = cartService.createCart();

		assertThat(cart.getId() != null && !StringUtils.isEmpty(cart.getId()));
	}

	@Test
	void addToCart() throws CartNotFoundException
	{
		Cart cart = cartService.createCart();
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setQuantity(1);
		cartItemDto.setName("Prova");
		cartItemDto.setDescription("Prova description");
		cartItemDto.setPrice(new Double(10));
		cartService.addToCart(cart.getId(), cartItemDto);

		cart = cartService.getCartFromId(cart.getId());

		assertThat(cart.getCartItems().size() == 1);
		assertThat(cart.getCartItems().get(0).getDescription().equals("Prova description"));
		assertThat(cart.getCartItems().get(0).getName().equals("Prova"));
		assertThat(cart.getCartItems().get(0).getQuantity() == 1);
		assertThat(cart.getCartItems().get(0).getPrice().equals(new Double(10)));
	}

	@Test
	void updateCartItem() throws Exception
	{
		Cart cart = cartService.createCart();
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setQuantity(1);
		cartItemDto.setName("Prova");
		cartItemDto.setDescription("Prova description");
		cartItemDto.setPrice(new Double(10));
		cartService.addToCart(cart.getId(), cartItemDto);

		cart = cartService.getCartFromId(cart.getId());

		CartItemDto updatecartItemDto = new CartItemDto();
		updatecartItemDto.setQuantity(2);
		updatecartItemDto.setName("Prova 2");
		updatecartItemDto.setDescription("Prova description 2");
		updatecartItemDto.setPrice(new Double(5));
		cartService.updateCartItem(cart.getId(), cart.getCartItems().get(0).getId(), updatecartItemDto);

		cart = cartService.getCartFromId(cart.getId());

		assertThat(cart.getCartItems().size() == 1);
		assertThat(cart.getCartItems().get(0).getDescription().equals("Prova description 2"));
		assertThat(cart.getCartItems().get(0).getName().equals("Prova 2"));
		assertThat(cart.getCartItems().get(0).getQuantity() == 2);
		assertThat(cart.getCartItems().get(0).getPrice().equals(new Double(5)));

		CartItemDto deletecartItemDto = new CartItemDto();
		deletecartItemDto.setQuantity(0);

		cartService.updateCartItem(cart.getId(), cart.getCartItems().get(0).getId(), deletecartItemDto);

		cart = cartService.getCartFromId(cart.getId());

		assertThat(cart.getCartItems().size() == 0);
	}

}
