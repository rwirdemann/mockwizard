package org.mockwizard.examples.orderservice;

import com.mongodb.DB;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;
import org.bson.types.ObjectId;

public class OrderRepository {
    private final JacksonDBCollection<Order, String> orders;

    public OrderRepository(DB db) {
        orders = JacksonDBCollection.wrap(db.getCollection("orders"), Order.class, String.class);
    }

    public DBCursor<Order> find() {
        return orders.find();
    }

    public void delete() {
        orders.remove(DBQuery.notExists("bla"));
    }

    public void create(Order order) {
        WriteResult<Order, String> save = orders.save(order);
        order.set_id(ObjectId.massageToObjectId(save.getSavedId()).toString());
    }
}
