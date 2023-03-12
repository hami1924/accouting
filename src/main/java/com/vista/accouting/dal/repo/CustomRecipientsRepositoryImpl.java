package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.service.models.MessageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomRecipientsRepositoryImpl implements CustomRecipientsRepository{

    private final MongoTemplate mongoTemplate;

    public CustomRecipientsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Recipients> findByQueryCustom(MessageQuery messageQuery, Pageable pageable) {
        Query query = new Query().with(pageable);

        List<Recipients> carDocs = mongoTemplate.find(query, Recipients.class);

        return PageableExecutionUtils.getPage(
                carDocs,
                pageable,
                () -> mongoTemplate.count(query.limit(-1).skip(-1), Recipients.class));
    }
}
