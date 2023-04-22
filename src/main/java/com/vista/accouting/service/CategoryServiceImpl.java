package com.vista.accouting.service;

import com.vista.accouting.dal.entity.Category;
import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.dal.repo.CategoryRepository;
import com.vista.accouting.enums.TagType;
import com.vista.accouting.exceptions.NotFoundUserException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category insert(Category category) {
        return repository.save(category);
    }

    @Override
    public Category findById(String id) {
        Optional<Category> category = repository.findById(new ObjectId(id));
        if (category.isPresent()) {
            return category.get();
        }
        throw new NotFoundUserException();
    }

    @Override
    public Category editById(String id, Category category) {
        Optional<Category> categoryOld = repository.findById(id);
        if (categoryOld.isPresent()) {

            if (Objects.nonNull(category.getName()) &&
                    !categoryOld.get().getName().equals(category.getName())) {
                categoryOld.get().setName(category.getName());
            }

            if (Objects.nonNull(category.getLang()) &&
                    !categoryOld.get().getLang().equals(category.getLang())) {
                categoryOld.get().setLang(category.getLang());
            }

            return categoryOld.get();
        }
        throw new NotFoundUserException();
    }

    @Override
    public List<Category> list(String userId) {
         return repository.findByUserIdAndTagType(userId, TagType.GENERAL.name());
    }

    @Override
    public List<Category> listAdmin() {
        return repository.findByUserIdAndTagType(null, TagType.GENERAL.name());
    }
}
