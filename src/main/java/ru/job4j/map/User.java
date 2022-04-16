package ru.job4j.map;

import java.util.Calendar;

public class User {
    private final String name;
    private int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int rsl = 17;
        rsl = 31 * rsl + name.hashCode();
        rsl = 31 * rsl + children;
        rsl = 31 * rsl + birthday.hashCode();
        return rsl;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return name.equals(((User) obj).name) && birthday.equals(((User) obj).birthday) && children == ((User) obj).children;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setChildren(int children) {
        this.children = children;
    }
}
