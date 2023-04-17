package com.vista.accouting.service;


import com.vista.accouting.dal.entity.Category;

import java.util.List;

public interface CategoryService {

    Category insert(Category category);

    Category findById(String id);

    Category editById(String id,Category category);

    List<Category> list(String userId);

    List<Category> listAdmin();
}
