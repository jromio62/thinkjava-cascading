package com.thinkjava.cascading;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import com.thinkjava.cascading.pojo.LabResult;
import com.thinkjava.cascading.pojo.Patient;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ExpandJson extends BaseOperation implements Function {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpandJson.class);
    public ExpandJson(Fields fields) {
        super(fields);
    }

    @Override
    public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
        ObjectMapper mapper = new ObjectMapper();
        TupleEntry tupleEntry = functionCall.getArguments();
        String jsonString =  tupleEntry.getString(0);
        Patient record = null;
        try {
            record = mapper.readValue(jsonString, Patient.class);
        } catch (IOException e) {
            LOGGER.warn("Cannot read json record: " + jsonString, e);
        }

        if (record != null && CollectionUtils.isNotEmpty(record.getLabs())) {
            for(LabResult result : record.getLabs()) {
                Tuple tuple = Tuple.size(5);
                tuple.set(0, record.getPatientId());
                tuple.set(1, record.getName());
                tuple.set(2, result.getType());
                tuple.set(3, result.getResult());
                tuple.set(4, result.getPerformed());
                functionCall.getOutputCollector().add(tuple);
            }
        }
    }
}
