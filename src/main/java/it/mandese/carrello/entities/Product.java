package it.mandese.carrello.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;
  private Double retailPrice;
  private Double salePrice;
}
