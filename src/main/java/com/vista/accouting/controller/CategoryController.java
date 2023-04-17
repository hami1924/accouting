package com.vista.accouting.controller;


import com.vista.accouting.controller.adpter.CategoryDtoAdepter;
import com.vista.accouting.controller.dto.CategoryDto;
import com.vista.accouting.dal.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryDtoAdepter categoryDtoAdepter;

    public CategoryController(CategoryDtoAdepter categoryDtoAdepter) {
        this.categoryDtoAdepter = categoryDtoAdepter;
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Category insert(@RequestBody CategoryDto tagDto)  {
        return categoryDtoAdepter.insert(tagDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Category edit(@PathVariable String id,@RequestBody CategoryDto tagDto)  {
        return categoryDtoAdepter.editTag(id,tagDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Category get(@PathVariable String id)  {
        return categoryDtoAdepter.findById(id);
    }

    @GetMapping("/user/{userId}/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Category> clientList(@PathVariable String userId)  {
        return categoryDtoAdepter.listByUserId(userId);
    }
    @GetMapping("/list/admin")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Category> adminList()  {
        return categoryDtoAdepter.adminList();
    }
}
