package com.example.minipro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minipro.R;
import com.example.minipro.model.Room;

public class EditRoomActivity extends AppCompatActivity {

    private EditText etRoomId, etRoomName, etRoomPrice, etTenantName, etTenantPhone;
    private RadioGroup rgStatus;
    private RadioButton rbAvailable, rbRented;
    private Button btnUpdate, btnCancel;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        initViews();
        loadRoomData();
        setupListeners();
    }

    private void initViews() {
        etRoomId = findViewById(R.id.et_room_id);
        etRoomName = findViewById(R.id.et_room_name);
        etRoomPrice = findViewById(R.id.et_room_price);
        etTenantName = findViewById(R.id.et_tenant_name);
        etTenantPhone = findViewById(R.id.et_tenant_phone);
        rgStatus = findViewById(R.id.rg_status);
        rbAvailable = findViewById(R.id.rb_available);
        rbRented = findViewById(R.id.rb_rented);
        btnUpdate = findViewById(R.id.btn_update);
        btnCancel = findViewById(R.id.btn_cancel);
    }

    private void loadRoomData() {
        Intent intent = getIntent();
        if (intent != null) {
            Room room = (Room) intent.getSerializableExtra("room");
            position = intent.getIntExtra("position", -1);

            if (room != null) {
                etRoomId.setText(room.getId());
                etRoomId.setEnabled(false);  // Không cho sửa mã phòng
                etRoomName.setText(room.getName());
                etRoomPrice.setText(String.valueOf((long) room.getPrice()));
                etTenantName.setText(room.getTenantName());
                etTenantPhone.setText(room.getTenantPhone());

                if (room.isRented()) {
                    rbRented.setChecked(true);
                } else {
                    rbAvailable.setChecked(true);
                }
            }
        }
    }

    private void setupListeners() {
        btnUpdate.setOnClickListener(v -> handleUpdate());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void handleUpdate() {
        // Validate dữ liệu
        String roomName = etRoomName.getText().toString().trim();
        String priceStr = etRoomPrice.getText().toString().trim();
        String tenantName = etTenantName.getText().toString().trim();
        String tenantPhone = etTenantPhone.getText().toString().trim();

        // Kiểm tra bắt buộc
        if (roomName.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra giá
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                Toast.makeText(this, getString(R.string.error_invalid_price), Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_invalid_price), Toast.LENGTH_SHORT).show();
            return;
        }

        // Nếu chọn "Đã thuê"
        boolean isRented = rbRented.isChecked();
        if (isRented) {
            if (tenantName.isEmpty() || tenantPhone.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidPhone(tenantPhone)) {
                Toast.makeText(this, getString(R.string.error_invalid_phone), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Tạo Room object
        Room room = new Room(
                etRoomId.getText().toString().trim(),
                roomName,
                price,
                isRented,
                tenantName,
                tenantPhone
        );

        // Truyền dữ liệu về MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("room", room);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, getString(R.string.success_updated), Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean isValidPhone(String phone) {
        return phone.length() >= 10 && phone.matches("[0-9]+");
    }
}
