package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    public static final String USER_NAME = "Пользователь";
    public static final String BOT_NAME = "Чат-бот";
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final String logPath;
    private final String botAnswersPath;
    private Random random;
    private boolean isOut = false;
    private boolean isStop = false;
    private List<String> botAnswers;

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat/log.txt", "./data/chat/phrases.txt");
        cc.run();
    }

    public ConsoleChat(String logPath, String botAnswersPath) {
        validate(logPath, botAnswersPath);
        this.logPath = logPath;
        this.botAnswersPath = botAnswersPath;
    }

    public void run() {
        printInstruction();
        List<String> log = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isOut) {
                String inStr = readUserInput(scanner);
                log.add(String.format("%s: %s", USER_NAME, inStr));
                switch (inStr.toLowerCase()) {
                    case (OUT):
                        isOut = true;
                        break;
                    case (STOP):
                        isStop = true;
                        break;
                    case (CONTINUE):
                        isStop = false;
                        break;
                    default:
                }
                if (!isOut && !isStop) {
                    String answer = String.format("%s: %s", BOT_NAME, getRandomAnswer());
                    System.out.println(answer);
                    log.add(answer);
                }
            }
        }
        saveLog(log);
    }

    private void printInstruction() {
        System.out.println("============================================");
        System.out.println(String.format("Введите \"%s\" для выключения бота", STOP));
        System.out.println(String.format("Введите \"%s\" для включения бота", STOP));
        System.out.println(String.format("Введите \"%s\" для завершения чата", OUT));
        System.out.println("============================================");
    }

    private String readUserInput(Scanner scanner) {
        System.out.print(String.format("%s: ", USER_NAME));
        return scanner.nextLine();
    }

    private String getRandomAnswer() {
        if (botAnswers == null) {
            botAnswers = readPhrases();
        }
        if (random == null) {
            random = new Random();
        }
        return botAnswers.get(random.nextInt(botAnswers.size()));
    }

    private List<String> readPhrases() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswersPath, DEFAULT_CHARSET))) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(logPath, DEFAULT_CHARSET, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String logPath, String botAnswersPath) throws IllegalArgumentException {
        if (logPath.isEmpty()) {
            throw new IllegalArgumentException("logPath is empty string");
        }
        if (botAnswersPath.isEmpty()) {
            throw new IllegalArgumentException("botAnswersPath is empty string");
        }
    }
}
