package com.Group3.common.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 2543641512850125440L;

	/**
     * Requesting ip address
     */
    private String ip;

    /**
     * Request path
     */
    private String path;

}
