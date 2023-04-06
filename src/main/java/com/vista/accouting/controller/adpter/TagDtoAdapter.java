package com.vista.accouting.controller.adpter;


import com.vista.accouting.controller.dto.TagDto;
import com.vista.accouting.controller.mapper.TagDtoMapper;
import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.service.TagService;
import com.vista.accouting.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TagDtoAdapter {

    private final UserService userService;
    private final TagService tagService;


    public TagDtoAdapter(UserService userService, TagService tagService) {
        this.userService = userService;
        this.tagService = tagService;

    }

    public TagEntity insert(TagDto tagDto){
        validateUser(tagDto);
        return tagService.insert(TagDtoMapper.INSTANCE.getEntity(tagDto));
    }

    public TagEntity editTag(String userId,TagDto tagDto){
        validateUser(tagDto);
        return tagService.editById(userId,TagDtoMapper.INSTANCE.getEntity(tagDto));
    }

    public TagEntity findById(String id){
        return tagService.findById(id);
    }

    public List<TagEntity> listByUserId(String userId){
            return tagService.list(userId);
    }

    public List<TagEntity> adminList(){
        return tagService.listAdmin();
    }


    private void validateUser(TagDto tagDto) {
        if (Objects.nonNull(tagDto.getUserId()))
          userService.getById(tagDto.getUserId());
    }
}
