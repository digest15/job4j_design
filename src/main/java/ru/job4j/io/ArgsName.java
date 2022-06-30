package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("Parameter %s not exist", key));
        }
        return value;
    }

    private void parse(String[] args) {
        for (String param : args) {
            valiate(param);

            int pos = param.indexOf("=");
            String key = param.substring(1, pos);
            String value = param.substring(pos + 1, param.length());
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("You must put some parameters. Usage java -jar NAME.jar -KEY=VALUE");
        }

        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private void valiate(String param) {
        if (!param.startsWith("-")) {
            throw new IllegalArgumentException(String.format("Parameter must start with \"-\" parameter: %s", param));
        }

        int pos = param.indexOf("=");
        if (pos == -1 || pos == 1 || pos == param.length() - 1) {
            throw new IllegalArgumentException(String.format("Bad parameter: %s", param));
        }
    }
}
