package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ImportResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ImportResultMapper {
    @Delete({
        "delete from SYM_IMPORT_RESULT",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_IMPORT_RESULT (id, err_result, ",
        "succ_result, file_id)",
        "values (#{id,jdbcType=VARCHAR}, #{errResult,jdbcType=VARCHAR}, ",
        "#{succResult,jdbcType=VARCHAR}, #{fileId,jdbcType=VARCHAR})"
    })
    int insert(ImportResult record);

    @InsertProvider(type=ImportResultSqlProvider.class, method="insertSelective")
    int insertSelective(ImportResult record);

    @Select({
        "select",
        "id, err_result, succ_result, file_id",
        "from SYM_IMPORT_RESULT",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="err_result", property="errResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="succ_result", property="succResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_id", property="fileId", jdbcType=JdbcType.VARCHAR)
    })
    ImportResult selectByPrimaryKey(String id);

    @UpdateProvider(type=ImportResultSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImportResult record);

    @Update({
        "update SYM_IMPORT_RESULT",
        "set err_result = #{errResult,jdbcType=VARCHAR},",
          "succ_result = #{succResult,jdbcType=VARCHAR},",
          "file_id = #{fileId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ImportResult record);
    @Select("select * from SYM_IMPORT_RESULT where file_id = #{fileId,jdbcType=VARCHAR}")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="err_result", property="errResult", jdbcType=JdbcType.VARCHAR),
            @Result(column="succ_result", property="succResult", jdbcType=JdbcType.VARCHAR),
            @Result(column="file_id", property="fileId", jdbcType=JdbcType.VARCHAR)
    })
    ImportResult findByfileId(String fileId);
}