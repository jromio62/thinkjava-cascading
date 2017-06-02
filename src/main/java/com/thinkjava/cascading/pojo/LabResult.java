package com.thinkjava.cascading.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by jromio on 6/1/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabResult {

    private String type;
    private String result;
    private String performed;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPerformed() {
        return performed;
    }

    public void setPerformed(String performed) {
        this.performed = performed;
    }
}
