package ru.job4j.generics.store;

public class RoleStore implements Store<Role> {

    private final Store<Role> storage = new MemStore<>();

    @Override
    public void add(Role model) {
        storage.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return storage.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return storage.delete(id);
    }

    @Override
    public Role findById(String id) {
        return storage.findById(id);
    }
}
