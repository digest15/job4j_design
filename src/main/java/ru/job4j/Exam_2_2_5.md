# 1. Что такое поток ввода-вывода?

В Java это обстракция, котороя используется для чтения и записи информации и унифицирует поведение потоков ввода-вывода несмотря на различия устройств с которых эта информация может потребляться или поставляться.
Чтение информации - это Поток-ввода.
Запись информации - это Поток-вывода.

В Java потоки представлены интерфейсами абстрактых классов InputStream и OutputStream, которые представляют потоки байтов.
Абстрактные классы Writer и Reader представляют потоки символов.


# 2. Что такое Java IO?

Java IO - это API (библиотека), расположенная в пакете "java.io". Доступна с версии Java 1.1.
Основана на концепции Каналов ввода-вывода, описанных в пункте 1.
В отличие от NIO имеют методы построчного считывания данных при работе с символами.

Отличительные особенности:
- Потокоориентированный - поочередное чтение-запись байтов информации, нет возможности перемещаться по потоку, даже в буфферизированном потоке (т.к. интерфейс не позволяет)
- Блокирующий (синхронный) ввод-вывод - вызывающий поток блокируется до тех пор, пока данные не будут считаны или записаны


# 3. Что такое Java NIO?

Это API (библиотека), расположенная в пакете "java.nio". Доступна с версии Java 1.4, в версии 1.7 была существенно расширена.
Основана на концепции Каналов и Буферов.
Канал - это абстракция объектов иточников-приемников данных.
Буфер - это контейнер данных в\из которого записываются.

Отличительные особенности:
- Не блокирующий - при считывании или записи данных в канал вызывающий поток не блокируется, если канал не имеет данный на момент запроса, он просто ничего не вернет и вызывающий поток не будет заблокирован.
- Селекторы - слушатели каналов на предмет появления данных, позволяет переключаться между каналами из одного потока.
- Наборы символов и селекторы - кодеры и декодеры для отображения байт и символов Unicode.


# 4. Что такое Scanner?

Это класс пакета java.util предназначенный для сканирования источников данных в целях нахождения токенов.
Поиск токенов происходит по шаблону (регулярному выражению), шаблон поиска - это разделитель токенов. По умолчанию разделитель это пробельные символы.


# 5. Как работает Scanner внутри?

Работает как интератор, он реализует этот интерфейс.
В качестве источника данных Scanner принимает любой вид данных, включая Reader, InputStream, File для java.io и Readable, Path для java.util.nio. Также можно задать источник в виде строки String.


# 6. Какие базовые методы существуют в Scanner?

В основном все методы можно разделить на hasTYPE и nextTYPE, где TYPE - это тип по шаблону которого будет происходить отделение токенов друг от друга. Например, hasInt(), nextInt().


# 7. Что такое байтовый поток? Как он реализован внутри?

Байтовый поток в Java - это объекты классов InputStream/OutputStream.
В потоке можно прочитать или записать один или несколько байтов.
Методы read()/write(int b), для чтения/записи одного байта. Реализация этого метода в различных потомках и определяет фактическую реализацию потока. Также есть метод read(byte[] buf) / write(byte[] buf) для записи сразу массива байтов.


# 8. Что такое символьный поток? Как он реализован внутри?

Символьный поток в Java - это объект Reader/Writer. В потоке можно прочитать или записать один или несколько символов (char, 2 байта), т.е. внутри байты преобразуеются в символы.
Реализации методов read(char[] cbuf, int off, int len) и write(char cbuf[], int off, int len) определяет фактическую реализацию работы конкретного объекта символьного потока.
Также дополнительно есть метод read() / write(int c) для чтения/записи одного символа или write(String) для записи строки (набора символов).


# 9. Что такое буферизированный поток?

Классы BufferedInputStream/BufferedOutputStream - это реализации потоков ввода/вывода использующая буффер (объект в памяти) для искорения работы потока, за счет сокращения операций считывания\записи с реальных устройств.


# 10. Что такое форматированный вывод? Какие механизмы позволяют осуществить форматированный вывод?

Базовой частью поддержки создания форматированного вывода в языке Java служит класс Formatter, включенный в пакет java.util. Он обеспечивает преобразования формата (format conversions) позволяющие выводить числа, строки и время и даты практически в любом понравившемся вам формате.
В классе Formatter объявлен метод format(), который преобразует переданные в него параметры в строку заданного формата и сохраняет в объекте типа Formatter.
Аналогичный метод format() объявлен у классов PrintStream и PrintWriter. System.out это статическая переменная типа PrintStream.
В Java 5 для классов PrintStream и PrintWriter добавлен метод printf(). Методы printf() и format() автоматически используют класс Formatter.


# 11. Как осуществляется ввод и вывод из командной строки?

Java содержит три системыные статичесике переменные:
- System.in - стандартный поток ввода. По умолчанию это клавиатура.
- System.out - стандартный поток вывода. По умолчанию это консоль.
- System.err - стандартный поток вывода ошибок. По умолчанию это консоль.

1. Использование ридера для считывания символов:
   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
   String name = reader.readLine();

2. Использование сканера для более удобного чтнеия данных и автоматического преобразования типов
   Scanner scanner = new Scanner(System.in);
   String name = scanner.nextLine();

3. Использование сканера для более удобной работы именно с консолью
   Console console = System.console();
   if(console!=null){
   String login = console.readLine("Введите строку:");
   }


# 12. Что такое класс Console? Расскажите его АПИ.

Специальный класс для удобной, посимвольной работы с консолью.
Существует в единственном экземпляре. Его может не быть, если текущая реализация виртуальной машины поддерживает консоль или процесс виртуальной машины был запущен в фоне.

В общем случае работа с классом Console выглядит следующим образом:

```java
Console console = System.console();
if(console!=null){
// считываем данные с консоли
String login = console.readLine("Введите логин:");
char[] password = console.readPassword("Введите пароль:");

	//Выводим данные в консоль
        console.printf("Введенный логин: %s \n", login);
        console.printf("Введенный пароль: %s \n", new String(password));
}
```

- System.console() = Для начала работы с объектом класса консоль его нужно получить, если вернулься null значит нет поддержки консоли или процесс виртуальной машины запущен в фоне.
- console.readLine("Введите логин:") - Выводит на консоль форматированную строку, считывает строку введенную в консоль после нажатия "Enter"
- console.readPassword("Введите пароль:") - Выводит на консоль форматированную строку, считывает строку введенную в консоль после нажатия "Enter". Вводимые символы не отображаются в консоле.
- console.printf("Введенный логин: %s \n", login) - Выводит на консоль форматированную строку


# 13. Что такое поток данных? Data stream.

Классы DataOutputStream и DataInputStream представляет поток вывода/ввода и предназначены для записи/чтения данных примитивных типов, таких, как int, double и т.д. Для записи каждого из примитивных типов предназначен свой метод.


# 14. Что такое поток объектов, Object stream.

Классы ObjectInputStream и ObjectOutputStream - представляют собой потоки ввода/вывода для чтения/записи объектов в бинарном виде.


# 15. Что такое Path? Как он реализуется на разных ОС?

Path - это интерфейс, реализации которого являются, улучшенным аналогом класса File, представляет путь до файла в файловой системе.

Решает следующий проблемы класса File:
- У File многие методы возращают boolean как результат работы, в итоге не ясна причина отказа операции. Экземпляры Path в случае отказа генерируют исключение.
- raname метод мог работать не корректно на разных плтформах
- Нет поддержки символьных ссылок
- Улучшена поддержка метаданных
- Многие методы классы File плохо работают для больший файлов и большого количества файлов в каталоге, могут вызвать переполнение памяти.
- Поддержка работы с циклическими ссылками

Каждый объект класса файловой системы, который поставляется со сборкой ВМ, содержит ссылку "свою" реализацию интерфейса Path, который также поставляется со сборкой ВМ и учитывает особенности ОС под которую расчитан.


# 16. Как получить список файлов?

```java
// java.io API
File dir = new File(path);
File[] arrFiles = dir.listFiles();
```

```java
// java.nio API
Stream<Path> stream = Files.list(dir);
```

```java
// java.nio API
Stream<Path> stream = Files.walk(dir);
```

```java
// java.nio API
DirectoryStream stream = Files.newDirectoryStream(Paths.get(dir));
```

```java
// java.nio API
Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
    ...
    ...
    ...            
});
```

# 17. Как проверить что файловая сущность является файлом или папкой?

```java
// java.io API
boolean fileIsDir = file.isDirectory();
```

```java
// java.nio API
boolean pathIsDir = Files.isDirectory(path);
```

# 18. Как удалить файл?

```java
// java.io API
boolean result = file.delete();
```

```java
// java.nio API
Files.delete(Paths.get(path));
```

# 19. Как переместить файл?

```java
// java.io API
boolean result = file.renameTo(new File(newFileName));
```

```java
// java.nio API
Path newPath = Files.move(path, Paths.get(newFileName));
```

# 20. Как управлять аттрибутами файла?

```java
// java.io API
boolean fileExists = file.exists();
boolean fileIsFile = file.isFile();
boolean fileIsDir = file.isDirectory();
boolean fileReadable = file.canRead();
boolean fileWritable = file.canWrite();
boolean fileExecutable = file.canExecute();
boolean fileHidden = file.isHidden();
```

```java
// java.nio API
boolean pathExists = Files.exists(path);
boolean pathIsFile = Files.isRegularFile(path);
boolean pathIsDir = Files.isDirectory(path);
boolean pathReadable = Files.isReadable(path);
boolean pathWritable = Files.isWritable(path);
boolean pathExecutable = Files.isExecutable(path);
boolean pathHidden = Files.isHidden(path);
```

```java
// java.nio API
BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
PosixFileAttributes attributes = Files.readAttributes(path, PosixFileAttributes.class);
```


# 21. Как создать файл?

```java
// java.io API
boolean result = file.createNewFile();
```

```java
// java.nio API
Path newPath = Files.createFile(path);
```

# 22. Как создать директорию?

```java
// java.io API
boolean result = file.mkdir();
```

```java
// java.nio API
File newPath = Files.createDirectory(path);
```

# 23. Как записать в файл?

```java
// java.io API, byte
try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
    os.write("Hello".getBytes());
}
```

```java
// java.io API, symbol
try(Writer wr = new BufferedWriter(new FileWriter(file))) {
    wr.write("Hello");
}
```

```java
// java.nio API, byte
Files.write(Path.of("log.txt"), "Hello".getBytes(StandardCharsets.UTF_8));
//Потоковый вывод
try (OutputStream os = Files.newOutputStream(utfFile)) {
    os.write("Hello".getBytes());
}
```

```java
// java.nio API, symbol
Files.writeString(Path.of("log.txt"), "Hello");
//Потоковый вывод
try (Writer wr = Files.newBufferedWriter(utfFile)) {
    wr.write("Hello");
}
```


# 24. Как прочитать данные из файла?

```java
// java.io API, byte
try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
    int inByte = 0;
    while ((inByte = in.read()) != 0) {
        
    }
}
```

```java
// java.io API, symbol
    try (Reader reader = new BufferedReader(new FileReader(file))) {
    int inByte = 0;
    while ((inByte = reader.read()) != 0) {

    }
}
```

```java
// java.nio API, byte
Files.readAllBytes(Path.of("log.txt"));
```

```java
// java.nio API, Потоки
try (InputStream is = Files.newInputStream(Path.of("log.txt"))) {
    int inByte = 0;
    while ((inByte = in.read()) != 0) {

        }
}
```

```java
// java.nio API, symbol
Files.readString(Path.of("log.txt"));
```

```java
// java.nio API, Потоки
try (BufferedReader bf = Files.newBufferedReader(utfFile)) {
    int inByte = 0;
    while ((inByte = bf.read()) != 0) {

    }
}
```

# 25. Что такое сокет?

Сокет представляет собой комбинацию IP - адреса и порта и является одним концом двустороннего канала связи между двумя программами.


# 26. Какие виды сокетов есть в Java? С каким протоколом они работают?

Сокеты бывают Клиентские и Срверные. Поддерживается оба протокола: TCP и UDP.


# 27. Как отправить через сокет сообщение?

```java
// java.io API
OutputStream out = socket.getOutputStream();
out.write("Hello".getBytes());
```

```java
// java.nio API
SocketChannel channel = SocketChannel.open(new InetSocketAddress(host, port));
OutputStream out = Channels.newOutputStream(channel);
out.write("Hello".getBytes());
```

# 28. Что такое логирование?

Логирование - это процесс записи в файл полезной (отладочной) информации о работе программы.


# 29. Какие уровни логирования вы знаете?

- OFF: никакие логи не записываются, все будут проигнорированы;
- FATAL: ошибка, после которой приложение уже не сможет работать и будет остановлено, например, JVM out of memory error;
- ERROR: уровень ошибок, когда есть проблемы, которые нужно решить. Ошибка не останавливает работу приложения в целом. Остальные запросы могут работать корректно;
- WARN: обозначаются логи, которые содержат предостережение. Произошло неожиданное действие, несмотря на это система устояла и выполнила запрос;
- INFO: лог, который записывает важные действия в приложении. Это не ошибки, это не предостережение, это ожидаемые действия системы;
- DEBUG: логи, необходимые для отладки приложения. Для уверенности в том, что система делает именно то, что от нее ожидают, или описания действия системы: “method1 начал работу”;
- TRACE: менее приоритетные логи для отладки, с наименьшим уровнем логирования;
- ALL: уровень, при котором будут записаны все логи из системы.


# 30. Какая библиотека для логгирования используется в курсе? Как ее настроить?

Log4j - как конкретная библиотека записи логов

slf4j - позволяет абстрагироваться от конкретных библиотек. Это позволяет придерживаться единого стиля логгирования для проектов, даже если в разных модялях проекта используются разные библиотеки логгирования.

Настройка:
1. Подключаем бибилотеки к проекту (вкладываем как ресурс или подключаем через мавен)
2. Создаем файл /src/main/resources/log4j.properties
3. Заполняем файл описание аппетдеров (приемников).
   Пример:

```
log4j.rootLogger=TRACE, CONSOLE_LOG, FILE_LOG

## Console appender
log4j.appender.CONSOLE_LOG=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE_LOG.Threshold=WARN
log4j.appender.CONSOLE_LOG.layout=org.apache.log4j.PatternLayout
log4j.appender.CONSOLE_LOG.layout.ConversionPattern=%d{ISO8601} %5p %c:%M:%L - %m%n

## File appender
log4j.appender.FILE_LOG=org.apache.log4j.FileAppender
log4j.appender.FILE_LOG.Threshold=DEBUG
log4j.appender.FILE_LOG.file=logs/debug.txt
log4j.appender.FILE_LOG.layout=org.apache.log4j.PatternLayout
log4j.appender.FILE_LOG.layout.ConversionPattern=%d{ISO8601} %5p %c:%M:%L - %m%n
```

1. rootLogger показывает уровень отображения (TRACE, INFO, DEBUG, WARN, ERROR) и подключенные приемники.
2. CONSOLE_LOG и FILE_LOG - это имена приемников. Они могут быть произвольными.
3. Threshold  параметр указывает уровень логгирования для приемника.
4. file параметр указывает расположение файла сохранения лога.


# 31. Опишите из каких элементов состоит формат JSON

Примитивные типы данных в JSON:
- число (целое или вещественное)
- литералы true, false и null
- строка —символы юникода, заключённые в двойные кавычки

Ссылочные типы данных:
- Объект - заключается в фигурные скобки ({ и }) и содержит разделенный запятой список пар имя/значение.
- Массив - заключается в квадратные скобки ([ и ]) и содержит разделенный запятой список значений.

# 32. Как преобразовать POJO в/из json?

```java
Gson gson = new GsonBuilder().create();
String jsonStr = gson.toJson(pogoObject);
```

```java
JSONObject jsonObject = new JSONObject();
jsonObject.put("name", pogoObject.getName());
String json = jsonObject.toString();
```

# 33. Опишите из каких элементов состоит формат XML

1. Объявление XML:
<?xml version="1.1" encoding="UTF-8" ?>

2. Теги. Это текст между угловыми скобками "<TagName>" и "</TagName>". Если у тега нет содержимого то он может не иметь закрвающего тега: <TagName />

3. Аттрибуты тега - это именованные параметры, указанные внутри угловых скобок:
   <size width="100" height="100"/>

4. Комментарии. Как однострочные, так и многострочные пишутся внутри <!-- и -->


# 34. Как преобразовать POJO в/из xml?

```java
JAXBContext context = JAXBContext.newInstance(pojoClass.class);
Marshaller marshaller = context.createMarshaller();
marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
String xml = "";
try (StringWriter writer = new StringWriter()) {
    marshaller.marshal(pojoObject, writer);
    xml = writer.getBuffer().toString();
}
```

```java
JAXBContext context = JAXBContext.newInstance(pojoClass.class);
Unmarshaller unmarshaller = context.createUnmarshaller();
try (StringReader reader = new StringReader(xml)) {
    pojoClass pojo = (pojoClass) unmarshaller.unmarshal(reader);
}
```