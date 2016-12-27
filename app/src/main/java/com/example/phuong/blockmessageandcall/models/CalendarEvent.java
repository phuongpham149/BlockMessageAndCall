package com.example.phuong.blockmessageandcall.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 27/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class CalendarEvent {
    private String name;
    private String start;
    private String end;
    private String description;

}
