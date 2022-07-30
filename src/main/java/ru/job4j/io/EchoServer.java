package ru.job4j.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoServer {

    private static final String MSG = "msg";
    private static final String HELLO = "HELLO";
    private static final String EXIT = "EXIT";
    public static final String PROTOCOL_NAME = "HTTP";

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                Map<String, String> parameters = null;
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);

                        if (parameters == null) {
                            parameters = getParameters(str);
                        }
                    }
                    if (parameters != null) {
                        String result;
                        String msg = parameters.get(MSG).toUpperCase();
                        switch (msg) {
                            case HELLO:
                                result = "Hello, dear friend.\n";
                                break;
                            case EXIT:
                                server.close();
                            default:
                                result = "What are say?\n";
                        }
                        out.write(result.getBytes());
                    }
                    out.flush();
                }
            }
        }
    }

    private static Map<String, String> getParameters(String str) {
        Map<String, String> args = null;
        String httpStr = PROTOCOL_NAME;
        if (str.contains(httpStr)) {
            int i = str.indexOf("?");
            if (i >= 0) {
                String substr = str.substring(i + 1);
                substr = substr.substring(0, substr.indexOf(httpStr) - 1);

                args = new HashMap<>();
                for (String keyValue : substr.split("&")) {
                    String[] array = keyValue.split("=");
                    args.put(array[0], array[1]);
                }
            }
        }
        return args;
    }
}
