package com.sununiq.scaffold.domain;

import java.util.Date;

public class Titles {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column titles.emp_no
     *
     * @mbg.generated
     */
    private Integer empNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column titles.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column titles.from_date
     *
     * @mbg.generated
     */
    private Date fromDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column titles.to_date
     *
     * @mbg.generated
     */
    private Date toDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column titles.emp_no
     *
     * @return the value of titles.emp_no
     *
     * @mbg.generated
     */
    public Integer getEmpNo() {
        return empNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column titles.emp_no
     *
     * @param empNo the value for titles.emp_no
     *
     * @mbg.generated
     */
    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column titles.title
     *
     * @return the value of titles.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column titles.title
     *
     * @param title the value for titles.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column titles.from_date
     *
     * @return the value of titles.from_date
     *
     * @mbg.generated
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column titles.from_date
     *
     * @param fromDate the value for titles.from_date
     *
     * @mbg.generated
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column titles.to_date
     *
     * @return the value of titles.to_date
     *
     * @mbg.generated
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column titles.to_date
     *
     * @param toDate the value for titles.to_date
     *
     * @mbg.generated
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}