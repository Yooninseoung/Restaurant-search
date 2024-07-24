package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "User")
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "userID")
    private String userID;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "phone_number", length = 20)
    private String phone_number;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    public User(String userID, String username, String password, String nickname, String phone_number, String address) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.address = address;
    }

    // Getters and Setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_Number) {
        this.phone_number = phone_Number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}