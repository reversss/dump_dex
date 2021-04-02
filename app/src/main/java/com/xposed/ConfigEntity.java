package com.xposed;


import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfigEntity {
    private List<String> packageList;
}
