-- Script tạo bảng users phù hợp với cấu trúc trong ảnh
-- Database: murach

USE murach;

-- Xóa bảng cũ nếu tồn tại
DROP TABLE IF EXISTS users;

-- Tạo bảng users mới với cấu trúc đúng
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

-- Thêm dữ liệu mẫu từ ảnh
INSERT INTO users (first_name, last_name, email) VALUES
('John', 'Kahn', 'john.doe@email.com'),
('Jane', 'Smith', 'jane.smith@email.com'),
('Alice', 'Johnson', 'alice.johnson@email.com'),
('Bob', 'Williams', 'bob.williams@email.com'),
('Charlie', 'Brown', 'charlie.brown@email.com');

-- Kiểm tra dữ liệu
SELECT * FROM users;