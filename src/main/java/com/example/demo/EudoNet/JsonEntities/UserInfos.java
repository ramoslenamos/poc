
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfos {

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

}