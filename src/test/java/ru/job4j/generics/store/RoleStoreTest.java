package ru.job4j.generics.store;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRolenameIsRole1() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Role1"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsRole1() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Role1"));
    }

    @Test
    public void whenReplaceThenRolenameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        store.replace("1", new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Maxim"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Role1"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsRole1() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role1"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName(), is("Role1"));
    }
}