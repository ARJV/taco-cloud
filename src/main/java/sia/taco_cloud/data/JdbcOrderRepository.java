// Эта реалицация была для JDBC

//package sia.taco_cloud.data;
//
//import org.springframework.asm.Type;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import sia.taco_cloud.Taco;
//import sia.taco_cloud.TacoOrder;
//
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository {
//
//    private JdbcOperations jdbcOperations;
//
//    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
//        this.jdbcOperations = jdbcOperations;
//    }
//
//    @Override
//    @Transactional
//    public TacoOrder save(TacoOrder order) {
//        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
//                "INSERT INTO Taco_Order "
//                        + "(delivery_name, delivery_street, delivery_city, "
//                        + "delivery_state, delivery_zip, cc_number, "
//                        + "cc_expiration, cc_cvv, placed_at) "
//                        + "VALUES (?,?,?,?,?,?,?,?,?)",
//                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
//        );
//        pscf.setReturnGeneratedKeys(true);
//
//        order.setPlacedAt(new Date());
//
//        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
//                Arrays.asList(
//                        order.getDeliveryName(),
//                        order.getDeliveryStreet(),
//                        order.getDeliveryCity(),
//                        order.getDeliveryState(),
//                        order.getDeliveryZip(),
//                        order.getCcNumber(),
//                        order.getCcExpiration(),
//                        order.getCcCVV(),
//                        order.getPlacedAt()
//                )
//        );
//
//
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long orderId = keyHolder.getKey().longValue();
//        order.setId(orderId);
//
//        List<Taco> tacos = order.getTacos();
//        int i = 0;
//        for (Taco taco : tacos) {
//            saveTaco(orderId, i++, taco);
//        }
//
//        return order;
//    }
//
//    private long saveTaco(long orderId, int orderKey, Taco taco) {
//        taco.setCreatedAt(new Date());
//
//        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
//                "INSERT INTO TACO"
//                        + "(name, created_at, taco_order, taco_order_key) "
//                        + "VALUES (?,?,?,?)",
//                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
//        );
//        pscf.setReturnGeneratedKeys(true);
//
//        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
//                Arrays.asList(
//                        taco.getName(),
//                        taco.getCreatedAt(),
//                        orderId,
//                        orderKey)
//        );
//
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long tacoId = keyHolder.getKey().longValue();
//        taco.setId(tacoId);
//
//        saveIngredientRefs(tacoId, taco.getIngredients());
//
//        return tacoId;
//    }
//
//    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
//        int key = 0;
//        for (IngredientRef ingredientRef : ingredientRefs) {
//            jdbcOperations.update(
//                    "INSERT INTO  INGREDIENT_REF (ingredient, taco, taco_key) VALUES (?, ?, ?)",
//                    ingredientRef.getIngredient(), tacoId, key++
//            );
//        }
//    }
//}