package com.thinkjava.cascading;

import org.junit.Test;

/**
 * Created by jromio on 6/2/17.
 */
public class MainLocalTest {

    @Test
    public void testFlowExecution() {

        String inputJsonFilePath = getClass().getClassLoader()
                .getResource("hospitalB.json").getPath();

        String inputCsvFilePath = getClass().getClassLoader()
                .getResource("hospitalA.csv").getPath();


        Main.main(new String[] {inputJsonFilePath,
                inputCsvFilePath,
                "build/output.csv", "local"});

    }

}
