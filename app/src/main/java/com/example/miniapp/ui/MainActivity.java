package com.example.miniapp.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniapp.R;
import com.example.miniapp.adapter.RoomAdapter;
import com.example.miniapp.model.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RoomAdapter.OnItemActionListener {
    
    private RecyclerView recyclerView;
    private RoomAdapter adapter;
    private List<Room> roomList;
    private Button btnAddRoom;
    
    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        btnAddRoom = findViewById(R.id.btnAddRoom);
        
        // Initialize room list with sample data
        roomList = new ArrayList<>();
        roomList.add(new Room("P001", "Phòng A101", 3000000, false, "", ""));
        roomList.add(new Room("P002", "Phòng A102", 3000000, true, "Nguyễn Văn A", "0912345678"));
        roomList.add(new Room("P003", "Phòng A103", 2500000, false, "", ""));
        roomList.add(new Room("P004", "Phòng B201", 3500000, true, "Trần Thị B", "0987654321"));
        
        // Setup RecyclerView
        adapter = new RoomAdapter(roomList, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        
        // Setup Add button
        btnAddRoom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });
    }
    
    @Override
    public void onEdit(int position) {
        Room room = roomList.get(position);
        Intent intent = new Intent(MainActivity.this, EditRoomActivity.class);
        intent.putExtra("room", room);
        intent.putExtra("position", position);
        startActivityForResult(intent, REQUEST_EDIT);
    }
    
    @Override
    public void onDelete(int position) {
        Room room = roomList.get(position);
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_delete_title))
                .setMessage(getString(R.string.dialog_delete_message))
                .setPositiveButton(getString(R.string.dialog_yes), (dialog, which) -> {
                    roomList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, getString(R.string.success_deleted), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(getString(R.string.dialog_no), null)
                .show();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ADD && data != null) {
                Room newRoom = (Room) data.getSerializableExtra("room");
                if (newRoom != null) {
                    roomList.add(newRoom);
                    adapter.notifyItemInserted(roomList.size() - 1);
                    Toast.makeText(this, getString(R.string.success_added), Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_EDIT && data != null) {
                Room updatedRoom = (Room) data.getSerializableExtra("room");
                int position = data.getIntExtra("position", -1);
                if (updatedRoom != null && position >= 0) {
                    roomList.set(position, updatedRoom);
                    adapter.notifyItemChanged(position);
                    Toast.makeText(this, getString(R.string.success_updated), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
