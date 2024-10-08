// // Эта реалицация была для JDBC

//package sia.taco_cloud.data;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import sia.taco_cloud.Ingredient;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class JdbcIngredientRepository implements IngredientRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Iterable<Ingredient> findAll() {
//        return jdbcTemplate.query("SELECT id, name, type FROM Ingredient",
//                this::mapRowToIngredient);
//    }
//
//    @Override
//    public Optional<Ingredient> findById(String id) {
//        List<Ingredient> result = jdbcTemplate.query(
//                "SELECT id, name, type FROM Ingredient WHERE id=?",
//                this::mapRowToIngredient,
//                id);
//
//        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
//    }
//
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update("INSERT INTO Ingredient (id, name, type) VALUES (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().toString());
//        return ingredient;
//    }
//
//    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
//        return new Ingredient(
//                row.getString("id"),
//                row.getString("name"),
//                Ingredient.Type.valueOf(row.getString("type"))
//        );
//    }
//}
