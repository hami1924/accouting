package com.vista.accouting.dal.repo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.service.models.MessageQuery;
import com.vista.accouting.service.models.TagDefaultPageModel;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;
import static com.mongodb.client.model.Filters.*;

import java.util.*;

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

    @Override
    public List<Recipients> findUserIdAndMessageHash(ObjectId user, String hashMessage) {
        Query query = new Query();
        query=conditionQuery(query,user,hashMessage);
        return mongoTemplate.find(query, Recipients.class);    }

    @Override
    public List<TagDefaultPageModel> findUniqueTagByGroup(String userId) {
        List<TagDefaultPageModel> list=new ArrayList<>();
        MatchOperation aggregationOperation_Match =Aggregation.match(Criteria.where("user._id").is(new ObjectId(userId)));
        GroupOperation aggregationOperation_Size = Aggregation.group("$tag.name")
                .count().as("count")
                .sum("$amount").as("tagsum");
        Aggregation aggregation =Aggregation.newAggregation(aggregationOperation_Match,aggregationOperation_Size);
        AggregationResults<Document> results = mongoTemplate.aggregate( aggregation,Recipients.class,Document.class);
        for (Document document:results.getMappedResults()){
                        TagDefaultPageModel tagDefaultPageModel=new TagDefaultPageModel(
                    (String) document.get("_id"),(Integer)document.get("count"),(Double) document.get("tagsum"));
            list.add(tagDefaultPageModel);
        }
        return list;
    }

    @Override
    public List<TagDefaultPageModel> findUniqueCategoryByGroup(MessageQuery messageQuery) {
        List<TagDefaultPageModel> list=new ArrayList<>();
        MatchOperation aggregationOperation_Match =Aggregation.match(Criteria.where("user._id").is(new ObjectId(messageQuery.getUserId())));
        MatchOperation aggregationOperation_NotMatch =Aggregation.match(Criteria.where("category.name").ne("CREDIT"));

        MatchOperation aggregationOperation_Date =Aggregation.match(new Criteria().andOperator(Criteria.where(QUERY_DATE).gte(messageQuery.getFrom()),
                Criteria.where(QUERY_DATE).lte(messageQuery.getTo())));


        GroupOperation aggregationOperation_Size = Aggregation.group("$category.name")
                .count().as("count")
                .sum("$amount").as("tagsum");
        Aggregation aggregation =Aggregation.newAggregation(aggregationOperation_Match,aggregationOperation_NotMatch
                ,aggregationOperation_Date,aggregationOperation_Size);
        AggregationResults<Document> results = mongoTemplate.aggregate( aggregation,Recipients.class,Document.class);
        for (Document document:results.getMappedResults()){
            TagDefaultPageModel tagDefaultPageModel=new TagDefaultPageModel(
                    (String) document.get("_id"),(Integer)document.get("count"),(Double) document.get("tagsum"));
            list.add(tagDefaultPageModel);
        }
        return list;
    }

    private Query conditionQuery(Query query,MessageQuery messageQuery){

        query.addCriteria(Criteria.where("user._id").is(new ObjectId(messageQuery.getUserId())));

        if (!Objects.isNull(messageQuery.getMessageType())) {
            query.addCriteria(Criteria.where("messageType").is(messageQuery.getMessageType().toString()));
        }

        if (!Objects.isNull(messageQuery.getCurrencyType())) {
            query.addCriteria(Criteria.where("currency").is(messageQuery.getCurrencyType().toString()));
        }

        if (!Objects.isNull(messageQuery.getTag())) {
            query.addCriteria(Criteria.where("tag.name").is(messageQuery.getTag()));
        }

        if (!Objects.isNull(messageQuery.getCategory())) {
            query.addCriteria(Criteria.where("category.name").is(messageQuery.getCategory()));
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
        query.with(Sort.by(Sort.Direction.DESC,"date"));
        return query;

    }

    private Query conditionQuery(Query query,ObjectId user, String hashMessage) {

        query.addCriteria(Criteria.where("user.id").is(user));


            query.addCriteria(Criteria.where("messageHash").is(hashMessage));

            return query;

    }
}
