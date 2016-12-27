package com.example.phuong.blockmessageandcall.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 21/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class Contact {
    private String name;
    private String phone;
}
