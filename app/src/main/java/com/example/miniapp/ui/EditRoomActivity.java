package com.example.miniapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniapp.R;
import com.example.miniapp.model.Room;

public class EditRoomActivity extends AppCompatActivity {
    
    private EditText edtRoomId, edtRoomName, edtPrice, edtTenantName, edtTenantPhone;
    private RadioGroup rgStatus;
    private Button btnUpdate, btnCancel;
    private Room room;
    private int position;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        
        // Get data from intent
        Intent intent = getIntent();
        room = (Room) intent.getSerializableExtra("room");
        position = intent.getIntExtra("position", -1);
        
        if (room == null) {
            finish();
            return;
        }
        
        // Initialize views
        edtRoomId = findViewById(R.id.et_room_id);
        edtRoomName = findViewById(R.id.et_room_name);
        edtPrice = findViewById(R.id.et_room_price);
        rgStatus = findViewById(R.id.rg_status);
        edtTenantName = findViewById(R.id.et_tenant_name);
        edtTenantPhone = findViewById(R.id.et_tenant_phone);
        btnUpdate = findViewById(R.id.btn_update);
        btnCancel = findViewById(R.id.btn_cancel);
        
        // Pre-fill form with room data
        edtRoomId.setText(room.getId());
        edtRoomId.setEnabled(false); // Disable editing room ID
        edtRoomName.setText(room.getName());
        edtPrice.setText(String.valueOf(room.getPrice()));
        
        if (room.isRented()) {
            rgStatus.check(R.id.rb_rented);
            edtTenantName.setText(room.getTenantName());
            edtTenantPhone.setText(room.getTenantPhone());
        } else {
            rgStatus.check(R.id.rb_available);
            edtTenantName.setText("");
            edtTenantPhone.setText("");
        }
        
        // Handle status change to show/hide tenant fields
        rgStatus.setOnCheckedChangeListener((group, checkedId) -> {
            boolean isRented = checkedId == R.id.rb_rented;
            edtTenantName.setEnabled(isRented);
            edtTenantPhone.setEnabled(isRented);
            if (!isRented) {
                edtTenantName.setText("");
                edtTenantPhone.setText("");
            }
        });
        
        // Update button
        btnUpdate.setOnClickListener(v -> updateRoom());
        
        // Cancel button
        btnCancel.setOnClickListener(v -> finish());
    }
    
    private void updateRoom() {
        // Get input values
        String roomName = edtRoomName.getText().toString().trim();
        String priceStr = edtPrice.getText().toString().trim();
        String tenantName = edtTenantName.getText().toString().trim();
        String tenantPhone = edtTenantPhone.getText().toString().trim();
        
        // Validate inputs
        if (roomName.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (priceStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
            return;
        }
        
        long price;
        try {
            price = Long.parseLong(priceStr);
            if (price <= 0) {
                Toast.makeText(this, getString(R.string.error_invalid_price), Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_invalid_price), Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Check if rented
        boolean isRented = rgStatus.getCheckedRadioButtonId() == R.id.rbRented;
        
        if (isRented) {
            if (tenantName.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (tenantPhone.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (tenantPhone.length() < 10) {
                Toast.makeText(this, getString(R.string.error_invalid_phone), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        
        // Update room object
        room.setName(roomName);
        room.setPrice(price);
        room.setRented(isRented);
        room.setTenantName(tenantName);
        room.setTenantPhone(tenantPhone);
        
        // Return data to MainActivity
        Intent intent = new Intent();
        intent.putExtra("room", room);
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
