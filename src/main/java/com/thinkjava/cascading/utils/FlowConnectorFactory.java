package com.thinkjava.cascading.utils;

import cascading.flow.FlowConnector;
import cascading.flow.FlowRuntimeProps;
import cascading.flow.hadoop2.Hadoop2MR1FlowConnector;
import cascading.flow.local.LocalFlowConnector;
import cascading.flow.tez.Hadoop2TezFlowConnector;
import cascading.property.AppProps;
import com.thinkjava.cascading.constants.Constants;

import java.util.Properties;

/**
 * Created by jromio on 5/29/17.
 */

public class FlowConnectorFactory {

     public static FlowConnector createFlowConnector(String engineType, Class clazz) {
        FlowConnector connector;
        Properties properties = AppProps.appProps()
                .setJarClass(clazz)
                .buildProperties();
        properties = FlowRuntimeProps.flowRuntimeProps()
                .setGatherPartitions(4)
                .buildProperties(properties);
        switch (engineType) {
            case "local":
                connector = new LocalFlowConnector();
                break;
            case "hadoop":
                connector = new Hadoop2MR1FlowConnector(properties);
            default:
                connector = new Hadoop2TezFlowConnector(properties);
                break;
        }
        return connector;
    }
}
