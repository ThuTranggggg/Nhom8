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
    
    private List<Room> roomList;
    private Context context;
    private OnItemActionListener listener;
    
    public interface OnItemActionListener {
        void onEdit(int position);
        void onDelete(int position);
    }
    
    public RoomAdapter(List<Room> roomList, Context context, OnItemActionListener listener) {
        this.roomList = roomList;
        this.context = context;
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
        
        // Tên phòng
        holder.tvName.setText(room.getName());
        
        // Giá thuê
        String priceText = String.format("Giá: %,.0f VND", room.getPrice());
        holder.tvPrice.setText(priceText);
        
        // Tình trạng và màu
        if (room.isRented()) {
            holder.tvStatus.setText(R.string.status_rented);
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.status_rented));
            
            // Hiển thị tên người thuê
            holder.tvTenant.setVisibility(View.VISIBLE);
            String tenantText = context.getString(R.string.label_tenant) + " " + room.getTenantName();
            holder.tvTenant.setText(tenantText);
        } else {
            holder.tvStatus.setText(R.string.status_available);
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.status_available));
            
            // Ẩn tên người thuê
            holder.tvTenant.setVisibility(View.GONE);
        }
        
        // Nút Edit
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(position);
            }
        });
        
        // Nút Delete
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(position);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return roomList.size();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvStatus;
        public TextView tvTenant;
        public Button btnEdit;
        public Button btnDelete;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_room_name);
            tvPrice = itemView.findViewById(R.id.tv_room_price);
            tvStatus = itemView.findViewById(R.id.tv_room_status);
            tvTenant = itemView.findViewById(R.id.tv_tenant_name);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
