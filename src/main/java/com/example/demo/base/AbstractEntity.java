package com.example.demo.base;

import java.io.Serializable;

public abstract class AbstractEntity<K extends Serializable> implements Serializable {
  public abstract K getId();
  public abstract String toString();
}
