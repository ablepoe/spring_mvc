package com.entity;

public class Category {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.id
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.catname
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    private String catname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.catdescription
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    private String catdescription;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.id
     *
     * @return the value of category.id
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.id
     *
     * @param id the value for category.id
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.catname
     *
     * @return the value of category.catname
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    public String getCatname() {
        return catname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.catname
     *
     * @param catname the value for category.catname
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    public void setCatname(String catname) {
        this.catname = catname == null ? null : catname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.catdescription
     *
     * @return the value of category.catdescription
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    public String getCatdescription() {
        return catdescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.catdescription
     *
     * @param catdescription the value for category.catdescription
     *
     * @mbggenerated Fri Jul 17 13:11:26 CST 2015
     */
    public void setCatdescription(String catdescription) {
        this.catdescription = catdescription == null ? null : catdescription.trim();
    }
}