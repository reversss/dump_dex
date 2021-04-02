package com.xposed;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfigEntity {
    private String packageName;
}
