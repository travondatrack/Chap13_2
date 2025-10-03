# Deploy User Admin App lên Render

## Bước 1: Chuẩn bị files cần thiết

Đảm bảo các files sau có trong repository:

- `Dockerfile` ✓
- `docker-entrypoint.sh` ✓
- `render.yaml` ✓
- `.env.example` ✓
- `README.md` ✓

## Bước 2: Push code lên Git

```bash
git add .
git commit -m "Add Docker support and Render deployment config"
git push origin main
```

## Bước 3: Tạo Database trên Render

1. Đăng nhập [Render Dashboard](https://dashboard.render.com/)
2. Click **New +** → **PostgreSQL** (hoặc sử dụng external MySQL)
3. Nhập thông tin:
   - **Name**: `userAdmin-db`
   - **Database**: `murach`
   - **User**: `root`
   - **Region**: chọn gần nhất
4. Click **Create Database**
5. Lưu lại **Internal Database URL** và **External Database URL**

## Bước 4: Deploy Web Service

### Cách 1: Sử dụng Blueprint (Khuyến nghị)

1. Click **New +** → **Blueprint**
2. Connect GitHub repository
3. Chọn repository chứa code
4. Render sẽ đọc `render.yaml` và tự động tạo service

### Cách 2: Manual Deploy

1. Click **New +** → **Web Service**
2. Connect repository
3. Cấu hình:
   - **Name**: `ch12-user-admin`
   - **Environment**: `Docker`
   - **Branch**: `main`
   - **Build Command**: để trống
   - **Start Command**: để trống (dùng Dockerfile CMD)

## Bước 5: Cấu hình Environment Variables

Trong **Environment** tab, thêm các biến:

| Key           | Value                                                                                               | Type   |
| ------------- | --------------------------------------------------------------------------------------------------- | ------ |
| `DB_URL`      | `jdbc:mysql://YOUR_DB_HOST:3306/murach?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=UTC` | Secret |
| `DB_USERNAME` | `root`                                                                                              | Secret |
| `DB_PASSWORD` | `your_password`                                                                                     | Secret |

**Lưu ý**: Thay `YOUR_DB_HOST` bằng:

- Nếu dùng external MySQL: IP/hostname của database server
- Nếu dùng Render PostgreSQL: cần convert sang MySQL hoặc update code

## Bước 6: Tạo Database Schema

Kết nối tới database và chạy:

```sql
-- Từ file create_user_table.sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE
);

-- Thêm dữ liệu mẫu
INSERT INTO users (first_name, last_name, email) VALUES
('John', 'Smith', 'john@murach.com'),
('Andrea', 'Steelman', 'andrea@murach.com'),
('Joel', 'Murach', 'joel@murach.com');
```

## Bước 7: Deploy và Kiểm tra

1. Click **Deploy Latest Commit** hoặc **Manual Deploy**
2. Theo dõi build logs
3. Sau khi deploy thành công, truy cập URL được cung cấp
4. Test đường dẫn: `https://your-app.onrender.com/userAdmin`

## Troubleshooting

### Nếu build failed:

- Kiểm tra Dockerfile syntax
- Xem build logs để tìm lỗi cụ thể

### Nếu runtime error:

- Kiểm tra environment variables
- Xem service logs trong Render dashboard
- Đảm bảo database accessible từ Render

### Nếu database connection failed:

- Kiểm tra DB_URL format
- Verify database credentials
- Test kết nối từ external tool

## Ghi chú

- Render free tier có giới hạn 750 giờ/tháng
- Service sẽ sleep sau 15 phút không hoạt động
- Database có thể bị xóa sau 90 ngày (free tier)
