package com.vista.accouting.service;

import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.dal.repo.TagRepository;
import com.vista.accouting.enums.TagType;
import com.vista.accouting.exceptions.NotFoundUserException;
import com.vista.accouting.service.models.TagDefaultPageModel;
import org.bson.BSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public TagEntity insert(TagEntity tagEntity) {
        return repository.save(tagEntity);
    }

    @Override
    public TagEntity findById(String id) {

        Optional<TagEntity> tagEntity = repository.findById(id);
        if (tagEntity.isPresent()) {
            return tagEntity.get();
        }
        throw new NotFoundUserException();
    }

    @Override
    public TagEntity editById(String id, TagEntity tag) {
        Optional<TagEntity> tagEntity = repository.findById(id);
        if (tagEntity.isPresent()) {

            if (Objects.nonNull(tag.getName()) &&
                    !tagEntity.get().getName().equals(tag.getName())) {
                tagEntity.get().setName(tag.getName());
            }

            if (Objects.nonNull(tag.getLang()) &&
                    !tagEntity.get().getLang().equals(tag.getLang())) {
                tagEntity.get().setLang(tag.getLang());
            }

            return tagEntity.get();
        }
        throw new NotFoundUserException();
    }

    @Override
    public List<TagEntity> list(String userId) {
        return repository.findByUserIdOrAndTagType(userId, TagType.GENERAL.name());
    }

    @Override
    public List<TagEntity> listAdmin() {
        return repository.findByUserIdOrAndTagType(null, TagType.GENERAL.name());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public TagEntity findTagInContent(String userId, String content) {
        List<TagEntity> list=repository.findByUserIdOrAndTagType(userId, TagType.GENERAL.name());

        return null;
    }


}
