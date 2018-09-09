package com.sununiq.scaffold.dao;

import com.sununiq.scaffold.domain.DeptEmp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptEmpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_emp
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("empNo") Integer empNo, @Param("deptNo") String deptNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_emp
     *
     * @mbg.generated
     */
    int insert(DeptEmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_emp
     *
     * @mbg.generated
     */
    DeptEmp selectByPrimaryKey(@Param("empNo") Integer empNo, @Param("deptNo") String deptNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_emp
     *
     * @mbg.generated
     */
    List<DeptEmp> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_emp
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DeptEmp record);
}