package com.sununiq.scaffold.dao;

import com.sununiq.scaffold.domain.Salaries;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalariesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salaries
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("empNo") Integer empNo, @Param("fromDate") Date fromDate);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salaries
     *
     * @mbg.generated
     */
    int insert(Salaries record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salaries
     *
     * @mbg.generated
     */
    Salaries selectByPrimaryKey(@Param("empNo") Integer empNo, @Param("fromDate") Date fromDate);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salaries
     *
     * @mbg.generated
     */
    List<Salaries> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table salaries
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Salaries record);
}