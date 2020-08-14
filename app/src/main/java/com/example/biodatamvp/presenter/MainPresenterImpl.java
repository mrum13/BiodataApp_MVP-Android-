package com.example.biodatamvp.presenter;

import com.example.biodatamvp.model.User;
import com.example.biodatamvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private List<User> users = new ArrayList<>();
    private int no = 4;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        init();
    }

    private void init() {
        User user = new User();
        user.setId(1);
        user.setNama("Muhammad Rum");
        user.setNoHp("085397875488");
        user.setPekerjaan("Android Developer");
        user.setStatus("Maumi nikah(Aamiin)");
        users.add(user);

        User user1 = new User();
        user1.setId(2);
        user1.setNama("Nunu");
        user1.setNoHp("08293270181");
        user1.setPekerjaan("Designer UI/UX");
        user1.setStatus("Maumi nikah(bede)");
        users.add(user1);

        User user2 = new User();
        user2.setId(3);
        user2.setNama("Siapa ?");
        user2.setNoHp("089999999");
        user2.setPekerjaan("Fullstack");
        user2.setStatus("Menikah tawwa");
        users.add(user2);
    }

    @Override
    public void save(User user) {
        no++;
        user.setId(no);
        users.add(user);

        mainView.onSave();
    }

    @Override
    public void update(User user) {
        for (User model : users){
            if (model.getId() == user.getId()){
                model.setNama(user.getNama());
                model.setNoHp(user.getNoHp());
                model.setStatus(user.getStatus());
                model.setPekerjaan(user.getPekerjaan());

                break;
            }
        }

        mainView.onUpdate();
    }

    @Override
    public void delete(User user) {
        users.remove(user);

        mainView.onDelete();
    }

    @Override
    public void load() {
        mainView.onLoad(users);
    }
}
