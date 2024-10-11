package org.example.utils;
import org.example.bo.ClientBO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 窗口工具 用于获得token 端口
 */
public class WinUtil {
    public static Pattern appPortPattern = Pattern.compile("--app-port=(\\d+)");
    public static Pattern tokenPattern = Pattern.compile("--remoting-auth-token=([\\w-]+)");
    public static Pattern reginPattern = Pattern.compile("--rso_platform_id=([\\w-]+)");

    /**
     * 通过进程名查询出进程的启动命令,解析出需要的客户端token和端口
     */
    public static ClientBO getClientProcess() throws IOException {
        String cmd = "WMIC PROCESS WHERE \"name='LeagueClientUx.exe'\" GET commandline";
        BufferedReader reader = null;
        Process process = null;
        ClientBO leagueClientBO = new ClientBO();
        try {
            process = Runtime.getRuntime().exec(cmd);
            // windows 命令必须gbk编码
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gb2312"));
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher appPortMatcher = appPortPattern.matcher(line);
                Matcher tokenPatternMatcher = tokenPattern.matcher(line);
                Matcher reginPatternMatcher = reginPattern.matcher(line);
                if (tokenPatternMatcher.find()) {
                    leagueClientBO.setToken(tokenPatternMatcher.group(1));
                }
                if (appPortMatcher.find()) {
                    leagueClientBO.setPort(appPortMatcher.group(1));
                }
                if (reginPatternMatcher.find()) {
                    leagueClientBO.setRegion(reginPatternMatcher.group(1));
                }
            }
            return leagueClientBO;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("lol进程查询失败");
                }
            }
            if (process != null) {
                process.getErrorStream().close();
                process.getOutputStream().close();
            }
        }

    }
}
