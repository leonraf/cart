package it.mandese.carrello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto
{
  private List<CartItemDto> cartItems = new ArrayList<>();
  private String id;

}
