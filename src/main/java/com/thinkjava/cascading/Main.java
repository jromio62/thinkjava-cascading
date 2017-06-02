package com.thinkjava.cascading;

import cascading.flow.Flow;
import cascading.flow.FlowConnector;
import cascading.flow.FlowDef;
import cascading.pipe.*;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import com.thinkjava.cascading.constants.Constants;
import com.thinkjava.cascading.utils.FlowConnectorFactory;
import com.thinkjava.cascading.utils.TapFactory;

public class Main {
    public static void main(String[] args) {

        final String inputJsonPath = args[0];
        final String inputCsvPath = args[1];
        final String outputPath = args[2];
        final String engineType = args.length > 3 ? args[3] : "hadoop";

        FlowDef flowDef = FlowDef.flowDef().setName("Cascading");

        //Create Taps
        final Tap inlineJsonTap = TapFactory.createSingleLineTap(inputJsonPath, engineType, new Fields("line"));
        final Tap inlineCsvTap = TapFactory.createCsvTap(inputCsvPath, engineType, Constants.RESULT_FIELDS);

        //initializing Pipes
        final Pipe inPatientPipe = new Pipe("jsonPatientPipe");
        final Pipe inCsvPatientPipe = new Pipe("csvPatientPipe");

        //connect pipes and sources within the flow definition
        flowDef.addSource(inPatientPipe, inlineJsonTap);
        flowDef.addSource(inCsvPatientPipe, inlineCsvTap);

        //expand json - apply ExpandJson function to all records from inPatientPipe
        Pipe labResultsPipe = new Each(inPatientPipe, new ExpandJson(Constants.RESULT_FIELDS));

        //merge both csv and json  Pipes
        labResultsPipe = new Merge(labResultsPipe, inCsvPatientPipe);


        //initializing Tap to write the output data
        final Tap sinkTap = TapFactory.createCsvTap(outputPath, engineType, Constants.RESULT_FIELDS);
        flowDef.addTailSink(labResultsPipe, sinkTap);

        //configure flow connector and  start the flow.
        FlowConnector flowConnector = FlowConnectorFactory.createFlowConnector(engineType, Main.class);
        Flow flow = flowConnector.connect(flowDef);
        flow.complete();
    }
}
