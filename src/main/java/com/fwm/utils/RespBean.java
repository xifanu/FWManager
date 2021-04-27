package com.fwm.utils;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RespBean implements Serializable {
    private String success;
    private String message;
}
