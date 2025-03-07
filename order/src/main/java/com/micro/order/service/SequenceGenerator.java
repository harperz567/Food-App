package com.micro.order.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.micro.order.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGenerator {
    @Autowired
    private MongoOperations mongoOperations;

    public int generateNextOrderId() {
        System.out.println("Starting ID generation");
        Query query = new Query(Criteria.where("_id").is("sequence"));
        Update update = new Update().inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        Sequence counter = mongoOperations.findAndModify(
            query,
            update,
            options,
            Sequence.class);

        return counter.getSequence();
    }
}
