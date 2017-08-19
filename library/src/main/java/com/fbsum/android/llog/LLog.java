package com.fbsum.android.llog;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xin on 8/19/17.
 */

public final class LLog {

    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;
    private static final int JSON = 0x7;

    private static final int STACK_TRACE_INDEX = 4;
    private static final String JAVA_SUFFIX = ".java";
    private static final String DEFAULT_TAG = "LLog";
    private static final int JSON_INDENT_SPACES = 4;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static String tag = DEFAULT_TAG;
    private static boolean enable = false;


    public static void init(boolean logEnable) {
        init(logEnable, tag);
    }

    public static void init(boolean logEnable, String globalTag) {
        enable = logEnable;
        tag = globalTag;
    }

    public static void e(String log) {
        if (!enable) {
            return;
        }
        printLog(E, tag, log);
    }

    public static void e(String tag, String log) {
        if (!enable) {
            return;
        }
        printLog(E, tag, log);
    }

    public static void d(String log) {
        if (!enable) {
            return;
        }
        printLog(D, tag, log);
    }

    public static void d(String tag, String log) {
        if (!enable) {
            return;
        }
        printLog(D, tag, log);
    }

    public static void json(String json) {
        if (!enable) {
            return;
        }
        printLog(JSON, tag, json);
    }

    public static void json(String tag, String json) {
        if (!enable) {
            return;
        }
        printLog(JSON, tag, json);
    }

    private static void printLog(int type, String tag, String log) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + JAVA_SUFFIX;
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0] + JAVA_SUFFIX;
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }

        if (TextUtils.equals(tag, DEFAULT_TAG)) {
            tag = className;
        }

        String location = "(" + className + ":" + lineNumber + ")#" + methodName + "(): ";

        switch (type) {
            case D: {
                Log.d(tag, location + log);
            }
            break;
            case E: {
                Log.e(tag, location + log);
            }
            break;
            case JSON: {
                printJson(tag, location, log);
            }
        }
    }

    private static void printJson(String tag, String location, String log) {

        String json;

        try {
            if (log.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(log);
                json = jsonObject.toString(JSON_INDENT_SPACES);
            } else if (log.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(log);
                json = jsonArray.toString(JSON_INDENT_SPACES);
            } else {
                json = log;
            }
        } catch (JSONException e) {
            json = log;
        }

        Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        json = location + LINE_SEPARATOR + json;
        String[] lines = json.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.e(tag, "║ " + line);
        }
        Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

}
