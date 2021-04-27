package com.fwm.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ClientPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ip = "";
    private String msg = "";
}
