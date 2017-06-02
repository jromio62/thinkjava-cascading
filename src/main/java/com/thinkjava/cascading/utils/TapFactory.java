package com.thinkjava.cascading.utils;

import cascading.pipe.*;
import cascading.scheme.Scheme;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tap.local.FileTap;
import cascading.tuple.Fields;

/**
 * Created by jromio on 5/29/17.
 */
public class TapFactory {

    public static Tap createSingleLineTap(String path, String engineType, Fields fields) {
        Scheme tapSchema = getLineSchema(fields, engineType);
        return getTap(tapSchema, path, engineType);
    }

    private static Scheme getLineSchema(Fields schemaFields, String engineType) {
        Scheme result;
        switch (engineType) {
            case "local":
                result = new cascading.scheme.local.TextLine(schemaFields);
                break;
            default:
                result = new cascading.scheme.hadoop.TextLine(schemaFields);
                break;
        }
        return result;
    }

    private static Tap getTap(Scheme tapSchema, String path, String engineType) {
        Tap result;
        switch (engineType) {
            case "local":
                result = new FileTap(tapSchema, path);
                break;
            default:
                result = new Hfs(tapSchema, path);
                break;
        }
        return result;
    }



    public static Tap createCsvTap(String path, String engineType, Fields fields) {
        Scheme tapSchema = getCsvSchema(fields, engineType);
        return getTap(tapSchema, path, engineType);
    }




    private static Scheme getCsvSchema(Fields schemaFields, String engineType) {
        Scheme result;
        switch (engineType) {
            case "local":
                result = new cascading.scheme.local.TextDelimited(schemaFields, true, false, ",", "\"", null);
                break;
            default:
                result = new cascading.scheme.hadoop.TextDelimited(schemaFields, true, false, ",", "\"", null);
                break;
        }
        return result;
    }

}