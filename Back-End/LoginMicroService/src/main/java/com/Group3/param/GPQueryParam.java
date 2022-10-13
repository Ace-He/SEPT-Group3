package com.Group3.param;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GPQueryParam {
    Long gid;
    private String userName;
    private String email;

    public GPQueryParam(Long gid) {
        this.gid = gid;
    }
}
