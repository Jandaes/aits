package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jared
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private String id;
    private String gName;
    private String price;

}
