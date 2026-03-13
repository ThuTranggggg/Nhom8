package com.example.miniapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniapp.R;
import com.example.miniapp.model.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    
    private Context context;
    private List<Room> roomList;
    private OnItemActionListener listener;
    
    // Interface for item actions
    public interface OnItemActionListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
    
    public RoomAdapter(Context context, List<Room> roomList, OnItemActionListener listener) {
        this.context = context;
        this.roomList = roomList;
        this.listener = listener;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = roomList.get(position);
        
        // Set room name
        holder.tvRoomName.setText(room.getName());
        
        // Set price
        holder.tvPrice.setText("Giá: " + String.format("%,d", room.getPrice()) + " VND");
        
        // Set status
        if (room.isRented()) {
            holder.tvStatus.setText("Đã thuê");
            holder.tvStatus.setTextColor(context.getColor(R.color.danger));
        } else {
            holder.tvStatus.setText("Còn trống");
            holder.tvStatus.setTextColor(context.getColor(R.color.success));
        }
        
        // Set tenant info visibility
        if (room.isRented()) {
            holder.tvTenantName.setVisibility(View.VISIBLE);
            holder.tvTenantName.setText("Người thuê: " + room.getTenantName());
        } else {
            holder.tvTenantName.setVisibility(View.GONE);
        }
        
        // Edit button
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });
        
        // Delete button
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return roomList.size();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomName, tvPrice, tvStatus, tvTenantName;
        Button btnEdit, btnDelete;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTenantName = itemView.findViewById(R.id.tvTenantName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
