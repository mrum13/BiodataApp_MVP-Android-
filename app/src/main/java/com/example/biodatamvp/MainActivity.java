package com.example.biodatamvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biodatamvp.model.User;
import com.example.biodatamvp.presenter.MainPresenter;
import com.example.biodatamvp.presenter.MainPresenterImpl;
import com.example.biodatamvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private RecyclerView recyclerView;
    private Button buttonAdd;
    private MainRecyclerAdapter adapter;
    private List<User> userList = new ArrayList<>();
    private AppCompatDialog dialog;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainRecyclerAdapter(userList);
        adapter.setOnCallBackListener(new MainRecyclerAdapter.OnCallBackListener() {
            @Override
            public void onClick(User user) {
                showDialogDetail(user);
            }
        });

        recyclerView.setAdapter(adapter);

        buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogInput();
            }
        });

        presenter.load();

    }

    @Override
    public void onLoad(List<User> users) {
        userList.clear();
        userList.addAll(users);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSave() {
        Toast.makeText(this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show();
        presenter.load();
    }

    @Override
    public void onDelete() {
        Toast.makeText(this, "Berhasil dihapus!", Toast.LENGTH_SHORT).show();
        presenter.load();
    }

    @Override
    public void onUpdate() {
        Toast.makeText(this, "Berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        presenter.load();
    }

    public void showDialogInput(){
        dialog = new AppCompatDialog(this);
        dialog.setContentView(R.layout.dialog_input);
        dialog.setTitle("");

        final EditText editTextNama = dialog.findViewById(R.id.edittext_nama);
        final EditText editTextNoHp = dialog.findViewById(R.id.edittext_nohp);
        final EditText editTextPekerjaan = dialog.findViewById(R.id.edittext_pekerjaan);
        final EditText editTextStatus = dialog.findViewById(R.id.edittext_status);

        Button buttonSave = dialog.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setNama(editTextNama.getText().toString());
                user.setNoHp(editTextNoHp.getText().toString());
                user.setPekerjaan(editTextPekerjaan.getText().toString());
                user.setStatus(editTextStatus.getText().toString());

                presenter.save(user);
                dialog.dismiss();
            }
        });

        Button buttonCancel = dialog.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()){
            dialog.show();
        }
    }

    public void showDialogDetail(final User user) {
        dialog = new AppCompatDialog(this);
        dialog.setContentView(R.layout.dialog_input);
        dialog.setTitle("");

        final EditText editTextNama = dialog.findViewById(R.id.edittext_nama);
        final EditText editTextNoHp = dialog.findViewById(R.id.edittext_nohp);
        final EditText editTextPekerjaan = dialog.findViewById(R.id.edittext_pekerjaan);
        final EditText editTextStatus = dialog.findViewById(R.id.edittext_status);

        editTextNama.setText(user.getNama());
        editTextNoHp.setText(user.getNoHp());
        editTextPekerjaan.setText(user.getPekerjaan());
        editTextStatus.setText(user.getStatus());

        Button buttonUpdate = dialog.findViewById(R.id.button_save);
        buttonUpdate.setText("Update");
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNama(editTextNama.getText().toString());
                user.setNoHp(editTextNoHp.getText().toString());
                user.setPekerjaan(editTextPekerjaan.getText().toString());
                user.setStatus(editTextStatus.getText().toString());

                presenter.update(user);
                dialog.dismiss();
            }
        });

        Button buttonDelete = dialog.findViewById(R.id.button_cancel);
        buttonDelete.setText("Delete");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete(user);
                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}