package com.example.minipro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minipro.model.Room;

public class AddRoomActivity extends AppCompatActivity {
    
    private EditText edtRoomId, edtRoomName, edtPrice, edtTenantName, edtTenantPhone;
    private RadioGroup rgStatus;
    private Button btnSave, btnCancel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        
        // Initialize views
        edtRoomId = findViewById(R.id.edtRoomId);
        edtRoomName = findViewById(R.id.edtRoomName);
        edtPrice = findViewById(R.id.edtPrice);
        rgStatus = findViewById(R.id.rgStatus);
        edtTenantName = findViewById(R.id.edtTenantName);
        edtTenantPhone = findViewById(R.id.edtTenantPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        
        // Handle status change to show/hide tenant fields
        rgStatus.setOnCheckedChangeListener((group, checkedId) -> {
            boolean isRented = checkedId == R.id.rbRented;
            edtTenantName.setEnabled(isRented);
            edtTenantPhone.setEnabled(isRented);
            if (!isRented) {
                edtTenantName.setText("");
                edtTenantPhone.setText("");
            }
        });
        
        // Save button
        btnSave.setOnClickListener(v -> saveRoom());
        
        // Cancel button
        btnCancel.setOnClickListener(v -> finish());
    }
    
    private void saveRoom() {
        // Get input values
        String roomId = edtRoomId.getText().toString().trim();
        String roomName = edtRoomName.getText().toString().trim();
        String priceStr = edtPrice.getText().toString().trim();
        String tenantName = edtTenantName.getText().toString().trim();
        String tenantPhone = edtTenantPhone.getText().toString().trim();
        
        // Validate inputs
        if (roomId.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
            return;
        }
        
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
        
        // Create new Room object
        Room newRoom = new Room(roomId, roomName, price, isRented, tenantName, tenantPhone);
        
        // Return data to MainActivity
        Intent intent = new Intent();
        intent.putExtra("room", newRoom);
        setResult(RESULT_OK, intent);
        finish();
    }
}
