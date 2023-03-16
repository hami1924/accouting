package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.service.models.MessageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomRecipientsRepository {

    Page<Recipients> findByQueryCustom(MessageQuery messageQuery, Pageable pageable);
    List<Recipients> findByQueryCustomWithoutPageable(MessageQuery messageQuery);

}
