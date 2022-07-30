package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte b = 1;
        short s = 2;
        char c = '3';
        int i = 4;
        long l = 5;
        float f = 6.6f;
        double d = 7.7d;
        boolean o = true;
        LOG.debug("Primitive variable value: byte {}, short {}, char {}, int {}, long {}, float {}, double {}, boolean {}",
                b, s, c, i, l, f, d, o);
    }
}
