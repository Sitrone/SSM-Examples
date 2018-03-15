package com.sununiq.github;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(namespace = "http://www.hifreud.com/ws/demo", name = "UserResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserResponse {
    @XmlElement(namespace = "http://www.hifreud.com/ws/demo")
    private String username;

    @XmlElement(namespace = "http://www.hifreud.com/ws/demo")
    private String gender;

    @XmlElement(namespace = "http://www.hifreud.com/ws/demo")
    private Date birthday;

    @XmlElement(namespace = "http://www.hifreud.com/ws/demo")
    private String location;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}