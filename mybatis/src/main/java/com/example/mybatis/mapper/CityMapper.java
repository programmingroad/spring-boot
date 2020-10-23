package com.example.mybatis.mapper;

import com.example.mybatis.entity.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/18 10:59
 * @description:
 **/

@Mapper
public interface CityMapper {

    @Select("SELECT * FROM city")
    List<City> findAll();

    @Insert({
            "<script>",
            "insert into city",
            "<trim prefix='(' suffix=')' suffixOverrides=',' >",
            "<if test='id != null' >",
            "ID,",
            "</if>",
            "<if test='name != null' >",
            "NAME,",
            "</if>",
            "<if test='status != null' >",
            "STATUS,",
            "</if>",
            "</trim>",
            "<trim prefix='values (' suffix=')' suffixOverrides=',' >",
            "<if test='id != null' >",
            "#{id,jdbcType=INTEGER},",
            "</if>",
            "<if test='name != null' >",
            "#{name,jdbcType=VARCHAR},",
            "</if>",
            "<if test='status != null' >",
            "#{status,jdbcType=TINYINT},",
            "</if>",
            "</trim>",
            "</script>"
    })
    int insert(City city);

}
