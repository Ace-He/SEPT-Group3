package com.Group3.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Label implements Serializable {

    private String label;
    private Long value;

}
