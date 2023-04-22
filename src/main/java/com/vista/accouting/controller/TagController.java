package com.vista.accouting.controller;

import com.vista.accouting.controller.adpter.TagDtoAdapter;
import com.vista.accouting.controller.dto.TagDto;
import com.vista.accouting.dal.entity.TagEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tag")
public class TagController {

    private final TagDtoAdapter tagDtoAdapter;

    public TagController(TagDtoAdapter tagDtoAdapter) {
        this.tagDtoAdapter = tagDtoAdapter;
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TagEntity insert(@RequestBody TagDto tagDto)  {
        return tagDtoAdapter.insert(tagDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TagEntity edit(@PathVariable String id,@RequestBody TagDto tagDto)  {
        return tagDtoAdapter.editTag(id,tagDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TagEntity get(@PathVariable String id)  {
        return tagDtoAdapter.findById(id);
    }

    @GetMapping("/user/{userId}/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TagEntity> clientList(@PathVariable String userId)  {
        return tagDtoAdapter.listByUserId(userId);
    }
    @GetMapping("/list/admin")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TagEntity> adminList()  {
        return tagDtoAdapter.adminList();
    }

    @GetMapping("/category/{categoryId}/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<TagEntity> listByCategoryId(@PathVariable String categoryId)  {
        return tagDtoAdapter.listByCategoryId(categoryId);
    }
}
