package com.aits.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jared
 */

public class XStreamDemo implements Serializable{
    private String id;
    private String name;

    @XStreamImplicit
    private List<Header> headers=new ArrayList<Header>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Header Header) {
      this.headers.add(Header);
    }
}
