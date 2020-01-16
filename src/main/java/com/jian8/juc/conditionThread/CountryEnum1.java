package com.jian8.juc.conditionThread;

import lombok.Getter;

/**
 * @author yanglin
 * @create 2020-01-15 15:54
 */
public enum CountryEnum1 {
  ONE(1, "齐国"), TWO(2, "楚国"), THREE(3, "燕国"),
  FOUR(4, "赵国"), FIVE(5, "魏国"), SIX(6, "韩国");
  @Getter
  private int index;
  @Getter private String value;

  CountryEnum1(int index, String value) {
    this.index = index;
    this.value = value;
  }

  public static  CountryEnum1 getCountry(int index){
    CountryEnum1[] values = CountryEnum1.values();
    for (CountryEnum1 value : values) {
      if(index == value.getIndex()){
        return value;
      }
    }

    return null;
  }
}
