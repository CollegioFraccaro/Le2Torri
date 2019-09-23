package com.le2t.prod.authentication.model;

import java.util.Arrays;

public class UsernameForm {

  private String[] multiUsernameSelected;
  private Integer id = null;

  public UsernameForm(String[] multiUsernameSelected) {
    this.multiUsernameSelected = multiUsernameSelected;
  }

  public UsernameForm() {
  }

  public String[] getMultiUsernameSelected() {
    return multiUsernameSelected;
  }

  public void setMultiUsernameSelected(String[] multiUsernameSelected) {
    this.multiUsernameSelected = multiUsernameSelected;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsernameForm that = (UsernameForm) o;
    return Arrays.equals(getMultiUsernameSelected(), that.getMultiUsernameSelected());
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(getMultiUsernameSelected());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UsernameForm{");
    sb.append("multiUsernameSelected=").append(Arrays.toString(multiUsernameSelected));
    sb.append('}');
    return sb.toString();
  }
}
