package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.BankInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface BankInfoMapper {
    @Delete({
        "delete from SAM_BANK_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_BANK_INFO (id, account_number, ",
        "name, sub_branch, ",
        "accountName, accountType, ",
        "staff_id)",
        "values (#{id,jdbcType=VARCHAR}, #{accountNumber,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{subBranch,jdbcType=VARCHAR}, ",
        "#{accountname,jdbcType=VARCHAR}, #{accounttype,jdbcType=VARCHAR}, ",
        "#{staffId,jdbcType=VARCHAR})"
    })
    int insert(BankInfo record);

    @InsertProvider(type=BankInfoSqlProvider.class, method="insertSelective")
    int insertSelective(BankInfo record);

    @Select({
        "select",
        "id, account_number, name, sub_branch, accountName, accountType, staff_id",
        "from SAM_BANK_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="account_number", property="accountNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sub_branch", property="subBranch", jdbcType=JdbcType.VARCHAR),
        @Result(column="accountName", property="accountname", jdbcType=JdbcType.VARCHAR),
        @Result(column="accountType", property="accounttype", jdbcType=JdbcType.VARCHAR),
        @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR)
    })
    BankInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=BankInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BankInfo record);

    @Update({
        "update SAM_BANK_INFO",
        "set account_number = #{accountNumber,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "sub_branch = #{subBranch,jdbcType=VARCHAR},",
          "accountName = #{accountname,jdbcType=VARCHAR},",
          "accountType = #{accounttype,jdbcType=VARCHAR},",
          "staff_id = #{staffId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(BankInfo record);

    @Select({
            "select",
            "id, account_number, name, sub_branch, accountName, accountType, staff_id",
            "from SAM_BANK_INFO",
            "where staff_id = #{staffId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="account_number", property="accountNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="sub_branch", property="subBranch", jdbcType=JdbcType.VARCHAR),
            @Result(column="accountName", property="accountname", jdbcType=JdbcType.VARCHAR),
            @Result(column="accountType", property="accounttype", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR)
    })
    List<BankInfo> findByStaffId(String staffId);

    @Delete({
            "delete from SAM_BANK_INFO",
            "where staff_id = #{staffId,jdbcType=VARCHAR}"
    })
    void deleteByStaffId(String staffId);

    @InsertProvider(type=BankInfoSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<BankInfo> bankInfoList);
}