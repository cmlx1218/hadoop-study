package com.cmlx.mapreduce.define;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-4 0004 16:26
 */
public class GroupFeedEntity {

    private Integer id;

    private String name;

    private String address;

    private Long uuid;

    private Float age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }
}
