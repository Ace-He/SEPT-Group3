package com.Group3.param;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GpQueryParam {


    Long gid;
    private String userName;
    private String email;

    public GpQueryParam(Long gid) {
        this.gid = gid;
    }
}

