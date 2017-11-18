package com.aits.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jared
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {
   // @XStreamAlias("id")
    private String id;
    private String hName;
    private List<Good> Good;
}
