package it.mandese.carrello.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem
{
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;
  private Double price;
  private Long productId;
  private int quantity;

  @ManyToOne
  private Cart cart;

}
