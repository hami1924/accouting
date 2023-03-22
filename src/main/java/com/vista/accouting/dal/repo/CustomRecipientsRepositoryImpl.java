package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.service.models.MessageQuery;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CustomRecipientsRepositoryImpl implements CustomRecipientsRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String  QUERY_DATE="date";



    @Override
    public Page<Recipients> findByQueryCustom(MessageQuery messageQuery, Pageable pageable) {
        Query query = new Query().with(pageable);
        query=conditionQuery(query,messageQuery);
        List<Recipients> carDocs = mongoTemplate.find(query, Recipients.class);

        Query finalQuery = query;
        return PageableExecutionUtils.getPage(
                carDocs,
                pageable,
                () -> mongoTemplate.count(finalQuery.limit(-1).skip(-1), Recipients.class));
    }

    @Override
    public List<Recipients> findByQueryCustomWithoutPageable(MessageQuery messageQuery) {
        Query query = new Query();
        query=conditionQuery(query,messageQuery);
        return mongoTemplate.find(query, Recipients.class);
    }

    private Query conditionQuery(Query query,MessageQuery messageQuery){

        query.addCriteria(Criteria.where("user._id").is(new ObjectId(messageQuery.getUserId())));

        if (!Objects.isNull(messageQuery.getMessageType())) {
            query.addCriteria(Criteria.where("messageType").is(messageQuery.getMessageType().toString()));
        }

        if (!Objects.isNull(messageQuery.getCurrencyType())) {
            query.addCriteria(Criteria.where("currency").is(messageQuery.getCurrencyType().toString()));
        }

        if (!Objects.isNull(messageQuery.getBanksEnum())) {
            query.addCriteria(Criteria.where("smsNumberAlert.banks").is(messageQuery.getBanksEnum().toString()));
        }
        if (!Objects.isNull(messageQuery.getFrom()) || !Objects.isNull(messageQuery.getTo())) {
            if (!Objects.isNull(messageQuery.getFrom()) && !Objects.isNull(messageQuery.getTo())){
                query.addCriteria(new Criteria().andOperator(Criteria.where(QUERY_DATE).gte(messageQuery.getFrom()),
                        Criteria.where(QUERY_DATE).lte(messageQuery.getTo())));
            }else if (!Objects.isNull(messageQuery.getFrom())){
                query.addCriteria(Criteria.where(QUERY_DATE).gte(messageQuery.getFrom()));
            }else if (!Objects.isNull(messageQuery.getTo())){
                query.addCriteria(Criteria.where(QUERY_DATE).lt(messageQuery.getTo()));
            }
        }
        return query;

    }
}
