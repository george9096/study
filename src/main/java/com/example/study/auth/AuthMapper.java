package com.example.study.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {
    int insertUser(@Param("email") String email,
                   @Param("passwordHash") String passwordHash,
                   @Param("name") String name);
    UserDto findByEmail(@Param("email") String email);
    UserDto findById(@Param("id") Long id);
}
