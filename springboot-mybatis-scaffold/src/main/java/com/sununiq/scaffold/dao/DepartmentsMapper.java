package com.sununiq.scaffold.dao;

import com.sununiq.scaffold.domain.Departments;
import java.util.List;

public interface DepartmentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departments
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String deptNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departments
     *
     * @mbg.generated
     */
    int insert(Departments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departments
     *
     * @mbg.generated
     */
    Departments selectByPrimaryKey(String deptNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departments
     *
     * @mbg.generated
     */
    List<Departments> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departments
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Departments record);
}