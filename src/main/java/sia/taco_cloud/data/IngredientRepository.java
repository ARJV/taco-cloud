package sia.taco_cloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}

// Эта реалицация была для JDBC
//public interface IngredientRepository {
//
//    Iterable<Ingredient> findAll();
//    Optional<Ingredient> findById(String id);
//    Ingredient save(Ingredient ingredient);
//}
