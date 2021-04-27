package com.fwm.utils;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public  class FWMShell {

    public static String  run(String shell) throws ExecuteException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        CommandLine cmdLine = CommandLine.parse(shell);
        DefaultExecutor executor = new DefaultExecutor();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,errorStream);
        executor.setExitValues(null);
        executor.setStreamHandler(streamHandler);
        int exitValue = executor.execute(cmdLine);
        String out = outputStream.toString("gbk");
        String error = errorStream.toString("gbk");
        outputStream.close();
        errorStream.close();
        return out+error;
    }
}
