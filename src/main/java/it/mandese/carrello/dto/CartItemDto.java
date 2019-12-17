package it.mandese.carrello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto
{
  private String name;
  private String description;
  private Double price;
  private Long productId;
  private int quantity;
  private Long id;

}
