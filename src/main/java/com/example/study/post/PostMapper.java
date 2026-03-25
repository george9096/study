package com.example.study.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<PostDto> findAll();

    PostDto findById(@Param("id") long id);

    int insert(PostCreateRequest request);

    int update(PostUpdateRequest request);

    int delete(long id);
}
