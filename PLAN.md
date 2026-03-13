# PLAN.md - PHÂN CÔNG TEAMWORK

## Tổng quan phân công

| Người | Nhiệm vụ code | Merge? |
|-------|--------------|--------|
| **Trang** | RoomAdapter + Room Model + Resource files (colors.xml, strings.xml, dimens.xml) + item_room.xml | **Có** — merge tất cả sau khi Hoa & Thao push |
| **Hoa** | MainActivity + activity_main.xml + AddRoomActivity + activity_add_room.xml + edit_text_bg.xml | Không |
| **Thao** | EditRoomActivity + activity_edit_room.xml + AndroidManifest.xml | Không |

> ⚠️ **Cả 3 người đều phải viết code Java và XML thực sự.**  
> Trang vừa code phần của mình, vừa là người thực hiện merge cuối cùng.

---

## Setup chung — tất cả hãy làm trước khi bắt đầu (5 phút)

1. **Clone hoặc pull repo** về máy
   ```bash
   git clone <repo_url>
   cd MiniApp
   ```

2. **Mở Android Studio, chờ Gradle sync xong**
   - File → Open → chọn thư mục MiniApp
   - Gradle sync sẽ tự chạy

3. **Tạo branch của mình riêng (QUAN TRỌNG!)**
   ```bash
   git checkout -b trang   # Nếu bạn là Trang
   # hoặc
   git checkout -b hoa     # Nếu bạn là Hoa
   # hoặc
   git checkout -b thao    # Nếu bạn là Thao
   ```

4. **Chạy app lần đầu** để confirm build thành công
   - Run → Run 'app'
   - Hoặc Shift+F10 (Windows)

5. **Quy tắc bắt buộc**
   - KHÔNG sửa `colors.xml`, `strings.xml`, `dimens.xml` — chỉ dùng `@color/`, `@string/`, `@dimen/`
   - KHÔNG sửa bất kỳ file nào của người khác
   - KHÔNG commit trực tiếp vào `main`
   - Code luôn trên branch của mình, push khi xong

---

## Trang — Người chủ project

### ✅ Nhiệm vụ chính

1. **res/values/colors.xml** — Định nghĩa TẤT CẢ màu (xem PROJECT.md §3)
2. **res/values/strings.xml** — Định nghĩa TẤT CẢ string (xem PROJECT.md §4)
3. **res/values/dimens.xml** — Định nghĩa TẤT CẢ dimension (xem PROJECT.md §5)
4. **java/com/example/miniapp/model/Room.java** — Model POJO Room
5. **java/com/example/miniapp/adapter/RoomAdapter.java** — Adapter hiển thị list
6. **res/layout/item_room.xml** — Layout item trong RecyclerView
7. ✅ **Merge branch của Hoa & Thao** sau khi cả 2 push xong

### 📋 Files cần tạo / chỉnh sửa

| File | Loại | Mô tả | Ưu tiên |
|------|------|-------|---------|
| res/values/colors.xml | Resource | Định nghĩa màu | **1 — Commit TRƯỚC TIÊN** |
| res/values/strings.xml | Resource | Định nghĩa string | **2 — Commit thứ 2** |
| res/values/dimens.xml | Resource | Định nghĩa dimen | **3 — Commit thứ 3** |
| java/.../model/Room.java | Java | Model POJO | **4 — Commit thứ 4** |
| java/.../adapter/RoomAdapter.java | Java | Adapter RecyclerView | 5 |
| res/layout/item_room.xml | XML | Layout item | 5 |
| java/.../ui/MainActivity.java | Java | Activity chính | 6 |
| res/layout/activity_main.xml | XML | Layout MainActivity | 6 |
| AndroidManifest.xml | XML | Thêm activity tags | 7 |

### 🔧 Chi tiết từng file

#### **res/values/colors.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Primary Colors -->
    <color name="primary">#2196F3</color>
    <color name="primary_dark">#1976D2</color>
    <color name="accent">#FF5722</color>
    
    <!-- Background -->
    <color name="background_main">#FFFFFF</color>
    <color name="background_secondary">#F5F5F5</color>
    
    <!-- Text Colors -->
    <color name="text_primary">#212121</color>
    <color name="text_secondary">#757575</color>
    <color name="text_light">#BDBDBD</color>
    <color name="text_white">#FFFFFF</color>
    
    <!-- Status Colors -->
    <color name="status_available">#4CAF50</color>
    <color name="status_rented">#F44336</color>
    
    <!-- Button -->
    <color name="button_primary">#2196F3</color>
    <color name="button_danger">#F44336</color>
    
    <!-- Border -->
    <color name="border_light">#E0E0E0</color>
    <color name="border_dark">#BDBDBD</color>
</resources>
```

**Commit:**
```bash
git add res/values/colors.xml
git commit -m "feat(trang): add colors resource"
git push origin trang
```

---

#### **res/values/strings.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">Room Rental Management</string>
    
    <!-- MainActivity -->
    <string name="title_room_list">Danh sách phòng trọ</string>
    <string name="btn_add_room">Thêm phòng</string>
    <string name="empty_list">Chưa có phòng nào</string>
    
    <!-- AddRoomActivity / EditRoomActivity -->
    <string name="title_add_room">Thêm phòng mới</string>
    <string name="title_edit_room">Sửa thông tin phòng</string>
    <string name="hint_room_id">Mã phòng (P001, P002, ...)</string>
    <string name="hint_room_name">Tên phòng</string>
    <string name="hint_room_price">Giá thuê/tháng (VND)</string>
    <string name="hint_tenant_name">Tên người thuê</string>
    <string name="hint_tenant_phone">Số điện thoại</string>
    <string name="label_status">Tình trạng</string>
    <string name="status_available">Còn trống</string>
    <string name="status_rented">Đã thuê</string>
    <string name="btn_save">Lưu</string>
    <string name="btn_update">Cập nhật</string>
    <string name="btn_cancel">Hủy</string>
    
    <!-- RoomAdapter / Item -->
    <string name="label_price">Giá:</string>
    <string name="label_tenant">Người thuê:</string>
    <string name="label_phone">SĐT:</string>
    <string name="btn_edit">Sửa</string>
    <string name="btn_delete">Xóa</string>
    
    <!-- Dialog -->
    <string name="dialog_delete_title">Xác nhận xóa</string>
    <string name="dialog_delete_message">Bạn chắc chắn muốn xóa phòng này?</string>
    <string name="dialog_yes">Có</string>
    <string name="dialog_no">Không</string>
    
    <!-- Validation -->
    <string name="error_empty_field">Không được để trống</string>
    <string name="error_invalid_price">Giá phải là số dương</string>
    <string name="error_invalid_phone">Số điện thoại không hợp lệ</string>
    <string name="success_added">Thêm phòng thành công</string>
    <string name="success_updated">Cập nhật phòng thành công</string>
    <string name="success_deleted">Xóa phòng thành công</string>
</resources>
```

**Commit:**
```bash
git add res/values/strings.xml
git commit -m "feat(trang): add strings resource"
git push origin trang
```

---

#### **res/values/dimens.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Margin / Padding -->
    <dimen name="margin_small">8dp</dimen>
    <dimen name="margin_medium">16dp</dimen>
    <dimen name="margin_large">24dp</dimen>
    
    <!-- Text Size -->
    <dimen name="text_size_small">12sp</dimen>
    <dimen name="text_size_normal">14sp</dimen>
    <dimen name="text_size_medium">16sp</dimen>
    <dimen name="text_size_large">18sp</dimen>
    <dimen name="text_size_title">20sp</dimen>
    
    <!-- Item Height -->
    <dimen name="item_room_height">200dp</dimen>
    <dimen name="button_height">48dp</dimen>
    
    <!-- Corner Radius -->
    <dimen name="corner_radius">8dp</dimen>
</resources>
```

**Commit:**
```bash
git add res/values/dimens.xml
git commit -m "feat(trang): add dimens resource"
git push origin trang
```

---

#### **java/com/example/miniapp/model/Room.java**

```java
package com.example.miniapp.model;

public class Room {
    private String id;              // Mã phòng (P001, P002, ...)
    private String name;            // Tên phòng
    private double price;           // Giá thuê/tháng
    private boolean isRented;       // true = Đã thuê, false = Còn trống
    private String tenantName;      // Tên người thuê
    private String tenantPhone;     // Số điện thoại người thuê
    
    // Constructor
    public Room(String id, String name, double price, boolean isRented, 
                String tenantName, String tenantPhone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isRented = isRented;
        this.tenantName = tenantName;
        this.tenantPhone = tenantPhone;
    }
    
    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }
    
    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
    
    public String getTenantPhone() { return tenantPhone; }
    public void setTenantPhone(String tenantPhone) { this.tenantPhone = tenantPhone; }
}
```

**Commit:**
```bash
git add java/com/example/miniapp/model/Room.java
git commit -m "feat(trang): add Room model"
git push origin trang
```

---

#### **java/com/example/miniapp/adapter/RoomAdapter.java**

```java
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
        
        holder.tvName.setText(room.getName());
        holder.tvPrice.setText(String.format("Giá: %.0f VND", room.getPrice()));
        
        // Hiển thị tình trạng với màu
        if (room.isRented()) {
            holder.tvStatus.setText(context.getString(R.string.status_rented));
            holder.tvStatus.setTextColor(context.getColor(R.color.status_rented));
        } else {
            holder.tvStatus.setText(context.getString(R.string.status_available));
            holder.tvStatus.setTextColor(context.getColor(R.color.status_available));
        }
        
        // Hiển thị tên người thuê nếu đã cho thuê
        if (room.isRented() && room.getTenantName() != null && !room.getTenantName().isEmpty()) {
            holder.tvTenant.setText(context.getString(R.string.label_tenant) + " " + room.getTenantName());
            holder.tvTenant.setVisibility(View.VISIBLE);
        } else {
            holder.tvTenant.setVisibility(View.GONE);
        }
        
        // Click listeners
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(position);
        });
        
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(position);
        });
    }
    
    @Override
    public int getItemCount() {
        return roomList.size();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvPrice, tvStatus, tvTenant;
        public Button btnEdit, btnDelete;
        
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
```

**Commit:**
```bash
git add java/com/example/miniapp/adapter/RoomAdapter.java
git commit -m "feat(trang): add RoomAdapter"
git push origin trang
```

---

#### **res/layout/item_room.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="@dimen/item_room_height"
    android:orientation="vertical"
    android:padding="@dimen/margin_medium"
    android:background="@color/background_main"
    android:borderless="true">
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:orientation="vertical">
        
        <TextView
            android:id="@+id/tv_room_name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Phòng A101"
            android:textSize="@dimen/text_size_large"
            android:textColor="@color/text_primary"
            android:textStyle="bold" />
        
        <TextView
            android:id="@+id/tv_room_price"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Giá: 3.000.000 VND"
            android:textSize="@dimen/text_size_medium"
            android:textColor="@color/text_secondary"
            android:marginTop="@dimen/margin_small" />
        
        <TextView
            android:id="@+id/tv_room_status"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Còn trống"
            android:textSize="@dimen/text_size_medium"
            android:textColor="@color/status_available"
            android:textStyle="bold"
            android:marginTop="@dimen/margin_small" />
        
        <TextView
            android:id="@+id/tv_tenant_name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Người thuê: Nguyễn Văn A"
            android:textSize="@dimen/text_size_normal"
            android:textColor="@color/text_secondary"
            android:marginTop="@dimen/margin_small"
            android:visibility="gone" />
    </LinearLayout>
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:marginTop="@dimen/margin_medium">
        
        <Button
            android:id="@+id/btn_edit"
            android:layout_width="0dp"
            android:layout_height="@dimen/button_height"
            android:layout_weight="1"
            android:text="@string/btn_edit"
            android:background="@color/button_primary"
            android:textColor="@color/text_white"
            android:marginEnd="@dimen/margin_small" />
        
        <Button
            android:id="@+id/btn_delete"
            android:layout_width="0dp"
            android:layout_height="@dimen/button_height"
            android:layout_weight="1"
            android:text="@string/btn_delete"
            android:background="@color/button_danger"
            android:textColor="@color/text_white"
            android:marginStart="@dimen/margin_small" />
    </LinearLayout>
</LinearLayout>
```

**Commit:**
```bash
git add res/layout/item_room.xml
git commit -m "feat(trang): add item_room layout"
git push origin trang
```

---

#### **java/com/example/miniapp/ui/MainActivity.java**

```java
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
    
    private RecyclerView rvRoomList;
    private Button btnAddRoom;
    private RoomAdapter adapter;
    private List<Room> roomList;
    
    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_EDIT = 2;
    private int editingPosition = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        initData();
        setupRecyclerView();
        setupListeners();
    }
    
    private void initViews() {
        rvRoomList = findViewById(R.id.rv_room_list);
        btnAddRoom = findViewById(R.id.btn_add_room);
    }
    
    private void initData() {
        roomList = new ArrayList<>();
        // Hardcode dữ liệu mẫu
        roomList.add(new Room("P001", "Phòng A101", 3000000, false, "", ""));
        roomList.add(new Room("P002", "Phòng A102", 3000000, true, "Nguyễn Văn A", "0912345678"));
        roomList.add(new Room("P003", "Phòng A103", 2500000, false, "", ""));
        roomList.add(new Room("P004", "Phòng B201", 3500000, true, "Trần Thị B", "0987654321"));
    }
    
    private void setupRecyclerView() {
        adapter = new RoomAdapter(roomList, this, this);
        rvRoomList.setAdapter(adapter);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
    }
    
    private void setupListeners() {
        btnAddRoom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });
    }
    
    @Override
    public void onEdit(int position) {
        editingPosition = position;
        Room room = roomList.get(position);
        Intent intent = new Intent(MainActivity.this, EditRoomActivity.class);
        intent.putExtra("room_id", room.getId());
        intent.putExtra("room_name", room.getName());
        intent.putExtra("room_price", room.getPrice());
        intent.putExtra("room_rented", room.isRented());
        intent.putExtra("tenant_name", room.getTenantName());
        intent.putExtra("tenant_phone", room.getTenantPhone());
        intent.putExtra("position", position);
        startActivityForResult(intent, REQUEST_EDIT);
    }
    
    @Override
    public void onDelete(int position) {
        Room room = roomList.get(position);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.dialog_delete_title)
                .setMessage(R.string.dialog_delete_message)
                .setPositiveButton(R.string.dialog_yes, (dialog, which) -> {
                    roomList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, R.string.success_deleted, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.dialog_no, null)
                .show();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_ADD) {
                Room newRoom = (Room) data.getSerializableExtra("new_room");
                if (newRoom != null) {
                    roomList.add(newRoom);
                    adapter.notifyItemInserted(roomList.size() - 1);
                    Toast.makeText(this, R.string.success_added, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_EDIT) {
                Room updatedRoom = (Room) data.getSerializableExtra("updated_room");
                int position = data.getIntExtra("position", -1);
                if (updatedRoom != null && position >= 0) {
                    roomList.set(position, updatedRoom);
                    adapter.notifyItemChanged(position);
                    Toast.makeText(this, R.string.success_updated, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
```

> ⚠️ **CHÚ Ý:** Room.java sẽ được modify để implement Serializable để truyền qua Bundle:

```java
// Sửa lại Room.java (trong BƯỚC 4)
package com.example.miniapp.model;

import java.io.Serializable;

public class Room implements Serializable {
    // ... giữ nguyên
}
```

**Commit:**
```bash
git add java/com/example/miniapp/adapter/RoomAdapter.java
git commit -m "feat(trang): add RoomAdapter"
git push origin trang
```

---

### ✅ Trang hoàn thành!

Khi xong toàn bộ code của mình, Trang chờ Hoa & Thao push xong branch của họ, rồi thực hiện merge (xem mục **MERGE** bên dưới).

---

## Hoa — MainActivity + AddRoomActivity

### ✅ Nhiệm vụ

Viết **MainActivity** (hiển thị danh sách phòng) và **AddRoomActivity** (thêm phòng mới).

### 📋 Files cần tạo

| File | Loại | Mô tả |
|------|------|-------|
| java/com/example/miniapp/ui/MainActivity.java | Java | Activity chính - hiển thị danh sách phòng |
| res/layout/activity_main.xml | XML | Layout MainActivity |
| java/com/example/miniapp/ui/AddRoomActivity.java | Java | Activity thêm phòng mới |
| res/layout/activity_add_room.xml | XML | Layout form thêm phòng |
| res/drawable/edit_text_bg.xml | Drawable | Background cho EditText |

### 🔧 Chi tiết từng file

#### **java/com/example/miniapp/ui/MainActivity.java**

```java
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
    
    private RecyclerView rvRoomList;
    private Button btnAddRoom;
    private RoomAdapter adapter;
    private List<Room> roomList;
    
    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_EDIT = 2;
    private int editingPosition = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        initData();
        setupRecyclerView();
        setupListeners();
    }
    
    private void initViews() {
        rvRoomList = findViewById(R.id.rv_room_list);
        btnAddRoom = findViewById(R.id.btn_add_room);
    }
    
    private void initData() {
        roomList = new ArrayList<>();
        // Hardcode dữ liệu mẫu
        roomList.add(new Room("P001", "Phòng A101", 3000000, false, "", ""));
        roomList.add(new Room("P002", "Phòng A102", 3000000, true, "Nguyễn Văn A", "0912345678"));
        roomList.add(new Room("P003", "Phòng A103", 2500000, false, "", ""));
        roomList.add(new Room("P004", "Phòng B201", 3500000, true, "Trần Thị B", "0987654321"));
    }
    
    private void setupRecyclerView() {
        adapter = new RoomAdapter(roomList, this, this);
        rvRoomList.setAdapter(adapter);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
    }
    
    private void setupListeners() {
        btnAddRoom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });
    }
    
    @Override
    public void onEdit(int position) {
        editingPosition = position;
        Room room = roomList.get(position);
        Intent intent = new Intent(MainActivity.this, EditRoomActivity.class);
        intent.putExtra("room_id", room.getId());
        intent.putExtra("room_name", room.getName());
        intent.putExtra("room_price", room.getPrice());
        intent.putExtra("room_rented", room.isRented());
        intent.putExtra("tenant_name", room.getTenantName());
        intent.putExtra("tenant_phone", room.getTenantPhone());
        intent.putExtra("position", position);
        startActivityForResult(intent, REQUEST_EDIT);
    }
    
    @Override
    public void onDelete(int position) {
        Room room = roomList.get(position);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.dialog_delete_title)
                .setMessage(R.string.dialog_delete_message)
                .setPositiveButton(R.string.dialog_yes, (dialog, which) -> {
                    roomList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, R.string.success_deleted, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.dialog_no, null)
                .show();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_ADD) {
                Room newRoom = (Room) data.getSerializableExtra("new_room");
                if (newRoom != null) {
                    roomList.add(newRoom);
                    adapter.notifyItemInserted(roomList.size() - 1);
                    Toast.makeText(this, R.string.success_added, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_EDIT) {
                Room updatedRoom = (Room) data.getSerializableExtra("updated_room");
                int position = data.getIntExtra("position", -1);
                if (updatedRoom != null && position >= 0) {
                    roomList.set(position, updatedRoom);
                    adapter.notifyItemChanged(position);
                    Toast.makeText(this, R.string.success_updated, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
```

> ⚠️ **CHÚ Ý:** Room.java sẽ được modify để implement Serializable:

```java
// Sửa lại Room.java (do Trang)
package com.example.miniapp.model;

import java.io.Serializable;

public class Room implements Serializable {
    // ... giữ nguyên
}
```

**Commit:**
```bash
git add java/com/example/miniapp/ui/MainActivity.java
git commit -m "feat(hoa): implement MainActivity"
git push origin hoa
```

---

#### **res/layout/activity_main.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@color/background_main">
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="@dimen/margin_medium"
        android:background="@color/primary">
        
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="@string/title_room_list"
            android:textSize="@dimen/text_size_title"
            android:textColor="@color/text_white"
            android:textStyle="bold" />
        
        <Button
            android:id="@+id/btn_add_room"
            android:layout_width="wrap_content"
            android:layout_height="@dimen/button_height"
            android:text="@string/btn_add_room"
            android:background="@color/button_primary"
            android:textColor="@color/text_white" />
    </LinearLayout>
    
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_room_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/background_secondary" />
</LinearLayout>
```

**Commit:**
```bash
git add res/layout/activity_main.xml
git commit -m "feat(hoa): add activity_main layout"
git push origin hoa
```

---

#### **java/com/example/miniapp/ui/AddRoomActivity.java**

```java
package com.example.miniapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniapp.R;
import com.example.miniapp.model.Room;

public class AddRoomActivity extends AppCompatActivity {
    
    private EditText etRoomId, etRoomName, etRoomPrice, etTenantName, etTenantPhone;
    private RadioGroup rgStatus;
    private RadioButton rbAvailable, rbRented;
    private Button btnSave, btnCancel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        
        initViews();
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
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);
        
        // Default: Còn trống
        rbAvailable.setChecked(true);
    }
    
    private void setupListeners() {
        btnSave.setOnClickListener(v -> handleSave());
        btnCancel.setOnClickListener(v -> finish());
    }
    
    private void handleSave() {
        // Validate dữ liệu
        String roomId = etRoomId.getText().toString().trim();
        String roomName = etRoomName.getText().toString().trim();
        String roomPriceStr = etRoomPrice.getText().toString().trim();
        String tenantName = etTenantName.getText().toString().trim();
        String tenantPhone = etTenantPhone.getText().toString().trim();
        
        // Check empty fields
        if (roomId.isEmpty() || roomName.isEmpty() || roomPriceStr.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_field, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Validate price
        double price;
        try {
            price = Double.parseDouble(roomPriceStr);
            if (price <= 0) {
                Toast.makeText(this, R.string.error_invalid_price, Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.error_invalid_price, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Validate phone nếu được chọn là "Đã thuê"
        boolean isRented = rbRented.isChecked();
        if (isRented) {
            if (tenantName.isEmpty() || tenantPhone.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_field, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidPhone(tenantPhone)) {
                Toast.makeText(this, R.string.error_invalid_phone, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        
        // Tạo Room object
        Room newRoom = new Room(roomId, roomName, price, isRented, tenantName, tenantPhone);
        
        // Return result
        Intent resultIntent = new Intent();
        resultIntent.putExtra("new_room", newRoom);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    
    private boolean isValidPhone(String phone) {
        return phone.length() >= 10 && phone.matches("[0-9]+");
    }
}
```

**Commit:**
```bash
git add java/com/example/miniapp/ui/AddRoomActivity.java
git commit -m "feat(hoa): implement AddRoomActivity"
git push origin hoa
```

---

#### **res/layout/activity_add_room.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="@dimen/margin_medium"
    android:background="@color/background_main">
    
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/title_add_room"
        android:textSize="@dimen/text_size_title"
        android:textColor="@color/text_primary"
        android:textStyle="bold"
        android:marginBottom="@dimen/margin_large" />
    
    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">
        
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            
            <!-- Mã phòng -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Mã phòng"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_room_id"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_room_id"
                android:inputType="text"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Tên phòng -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Tên phòng"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_room_name"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_room_name"
                android:inputType="text"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Giá thuê -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Giá thuê/tháng"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_room_price"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_room_price"
                android:inputType="number"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Tình trạng -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@string/label_status"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <RadioGroup
                android:id="@+id/rg_status"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:marginBottom="@dimen/margin_medium">
                
                <RadioButton
                    android:id="@+id/rb_available"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/status_available"
                    android:textColor="@color/text_primary" />
                
                <RadioButton
                    android:id="@+id/rb_rented"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/status_rented"
                    android:textColor="@color/text_primary" />
            </RadioGroup>
            
            <!-- Tên người thuê -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Tên người thuê"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_tenant_name"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_tenant_name"
                android:inputType="text"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Số điện thoại -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Số điện thoại"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_tenant_phone"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_tenant_phone"
                android:inputType="phone"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
        </LinearLayout>
    </ScrollView>
    
    <!-- Buttons -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:marginTop="@dimen/margin_large">
        
        <Button
            android:id="@+id/btn_save"
            android:layout_width="0dp"
            android:layout_height="@dimen/button_height"
            android:layout_weight="1"
            android:text="@string/btn_save"
            android:background="@color/button_primary"
            android:textColor="@color/text_white"
            android:marginEnd="@dimen/margin_small" />
        
        <Button
            android:id="@+id/btn_cancel"
            android:layout_width="0dp"
            android:layout_height="@dimen/button_height"
            android:layout_weight="1"
            android:text="@string/btn_cancel"
            android:background="@color/border_dark"
            android:textColor="@color/text_white"
            android:marginStart="@dimen/margin_small" />
    </LinearLayout>
</LinearLayout>
```

> ⚠️ **CHÚ Ý:** Hoa cần tạo drawable resource cho EditText background:

#### **res/drawable/edit_text_bg.xml** (cần tạo)

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="@color/background_secondary" />
    <stroke android:width="1dp" android:color="@color/border_light" />
    <corners android:radius="@dimen/corner_radius" />
</shape>
```

**Commit:**
```bash
git add res/layout/activity_add_room.xml
git add res/drawable/edit_text_bg.xml
git commit -m "feat(hoa): add AddRoomActivity layout and resources"
git push origin hoa
```

---

### ✅ Hoa hoàn thành!

Sau khi hoàn thành, Hoa push branch `hoa` lên repo và thông báo cho Trang để merge.

---

## Thao — EditRoomActivity + AndroidManifest

### ✅ Nhiệm vụ

Viết **EditRoomActivity** (sửa thông tin phòng) và cập nhật **AndroidManifest.xml** (khai báo các Activity).

### 📋 Files cần tạo

| File | Loại | Mô tả |
|------|------|-------|
| java/com/example/miniapp/ui/EditRoomActivity.java | Java | Activity sửa thông tin phòng |
| res/layout/activity_edit_room.xml | XML | Layout form sửa phòng |
| AndroidManifest.xml | XML | Thêm khai báo các <activity> tags |

### 🔧 Chi tiết từng file

#### **java/com/example/miniapp/ui/EditRoomActivity.java**

```java
package com.example.miniapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniapp.R;
import com.example.miniapp.model.Room;

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
        String roomId = intent.getStringExtra("room_id");
        String roomName = intent.getStringExtra("room_name");
        double roomPrice = intent.getDoubleExtra("room_price", 0);
        boolean isRented = intent.getBooleanExtra("room_rented", false);
        String tenantName = intent.getStringExtra("tenant_name");
        String tenantPhone = intent.getStringExtra("tenant_phone");
        position = intent.getIntExtra("position", -1);
        
        etRoomId.setText(roomId);
        etRoomId.setEnabled(false); // Không cho sửa mã phòng
        etRoomName.setText(roomName);
        etRoomPrice.setText(String.valueOf((int) roomPrice));
        etTenantName.setText(tenantName != null ? tenantName : "");
        etTenantPhone.setText(tenantPhone != null ? tenantPhone : "");
        
        if (isRented) {
            rbRented.setChecked(true);
        } else {
            rbAvailable.setChecked(true);
        }
    }
    
    private void setupListeners() {
        btnUpdate.setOnClickListener(v -> handleUpdate());
        btnCancel.setOnClickListener(v -> finish());
    }
    
    private void handleUpdate() {
        // Validate dữ liệu
        String roomId = etRoomId.getText().toString().trim();
        String roomName = etRoomName.getText().toString().trim();
        String roomPriceStr = etRoomPrice.getText().toString().trim();
        String tenantName = etTenantName.getText().toString().trim();
        String tenantPhone = etTenantPhone.getText().toString().trim();
        
        // Check empty fields
        if (roomName.isEmpty() || roomPriceStr.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_field, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Validate price
        double price;
        try {
            price = Double.parseDouble(roomPriceStr);
            if (price <= 0) {
                Toast.makeText(this, R.string.error_invalid_price, Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.error_invalid_price, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Validate phone nếu được chọn là "Đã thuê"
        boolean isRented = rbRented.isChecked();
        if (isRented) {
            if (tenantName.isEmpty() || tenantPhone.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_field, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidPhone(tenantPhone)) {
                Toast.makeText(this, R.string.error_invalid_phone, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        
        // Tạo Room object cập nhật
        Room updatedRoom = new Room(roomId, roomName, price, isRented, tenantName, tenantPhone);
        
        // Return result
        Intent resultIntent = new Intent();
        resultIntent.putExtra("updated_room", updatedRoom);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    
    private boolean isValidPhone(String phone) {
        return phone.length() >= 10 && phone.matches("[0-9]+");
    }
}
```

**Commit:**
```bash
git add java/com/example/miniapp/ui/EditRoomActivity.java
git commit -m "feat(thao): implement EditRoomActivity"
git push origin thao
```

---

#### **res/layout/activity_edit_room.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="@dimen/margin_medium"
    android:background="@color/background_main">
    
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/title_edit_room"
        android:textSize="@dimen/text_size_title"
        android:textColor="@color/text_primary"
        android:textStyle="bold"
        android:marginBottom="@dimen/margin_large" />
    
    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">
        
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            
            <!-- Mã phòng (không cho sửa) -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Mã phòng"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_room_id"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_room_id"
                android:inputType="text"
                android:enabled="false"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Tên phòng -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Tên phòng"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_room_name"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_room_name"
                android:inputType="text"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Giá thuê -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Giá thuê/tháng"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_room_price"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_room_price"
                android:inputType="number"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Tình trạng -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@string/label_status"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <RadioGroup
                android:id="@+id/rg_status"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:marginBottom="@dimen/margin_medium">
                
                <RadioButton
                    android:id="@+id/rb_available"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/status_available"
                    android:textColor="@color/text_primary" />
                
                <RadioButton
                    android:id="@+id/rb_rented"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/status_rented"
                    android:textColor="@color/text_primary" />
            </RadioGroup>
            
            <!-- Tên người thuê -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Tên người thuê"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_tenant_name"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_tenant_name"
                android:inputType="text"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
            
            <!-- Số điện thoại -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Số điện thoại"
                android:textSize="@dimen/text_size_normal"
                android:textColor="@color/text_primary"
                android:textStyle="bold"
                android:marginBottom="@dimen/margin_small" />
            
            <EditText
                android:id="@+id/et_tenant_phone"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:hint="@string/hint_tenant_phone"
                android:inputType="phone"
                android:padding="@dimen/margin_medium"
                android:background="@drawable/edit_text_bg"
                android:textColor="@color/text_primary"
                android:textColorHint="@color/text_light"
                android:marginBottom="@dimen/margin_medium" />
        </LinearLayout>
    </ScrollView>
    
    <!-- Buttons -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:marginTop="@dimen/margin_large">
        
        <Button
            android:id="@+id/btn_update"
            android:layout_width="0dp"
            android:layout_height="@dimen/button_height"
            android:layout_weight="1"
            android:text="@string/btn_update"
            android:background="@color/button_primary"
            android:textColor="@color/text_white"
            android:marginEnd="@dimen/margin_small" />
        
        <Button
            android:id="@+id/btn_cancel"
            android:layout_width="0dp"
            android:layout_height="@dimen/button_height"
            android:layout_weight="1"
            android:text="@string/btn_cancel"
            android:background="@color/border_dark"
            android:textColor="@color/text_white"
            android:marginStart="@dimen/margin_small" />
    </LinearLayout>
</LinearLayout>
```

**Commit:**
```bash
git add res/layout/activity_edit_room.xml
git commit -m "feat(thao): add EditRoomActivity layout"
git push origin thao
```

---

#### **AndroidManifest.xml**

Thêm 3 `<activity>` tags cho các Activity:

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MiniApp"
        tools:targetApi="31">

        <activity
            android:name=".ui.MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity
            android:name=".ui.AddRoomActivity"
            android:exported="true" />

        <activity
            android:name=".ui.EditRoomActivity"
            android:exported="true" />

    </application>

</manifest>
```

**Commit:**
```bash
git add AndroidManifest.xml
git commit -m "feat(thao): add activity declarations in manifest"
git push origin thao
```

---

### ✅ Thao hoàn thành!

Sau khi hoàn thành, Thao push branch `thao` lên repo và thông báo cho Trang để merge.

---

## MERGE — Trang thực hiện (sau khi Hoa & Thao đã push)

> ⚠️ **Trang chỉ bắt đầu merge SAU KHI:**
> 1. Hoàn thành code phần của mình
> 2. Nhận confirm từ Hoa & Thao đã push xong branch của họ
> 3. Cả 3 branch đều build success trên máy của họ

### Bước 1: Setup trên máy Trang

```bash
# Đảm bảo code của Trang đã commit & push hết
cd MiniApp
git status
# → phải clean (không có uncommitted changes)

# Pull latest version
git pull origin main
git pull origin trang
```

### Bước 2: Merge các branch

```bash
# Checkout về main
git checkout main
git pull origin main

# Merge trang (branch của mình)
git merge trang --no-ff -m "merge: trang - Adapter + Model + Resources"

# Merge hoa
git merge hoa --no-ff -m "merge: hoa - MainActivity + AddRoomActivity"

# Merge thao
git merge thao --no-ff -m "merge: thao - EditRoomActivity + AndroidManifest"
```

### Bước 3: Resolve conflicts (nếu có)

Nếu gặp conflict, git sẽ báo:
```
CONFLICT (content): Merge conflict in [file_name]
```

**Cách xử lý:**

| File | Khả năng conflict | Cách resolve |
|------|------------------|--------------|
| AndroidManifest.xml | VẤN ĐỀ — mỗi người thêm `<activity>` tag | Giữ TẤT CẢ các `<activity>` tag của cả 3 người, xóa conflict markers `<<<<`, `====`, `>>>>` |
| strings.xml | VẤN ĐỀ — nếu 2 người thêm string cùng lúc | Giữ TẤT CẢ các `<string>` tag, xóa markers |
| build.gradle | Không nên xảy ra nếu không ai sửa | Nếu có: giữ bản gốc, xóa markers |

**Ví dụ resolve conflict AndroidManifest.xml:**

```xml
<!-- Trước (có markers) -->
<<<<<<< HEAD
<activity android:name=".ui.MainActivity" />
=======
<activity android:name=".ui.AddRoomActivity" />
>>>>>>> hoa

<!-- Sau (cleanup) -->
<activity android:name=".ui.MainActivity" />
<activity android:name=".ui.AddRoomActivity" />
<activity android:name=".ui.EditRoomActivity" />
```

Sau khi fix conflict:
```bash
git add [file_conflict]
git commit -m "resolve: merge conflicts"
```

### Bước 4: Build & test

```bash
# Build project
./gradlew clean build
# hoặc từ Android Studio: Build → Make Project

# Chạy app
./gradlew installDebug  # hoặc Run từ AS
```

**Nếu build fail:**
- Check error message
- Tìm file lỗi
- Thông báo cho Hoa/Thao để fix

**Nếu build success:**
- Tiến hành Push

### Bước 5: Push lên main

```bash
# Đảm bảo main branch clean
git status
# → phải clean

# Push
git push origin main

# Verify
git log --oneline | head -10
# → phải thấy 3 commit merge
```

---

## Các file CÓ THỂ conflict và cách xử lý

| File | Lý do conflict | Cách resolve |
|------|----------------|--------------|
| **AndroidManifest.xml** | Mỗi người thêm `<activity>` tag cho Activity của mình | GIỮ TẤT CẢ, không xóa ai |
| **res/values/strings.xml** | Hoa & Thao có thể thêm string riêng mà không xác nhân trước | GIỮ TẤT CẢ, sắp xếp theo thứ tự |
| **build.gradle** | Thường không conflict nếu không ai sửa dependency | Giữ bản main |

**QUY TẮC RESOLVE CHUNG:**
- Khi thấy conflict marker (`<<<<<<<`, `=======`, `>>>>>>>`):
  - ĐỌCD (luôn GIỮ cả 2 phần nếu là tag/resource)
  - Xóa markers
  - Đảm bảo XML/Java hợp lệ (balanced tags, no syntax error)
  - Git add + commit

---

## YÊU CẦU QUAN TRỌNG

1. ✅ **PROJECT.md** phải đủ chi tiết để mỗi người code điểm xác định (không đổi): Package, ID, Color, String...
2. ✅ **PLAN.md** phải đủ để mỗi người CHỈ ĐỌC phần mình, copy-paste code, modify & test → build success
3. ✅ **Ưu tiên code đơn giản nhất:**
   - findViewById (KHÔNG DataBinding)
   - startActivityForResult (KHÔNG LiveData)
   - ArrayList (KHÔNG Room)
   - Validation: if/try-catch (KHÔNG ViewModel)
4. ✅ **Git teamwork:**
   - Mỗi người 1 branch riêng — KHÔNG chạm vào code người khác
   - Commit thường xuyên vào branch của mình
   - Push lên repo NGAY SAU KHI xong 1 feature
   - Merge là bước cuối, do Trang thực hiện duy nhất
5. ✅ **Build & test:**
   - Mỗi người build check trên máy mình trước khi push
   - Merge xong phải build success toàn bộ project
   - KHÔNG conflict code, KHÔNG error

---

## Quy trình tóm tắt (Quick Start)

```
Bước 1 (Mỗi người): Setup & create branch
  $ git checkout -b [trang/hoa/thao]
  $ Gradle sync, build check

Bước 2 (Trang): Commit resource files + Model/Adapter trước
  $ git commit -m "feat(...)"
  $ git push origin trang

Bước 3 (Hoa, Thao): Code trên branch của mình, push
  $ git commit -m "feat(...)"
  $ git push origin [hoa/thao]

Bước 4 (Cả 3): Thông báo cho Trang đã push xong

Bước 5 (Trang): Merge tất cả vào main
  $ git checkout main
  $ git merge trang --no-ff
  $ git merge hoa --no-ff
  $ git merge thao --no-ff
  $ [Resolve conflicts nếu có]
  $ git push origin main

Bước 6 (Cả 3): Pull main, chạy app final test
  $ git pull origin main
  $ ./gradlew clean build
  $ [Run app]  ✅ DONE!
```

