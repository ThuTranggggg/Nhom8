# PROJECT.md - ROOM RENTAL MANAGEMENT APP

## 1. Tổng quan dự án & Phân công

**Tên app:** Room Rental Management App  
**Mô tả:** Ứng dụng giúp chủ nhà trọ quản lý danh sách phòng trọ, thông tin người thuê và tình trạng phòng.

**Phân công teamwork (cân bằng):**

| Người | Responsibility | Files | % Công việc |
|-------|----------------|-------|-----------|
| **Trang** | Core models, adapters, resources | Model, Adapter, Colors/Strings/Dimens, item_room layout | ~35% |
| **Hoa** | Main UI & Add functionality  | MainActivity + layout, AddRoomActivity + layout, EditText drawable | ~35% |
| **Thao** | Edit UI & Manifest | EditRoomActivity + layout, AndroidManifest | ~30% |

**Stack:**
```
android {
    namespace 'com.example.miniapp'
    compileSdk 36
    
    defaultConfig {
        applicationId "com.example.miniapp"
        minSdk 24
        targetSdk 36
        versionCode 1
        versionName "1.0"
    }
}

dependencies:
- androidx.appcompat:appcompat
- com.google.android.material:material
- androidx.activity:activity
- androidx.constraintlayout:constraintlayout
```

---

## 2. Cấu trúc thư mục & Phân công Files

```
app/src/main/

├── AndroidManifest.xml (Thao)

├── java/com/example/miniapp/
│
│   ├── ui/
│   │   ├── MainActivity.java (Hoa)
│   │   ├── AddRoomActivity.java (Hoa)
│   │   └── EditRoomActivity.java (Thao)
│   │
│   ├── adapter/
│   │   └── RoomAdapter.java (Trang)
│   │
│   ├── model/
│   │   └── Room.java (Trang)
│   │
│   └── util/
│       └── Constants.java
│
└── res/
    ├── layout/
    │   ├── activity_main.xml (Hoa)
    │   ├── activity_add_room.xml (Hoa)
    │   ├── activity_edit_room.xml (Thao)
    │   └── item_room.xml (Trang)
    │
    ├── drawable/
    │   ├── ic_launcher_background.xml
    │   ├── ic_launcher_foreground.xml
    │   └── edit_text_bg.xml (Hoa)
    │
    └── values/
        ├── colors.xml (Trang)
        ├── strings.xml (Trang)
        ├── dimens.xml (Trang)
        └── themes.xml
```

---

## 3. Design Tokens — colors.xml

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

---

## 4. strings.xml

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

---

## 5. dimens.xml

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

**⚠️ CHÚ Ý:** item_room_height = 200dp (để chứa buttons Edit/Delete ở phía dưới)

---

## 6. Danh sách Screen & File (với phân công)

| Screen | Activity | Layout file | Người làm | Mô tả |
|--------|----------|-------------|----------|-------|
| Danh sách phòng | MainActivity (Hoa) | activity_main.xml (Hoa) | **Hoa** | Hiển thị RecyclerView danh sách phòng + nút Add |
| Thêm phòng | AddRoomActivity (Hoa) | activity_add_room.xml (Hoa) | **Hoa** | Form thêm phòng mới (ID, Tên, Giá, Tình trạng, Người thuê, SĐT) |
| Sửa phòng | EditRoomActivity (Thao) | activity_edit_room.xml (Thao) | **Thao** | Form sửa phòng (pre-fill dữ liệu) |
| Item phòng | - | item_room.xml (Trang) | **Trang** | Mỗi item trong RecyclerView hiển thị Tên, Giá, Tình trạng, nút Edit/Delete |

---

## 7. Shared Layouts & Adapters (Core - do Trang)

| File | Loại | Người làm | Mô tả |
|------|------|----------|-------|
| item_room.xml | Layout | **Trang** | Item room trong RecyclerView: Tên phòng, Giá, Tình trạng (tô màu), nút Edit/Delete |
| RoomAdapter.java | Adapter | **Trang** | extends RecyclerView.Adapter<RoomAdapter.ViewHolder>, bind dữ liệu vào item_room.xml |
| Room.java | Model | **Trang** | Data model POJO với getters/setters, implements Serializable |
| colors.xml | Resource | **Trang** | Định nghĩa tất cả màu được dùng trong app |
| strings.xml | Resource | **Trang** | Định nghĩa tất cả string literals được dùng trong app |
| dimens.xml | Resource | **Trang** | Định nghĩa tất cả dimension (margin, padding, text size, ...) |
| edit_text_bg.xml | Drawable | **Hoa** | Background shape cho EditText trong Add/Edit forms |
| AndroidManifest.xml | XML | **Thao** | Khai báo tất cả activities (MainActivity, AddRoomActivity, EditRoomActivity) |

---

## 8. Data Model

### Room.java

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

---

## 9. Navigation

### Flow
1. **MainActivity** (List)
   - Nhấn "Thêm phòng" → `startActivity(new Intent(MainActivity.this, AddRoomActivity.class))`
   - Nhấn nút Edit trên item → `startActivityForResult(Intent, EDIT_REQUEST_CODE)` → **EditRoomActivity**
   - Long-press item hoặc nút Delete → AlertDialog → xóa khỏi ArrayList → notify Adapter
   
2. **AddRoomActivity**
   - Nhấn "Lưu" → validate dữ liệu → thêm vào ArrayList → `setResult(RESULT_OK)` → `finish()`
   - Nhấn "Hủy" → `finish()` quay lại MainActivity

3. **EditRoomActivity**
   - Nhấn "Cập nhật" → validate dữ liệu → cập nhật ArrayList → `setResult(RESULT_OK)` → `finish()`
   - Nhấn "Hủy" → `finish()` quay lại MainActivity

### Data passing
- **MainActivity → AddRoomActivity:** Không cần (tạo mới)
- **MainActivity → EditRoomActivity:** Truyền Room object hoặc index qua Intent Bundle
- **AddRoomActivity/EditRoomActivity → MainActivity:** Qua onActivityResult() callback

---

## 10. Chi tiết các chức năng CRUD

### Create – Thêm phòng
**Activity:** AddRoomActivity  
**Layout:** activity_add_room.xml  
**Luồng:**
1. Nhập thông tin: Mã phòng, Tên phòng, Giá thuê, Tình trạng, Tên người thuê, SĐT
2. Validate dữ liệu:
   - Tất cả field bắt buộc (không để trống)
   - Giá phải là số dương
   - SĐT phải ≥ 10 số nếu chọn "Đã thuê"
3. Thêm vào ArrayList (MainActivity)
4. `setResult(RESULT_OK)` + `finish()`
5. MainActivity nhận kết quả → `adapter.notifyItemInserted()` → Toast success

### Read – Hiển thị danh sách phòng
**Activity:** MainActivity  
**Layout:** activity_main.xml (RecyclerView) + item_room.xml (item)
**Luồng:**
1. Khởi động app → MainActivity
2. RecyclerView hiển thị danh sách phòng từ ArrayList
3. Mỗi item hiển thị:
   - Tên phòng (bold, large)
   - Giá thuê (secondary text)
   - Tình trạng (Còn trống/Đã thuê) với màu động
   - Tên người thuê (chỉ hiển thị khi đã thuê)
   - 2 buttons: Edit, Delete

### Update – Sửa thông tin phòng
**Activity:** EditRoomActivity  
**Layout:** activity_edit_room.xml  
**Luồng:**
1. Nhấn nút Edit trên item → MainActivity gửi dữ liệu Room qua Intent
2. EditRoomActivity pre-fill form với dữ liệu Room
3. Mã phòng DISABLED (không cho sửa)
4. Sửa thông tin → Validate → `setResult(RESULT_OK)` → `finish()`
5. MainActivity nhận → Update ArrayList → `adapter.notifyItemChanged()` → Toast success

### Delete – Xóa phòng
**Activity:** MainActivity  
**Luồng:**
1. Nhấn nút Delete trên item
2. AlertDialog xác nhận "Bạn chắc chắn muốn xóa phòng này?"
3. Confirm → Xóa khỏi ArrayList → `adapter.notifyItemRemoved()` → Toast success
4. Hủy → Đóng dialog không làm gì

---

## 11. Item Layout - item_room.xml (Chi tiết thiết kế)

**Height:** 200dp (để fit content + buttons)

**Cấu trúc:**
```
┌─────────────────────────────────────┐
│ Phòng A101                  [bold]  │ (15%)
├─────────────────────────────────────┤
│ Giá: 3.000.000 VND                  │ (10%)
├─────────────────────────────────────┤
│ Còn trống (màu xanh)     [bold]    │ (10%)
├─────────────────────────────────────┤
│ Người thuê: Nguyễn Văn A            │ (10%, ẩn mặc định)
├─────────────────────────────────────┤
│                                     │ (45% - spacer)
├─────────────────────────────────────┤
│ [  Sửa   |  Xóa  ]                  │ (10%)
└─────────────────────────────────────┘
```

**Thành phần:**
1. **Top LinearLayout (weight=1):** Chứa text info
   - TextView: room name (18sp, bold, primary color)
   - TextView: price (16sp, secondary color)
   - TextView: status (16sp, bold, color động)
   - TextView: tenant name (14sp, secondary color, visibility gone by default)

2. **Spacer View (weight=1):** Đẩy buttons xuống dưới

3. **Bottom LinearLayout:** Chứa 2 buttons
   - Button Edit (0.5 width, primary color)
   - Button Delete (0.5 width, danger color)

**Styling:**
- Background: @drawable/item_room_bg hoặc color/background_main
- Elevation: 4dp (shadow effect)
- Padding: margin_medium
- Margin ngoài: margin_medium

---

## 12. Drawable Resources

### edit_text_bg.xml (NEW)
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="@color/background_secondary" />
    <stroke android:width="1dp" android:color="@color/border_light" />
    <corners android:radius="@dimen/corner_radius" />
</shape>
```
**Dùng cho:** EditText background trong AddRoomActivity & EditRoomActivity

### item_room_bg.xml (OPTIONAL)
Nếu muốn card effect:
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="@color/background_main" />
    <stroke android:width="1dp" android:color="@color/border_light" />
    <corners android:radius="@dimen/corner_radius" />
</shape>
```
Hoặc có thể bỏ qua và dùng elevation thay vì drawable background.

---

## 13. Validation Rules

### AddRoomActivity & EditRoomActivity

**Bắt buộc:**
- [ ] Mã phòng: Không để trống
- [ ] Tên phòng: Không để trống
- [ ] Giá thuê: Không để trống + phải > 0
- [ ] Nếu chọn "Đã thuê":
  - [ ] Tên người thuê: Không để trống
  - [ ] SĐT: Không để trống + ≥ 10 digits

**Thông báo lỗi:**
- "Không được để trống" → Toast (red)
- "Giá phải là số dương" → Toast (red)
- "Số điện thoại không hợp lệ" → Toast (red)

**Thông báo thành công:**
- "Thêm phòng thành công" (Add)
- "Cập nhật phòng thành công" (Edit)
- "Xóa phòng thành công" (Delete)

---

## 14. Sample Data (Hardcode trong MainActivity)

```java
roomList.add(new Room("P001", "Phòng A101", 3000000, false, "", ""));
roomList.add(new Room("P002", "Phòng A102", 3000000, true, "Nguyễn Văn A", "0912345678"));
roomList.add(new Room("P003", "Phòng A103", 2500000, false, "", ""));
roomList.add(new Room("P004", "Phòng B201", 3500000, true, "Trần Thị B", "0987654321"));
```

---

## 15. Git Workflow

**Branch Strategy:**
- Main: `main` (release branch)
- Dev: `trang`, `hoa`, `thao` (feature branches)

**Merge:**
```bash
# Trang merge Hoa & Thao, then push to main
git merge hoa --no-ff
git merge thao --no-ff
git checkout main
git merge trang --no-ff
git push origin main
```

---

## 16. Testing Checklist

- [ ] App launches không crash
- [ ] Main activity shows RecyclerView with 4 sample rooms
- [ ] Add button opens AddRoomActivity
- [ ] Add new room: validate, save, return to main, refresh list
- [ ] Edit button on item: pre-fill form, update, return to main, refresh list
- [ ] Delete button on item: confirm dialog, delete, return to main, remove item
- [ ] Status color changes (green for available, red for rented)
- [ ] Tenant name only shows when rented
- [ ] All EditText fields require input (no empty on submit)
- [ ] Phone validation works (≥ 10 digits)
- [ ] Toast messages show correctly (Add, Edit, Delete, Error)

---

## 17. File Checklist

**Java:**
- ✅ MainActivity.java
- ✅ AddRoomActivity.java
- ✅ EditRoomActivity.java
- ✅ RoomAdapter.java
- ✅ Room.java (Model)

**XML - Layouts:**
- ✅ activity_main.xml
- ✅ activity_add_room.xml
- ✅ activity_edit_room.xml
- ✅ item_room.xml

**XML - Resources:**
- ✅ colors.xml
- ✅ strings.xml
- ✅ dimens.xml

**XML - Drawables:**
- ✅ edit_text_bg.xml
- ✅ (optional) item_room_bg.xml

**Android:**
- ✅ AndroidManifest.xml (with activity declarations)
**Luồng:**
1. **DataSource:** ArrayList<Room> hardcoded 4 phòng mẫu
   - P001 (Phòng A101, 3M, Còn trống)
   - P002 (Phòng A102, 3M, Đã thuê - Nguyễn Văn A)
   - P003 (Phòng A103, 2.5M, Còn trống)
   - P004 (Phòng B201, 3.5M, Đã thuê - Trần Thị B)

2. **RecyclerView Setup:**
   - Adapter: RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>
   - LayoutManager: LinearLayoutManager (vertical)
   - Item count: roomList.size()

3. **Mỗi item hiển thị:**
   - **Tên phòng** (tv_room_name): textSize large, bold, text_primary color
   - **Giá thuê** (tv_room_price): format "Giá: XXX,XXX VND", text_secondary color
   - **Tình trạng** (tv_room_status): 
     - Nếu `isRented = true` → "Đã thuê" + status_rented color (#F44336 - đỏ)
     - Nếu `isRented = false` → "Còn trống" + status_available color (#4CAF50 - xanh)
   - **Tên người thuê** (tv_tenant_name): "Người thuê: Tên" (chỉ visible nếu đã thuê)
   - **Nút Edit** (btn_edit): blue (#2196F3), height 48dp, onClick → EditRoomActivity
   - **Nút Delete** (btn_delete): red (#F44336), height 48dp, onClick → AlertDialog confirm

4. **Màu sắc theo tình trạng:**
   ```
   Tình trạng: Còn trống → Tô màu XANH (#4CAF50)
   Tình trạng: Đã thuê   → Tô màu ĐỎ (#F44336)
   ```

### Update – Sửa thông tin phòng
**Activity:** EditRoomActivity  
**Layout:** activity_edit_room.xml  
**Luồng:**
1. MainActivity gửi dữ liệu phòng qua Intent Bundle (room_id, room_name, room_price, room_rented, tenant_name, tenant_phone, position)
2. EditRoomActivity load dữ liệu → pre-fill vào form
3. Mã phòng: disabled (không cho sửa)
4. Validate tương tự Create
5. Cập nhật vào ArrayList tại vị trí cũ
6. `setResult(RESULT_OK)` + truyền updated_room + position
7. MainActivity nhận → `adapter.notifyItemChanged(position)` → Toast update success

### Delete – Xóa phòng
**Trigger:** Nút Delete trong item_room.xml  
**Luồng:**
1. Nhấn nút Delete → AlertDialog xác nhận
   - Title: "Xác nhận xóa"
   - Message: "Bạn chắc chắn muốn xóa phòng này?"
   - Buttons: "Có" (delete) / "Không" (cancel)
2. Nếu "Có" → xóa khỏi ArrayList
3. `adapter.notifyItemRemoved(position)` cập nhật UI
4. Toast: "Xóa phòng thành công"

---

## 12. Convention bắt buộc

- **File Java:** PascalCase (MainActivity.java, RoomAdapter.java, Room.java)
- **File XML layout:** snake_case (activity_main.xml, item_room.xml)
- **Resource ID trong XML:** snake_case (tv_room_name, btn_add_room, rv_room_list)
- **Màu sắc:** KHÔNG hardcode, chỉ dùng `@color/`
- **String hiển thị:** Định nghĩa trong strings.xml, dùng `@string/`
- **Dimens:** Dùng `@dimen/` thay vì hardcode dp/sp
- **Package:** `com.example.miniapp` — không đổi
- **KHÔNG thêm feature ngoài thiết kế**
- **Mỗi Activity là 1 file Java riêng + 1 file layout XML riêng**
- **Adapter đặt trong package:** `com.example.miniapp.adapter`
- **Model đặt trong package:** `com.example.miniapp.model`
- **Activity đặt trong package:** `com.example.miniapp.ui`
