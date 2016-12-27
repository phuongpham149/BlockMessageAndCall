package com.example.phuong.blockmessageandcall.models;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 27/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class PhoneBlock extends SugarRecord {
    String phone;
    public PhoneBlock(){

    }

    @Override
    public String toString() {
        return "PhoneBlock{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
