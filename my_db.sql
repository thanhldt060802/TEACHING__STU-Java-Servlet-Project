-- Bảng người dùng
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    hashed_password TEXT NOT NULL,
    address VARCHAR(255) NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO users (full_name, email, username, hashed_password, address, role_name, created_at) VALUES
('Lê Minh Quân', 'leminhquan01@example.com', 'leminhquan01', '123', '123 Lê Lợi, Hà Nội', 'ADMIN', '2024-01-05 09:00:00'), -- id: 1
('Võ Thị Kim Oanh', 'vokimoanh07@example.com', 'vokimoanh07', '123', '88 Hùng Vương, TP.HCM', 'CUSTOMER', '2024-01-03 10:00:00'), -- id: 2
('Nguyễn Hữu Tài', 'nguyentai08@example.com', 'nguyentai08', '123', '54 Điện Biên Phủ, Đà Nẵng', 'CUSTOMER', '2024-01-14 14:10:00'), -- id: 3
('Đặng Thị Hằng', 'danghang09@example.com', 'danghang09', '123', '76 Nguyễn Trãi, Hà Nội', 'CUSTOMER', '2024-01-26 12:00:00'), -- id: 4
('Hoàng Văn Tuấn', 'hoangtuan10@example.com', 'hoangtuan10', '123', '33 Trần Phú, Huế', 'CUSTOMER', '2024-02-01 15:30:00'), -- id: 5
('Phan Thị Nhung', 'phannhung11@example.com', 'phannhung11', '123', '23 Lê Thánh Tông, TP.HCM', 'CUSTOMER', '2024-02-12 13:00:00'), -- id: 6
('Nguyễn Văn Toàn', 'nguyentoan12@example.com', 'nguyentoan12', '123', '18 Hòa Bình, Cần Thơ', 'CUSTOMER', '2024-02-19 17:00:00'); -- id: 7