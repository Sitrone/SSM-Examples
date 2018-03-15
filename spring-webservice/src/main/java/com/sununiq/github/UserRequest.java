package com.sununiq.github;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://www.hifreud.com/ws/demo", name = "UserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRequest {

    @XmlElement(namespace = "http://www.hifreud.com/ws/demo")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}