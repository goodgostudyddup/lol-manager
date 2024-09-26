package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientIsRun {
    public static boolean isRun(String processName){
        String line;
        String pidInfo = "";

        try {
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = input.readLine()) != null) {
                pidInfo += line;
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pidInfo.contains(processName);
    }
}
