package it.mandese.carrello.repositories;

import it.mandese.carrello.entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String>
{

}
