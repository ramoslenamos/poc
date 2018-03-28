
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserInfos implements Serializable {
  @Id
  @GeneratedValue
  public Long id;
  @SerializedName("SubscriberLogin")
  @Expose
  public String subscriberLogin;
  @SerializedName("SubscriberPassword")
  @Expose
  public String subscriberPassword;
  @SerializedName("BaseName")
  @Expose
  public String baseName;
  @SerializedName("UserLogin")
  @Expose
  public String userLogin;
  @SerializedName("UserPassword")
  @Expose
  public String userPassword;
  @SerializedName("UserLang")
  @Expose
  public String userLang;
  @SerializedName("ProductName")
  @Expose
  public String productName;

  /**
   * No args constructor for use in serialization
   */
  public UserInfos() {
  }

  /**
   * @param subscriberLogin
   * @param subscriberPassword
   * @param userLang
   * @param userPassword
   * @param baseName
   * @param userLogin
   * @param productName
   */
  public UserInfos(String subscriberLogin, String subscriberPassword, String baseName, String userLogin, String userPassword, String userLang, String productName) {
    super();
    this.subscriberLogin = subscriberLogin;
    this.subscriberPassword = subscriberPassword;
    this.baseName = baseName;
    this.userLogin = userLogin;
    this.userPassword = userPassword;
    this.userLang = userLang;
    this.productName = productName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubscriberLogin() {
    return subscriberLogin;
  }

  public void setSubscriberLogin(String subscriberLogin) {
    this.subscriberLogin = subscriberLogin;
  }

  public String getSubscriberPassword() {
    return subscriberPassword;
  }

  public void setSubscriberPassword(String subscriberPassword) {
    this.subscriberPassword = subscriberPassword;
  }

  public String getBaseName() {
    return baseName;
  }

  public void setBaseName(String baseName) {
    this.baseName = baseName;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserLang() {
    return userLang;
  }

  public void setUserLang(String userLang) {
    this.userLang = userLang;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }
}