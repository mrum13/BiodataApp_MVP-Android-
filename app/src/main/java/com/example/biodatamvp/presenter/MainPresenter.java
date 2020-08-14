package com.example.biodatamvp.presenter;

import com.example.biodatamvp.model.User;

public interface MainPresenter {
    void save(User user);
    void update(User user);
    void delete(User user);
    void load();
}
