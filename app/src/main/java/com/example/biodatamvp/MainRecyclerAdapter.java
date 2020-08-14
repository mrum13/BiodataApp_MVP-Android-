package com.example.biodatamvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biodatamvp.model.User;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private List<User> users;
    private OnCallBackListener listener;


    public MainRecyclerAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewNama.setText(user.getNama());
        holder.textViewStatus.setText(user.getStatus());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnCallBackListener(OnCallBackListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNama;
        TextView textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNama = itemView.findViewById(R.id.nama);
            textViewStatus = itemView.findViewById(R.id.status);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onClick(users.get(getAdapterPosition()));
            }
        }
    }

    public interface OnCallBackListener{
        void onClick(User user);
    }
}
