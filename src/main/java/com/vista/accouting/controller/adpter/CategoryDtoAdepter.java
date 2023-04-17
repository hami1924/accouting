package com.vista.accouting.controller.adpter;

import com.vista.accouting.controller.dto.CategoryDto;
import com.vista.accouting.controller.mapper.CategoryDtoMapper;
import com.vista.accouting.dal.entity.Category;
import com.vista.accouting.service.CategoryService;
import com.vista.accouting.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CategoryDtoAdepter {
    private final UserService userService;

    private final CategoryService categoryService;

    public CategoryDtoAdepter(UserService userService, CategoryService categoryService) {
        this.userService = userService;

        this.categoryService = categoryService;
    }

    public Category insert(CategoryDto categoryDto){
        validateUser(categoryDto);
        return categoryService.insert(CategoryDtoMapper.INSTANCE.getEntity(categoryDto));
    }

    public Category editTag(String userId,CategoryDto tagDto){
        validateUser(tagDto);
        return categoryService.editById(userId,CategoryDtoMapper.INSTANCE.getEntity(tagDto));
    }

    public Category findById(String id){
        return categoryService.findById(id);
    }

    public List<Category> listByUserId(String userId){
        return categoryService.list(userId);
    }

    public List<Category> adminList(){
        return categoryService.listAdmin();
    }


    private void validateUser(CategoryDto tagDto) {
        if (Objects.nonNull(tagDto.getUserId()))
            userService.getById(tagDto.getUserId());
    }
}
