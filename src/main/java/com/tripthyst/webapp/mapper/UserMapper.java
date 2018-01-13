package com.tripthyst.webapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.tripthyst.webapp.model.UserModel;

@Mapper
public interface UserMapper {
	
	@Select("select * from user where username = #{username}")
	@Results(value = {
		@Result(property = "id", column = "id"),
		@Result(property = "username", column = "username"),
		@Result(property = "password", column = "password"),
		@Result(property = "role", column = "username", one = @One(select = "selectUserRole"))
	})
	UserModel selectUser(@Param("username") String username);
	
	@Select("select role from user_role where username = #{username}")
	String selectUserRole(@Param("username") String username);
	
}
