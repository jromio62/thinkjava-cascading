package com.thinkjava.cascading.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by jromio on 6/1/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

    private String patientId;
    private String name;
    private List<LabResult> labs;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<LabResult> getLabs() {
        return labs;
    }

    public void setLabs(List<LabResult> labs) {
        this.labs = labs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
