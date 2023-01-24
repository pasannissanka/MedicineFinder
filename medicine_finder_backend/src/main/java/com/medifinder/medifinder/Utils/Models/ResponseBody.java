package com.medifinder.medifinder.Utils.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseBody<T> {
    private String message;
    @Nullable
    private T data;
    @Nullable
    private String error;
}
