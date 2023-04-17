package com.vista.accouting.service;

import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.dal.repo.TagRepository;
import com.vista.accouting.enums.TagType;
import com.vista.accouting.exceptions.NotFoundUserException;
import com.vista.accouting.exceptions.UserFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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
    public TagEntity findByName(String name) {
        Optional<TagEntity> tag= repository.findByName(name);
        if (tag.isPresent())
            return tag.get();

        throw new UserFoundException();
    }

    @Override
    public List<TagEntity> list(String userId) {
        return repository.findByUserIdOrTagType(userId, TagType.GENERAL.name());
    }

    @Override
    public List<TagEntity> listAdmin() {
        return repository.findByUserIdOrTagType(null, TagType.GENERAL.name());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public TagEntity findTagInContent(String userId, String content) {
        List<TagEntity> list=repository.findByUserIdOrTagType(userId, TagType.GENERAL.name());

        return null;
    }


}
