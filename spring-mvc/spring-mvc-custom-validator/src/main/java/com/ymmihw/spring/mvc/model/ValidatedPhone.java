package com.ymmihw.spring.mvc.model;

import com.ymmihw.spring.mvc.validator.ContactNumberConstraint;

public class ValidatedPhone {

  @ContactNumberConstraint
  private String phone;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return phone;
  }
}
