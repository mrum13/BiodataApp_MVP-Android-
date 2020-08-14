package com.example.biodatamvp.view;

import com.example.biodatamvp.model.User;

import java.util.List;

public interface MainView {
    void onLoad(List<User> users);
    void onSave();
    void onDelete();
    void onUpdate();
}
