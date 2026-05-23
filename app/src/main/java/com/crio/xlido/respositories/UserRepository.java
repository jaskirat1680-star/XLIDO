package com.crio.xlido.respositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.User;

public class UserRepository implements IUserRepository{
    private final Map<Long, User> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public List<User> findAll(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public User save(User entity) {
        User newUser = new User(idCounter.incrementAndGet(), entity);
        storage.putIfAbsent(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

}
