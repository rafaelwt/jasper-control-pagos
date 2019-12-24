package com.reportes.jasper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.reportes.jasper.model.*;

@Mapper
public interface UsersMapper {

	 @Select("select * from users")
	 List<Users> findAll();

}
