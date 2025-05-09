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

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO categories (name, created_at) VALUES
('Áo thun', '2024-01-10 10:00:00'), -- id: 1
('Áo sơ mi', '2024-02-15 14:30:00'), -- id: 2
('Quần jeans', '2024-04-05 16:45:00'), -- id: 3
('Quần short', '2024-05-12 11:10:00'), -- id: 4
('Váy đầm', '2024-08-28 15:00:00'), -- id: 5
('Giày thể thao', '2024-06-18 13:50:00'); -- id: 6

-- Bảng thương hiệu sản phẩm
CREATE TABLE brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO brands (name, description, created_at) VALUES
('Nike', 'Thương hiệu nổi tiếng toàn cầu chuyên về giày thể thao và trang phục thể thao.', '2024-01-12 10:00:00'), -- id: 1
('Adidas', 'Thương hiệu thời trang thể thao đến từ Đức, nổi bật với các mẫu giày và áo thể thao.', '2024-02-18 14:30:00'), -- id: 2
('Puma', 'Puma cung cấp sản phẩm thể thao với thiết kế năng động và trẻ trung.', '2024-03-05 09:45:00'), -- id: 3
('Levis', 'Thương hiệu nổi tiếng với các sản phẩm quần jeans chất lượng cao.', '2024-03-25 16:00:00'), -- id: 4
('Zara', 'Zara chuyên về thời trang nhanh, cập nhật xu hướng liên tục với mức giá hợp lý.', '2024-04-10 11:20:00'), -- id: 5
('H&M', 'Thời trang bình dân, đa dạng mẫu mã cho mọi đối tượng và phong cách.', '2024-05-02 13:15:00'); -- id: 6

-- Bảng sản phẩm
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    sex VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    discount_percentage INT NOT NULL,
    stock INT NOT NULL,
    image_url TEXT NOT NULL,
    category_id BIGINT NOT NULL REFERENCES categories(id),
    brand_id BIGINT NOT NULL REFERENCES brands(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO products (name, description, sex, price, discount_percentage, stock, image_url, category_id, brand_id, created_at) VALUES
('Túi xách da', 'Áo khoác mùa đông, giữ ấm tốt cho cơ thể.', 'UNISEX', 430238, 42, 64, 'https://example.com/images/12.jpg', 4, 3, '2024-06-05 00:00:00'), -- id: 1
('Quần dài kaki', 'Áo hoodie thể thao thoải mái.', 'MALE', 285038, 27, 51, 'https://example.com/images/36.jpg', 4, 2, '2024-10-17 00:00:00'), -- id: 2
('Giày boot nam', 'Áo khoác da cao cấp cho mùa lạnh.', 'FEMALE', 948931, 48, 58, 'https://example.com/images/37.jpg', 5, 3, '2024-07-23 00:00:00'), -- id: 3
('Áo polo', 'Giày boot da sang trọng cho mùa đông.', 'FEMALE', 593538, 1, 79, 'https://example.com/images/27.jpg', 6, 2, '2024-01-27 00:00:00'), -- id: 4
('Áo sơ mi công sở', 'Váy đầm dạ hội đẹp và sang trọng.', 'UNISEX', 288631, 32, 88, 'https://example.com/images/66.jpg', 1, 4, '2024-11-12 00:00:00'), -- id: 5
('Áo thun thể thao', 'Áo sơ mi công sở với thiết kế thanh lịch.', 'MALE', 368347, 20, 60, 'https://example.com/images/19.jpg', 2, 1, '2024-08-02 00:00:00'), -- id: 6
('Áo khoác da', 'Áo khoác da cao cấp cho mùa lạnh.', 'UNISEX', 499938, 35, 55, 'https://example.com/images/55.jpg', 3, 7, '2024-09-01 00:00:00'), -- id: 7
('Quần jeans nữ', 'Quần jeans nữ đẹp và dễ phối đồ.', 'FEMALE', 451237, 15, 48, 'https://example.com/images/23.jpg', 4, 4, '2024-07-19 00:00:00'), -- id: 8
('Giày thể thao Nike', 'Giày thể thao Nike, phong cách thể thao, thoải mái.', 'MALE', 631023, 25, 70, 'https://example.com/images/62.jpg', 4, 1, '2024-05-10 00:00:00'), -- id: 9
('Váy đầm dạ hội', 'Váy đầm dạ hội đẹp và sang trọng.', 'FEMALE', 799999, 30, 40, 'https://example.com/images/13.jpg', 8, 9, '2024-03-12 00:00:00'), -- id: 1
('Kính mát thời trang', 'Kính mắt thể thao bảo vệ mắt khỏi ánh sáng mặt trời.', 'UNISEX', 128500, 5, 120, 'https://example.com/images/21.jpg', 5, 5, '2024-10-25 00:00:00'), -- id: 10
('Áo khoác mùa đông', 'Áo khoác mùa đông, giữ ấm tốt cho cơ thể.', 'FEMALE', 850012, 40, 63, 'https://example.com/images/5.jpg', 3, 5, '2024-12-01 00:00:00'), -- id: 11
('Quần jogger', 'Quần jogger thời trang, dễ phối đồ.', 'MALE', 379295, 18, 92, 'https://example.com/images/33.jpg', 4, 1, '2024-05-20 00:00:00'), -- id: 12
('Giày thể thao Adidas', 'Giày thể thao Adidas, bền bỉ và dễ chịu.', 'MALE', 724932, 20, 72, 'https://example.com/images/56.jpg', 6, 2, '2024-06-15 00:00:00'), -- id: 13
('Giày boot da', 'Giày boot da sang trọng cho mùa đông.', 'FEMALE', 883416, 50, 33, 'https://example.com/images/4.jpg', 4, 3, '2024-11-05 00:00:00'), -- id: 14
('Túi xách công sở', 'Túi xách công sở dành cho những người đi làm.', 'UNISEX', 1500000, 18, 70, 'https://example.com/images/76.jpg', 3, 3, '2024-07-22 00:00:00'), -- id: 15
('Váy đầm công sở', 'Váy đầm công sở thanh lịch, dễ phối.', 'FEMALE', 503670, 10, 85, 'https://example.com/images/70.jpg', 3, 1, '2024-06-03 00:00:00'), -- id: 16
('Áo len', 'Áo len ấm áp cho mùa lạnh.', 'MALE', 250237, 15, 61, 'https://example.com/images/19.jpg', 2, 4, '2024-07-10 00:00:00'), -- id: 17
('Áo hoodie', 'Áo hoodie thể thao thoải mái.', 'UNISEX', 345890, 23, 58, 'https://example.com/images/34.jpg', 2, 2, '2024-09-10 00:00:00'), -- id: 18
('Áo khoác bomber', 'Áo khoác bomber phong cách trẻ trung.', 'MALE', 530201, 12, 55, 'https://example.com/images/44.jpg', 5, 2, '2024-05-07 00:00:00'), -- id: 19
('Quần short nữ', 'Quần short nữ thoải mái cho mùa hè.', 'FEMALE', 220450, 30, 79, 'https://example.com/images/3.jpg', 4, 5, '2024-12-04 00:00:00'), -- id: 20
('Quần kaki', 'Quần dài kaki phù hợp với nhiều hoàn cảnh.', 'UNISEX', 499203, 18, 60, 'https://example.com/images/16.jpg', 1, 4, '2024-06-20 00:00:00'), -- id: 21
('Áo khoác vải', 'Áo khoác vải nhẹ cho mùa xuân.', 'MALE', 389786, 30, 80, 'https://example.com/images/28.jpg', 3, 3, '2024-06-10 00:00:00'), -- id: 22
('Giày thể thao Uniqlo', 'Giày thể thao Uniqlo bền và nhẹ.', 'UNISEX', 475389, 22, 67, 'https://example.com/images/50.jpg', 6, 2, '2024-08-15 00:00:00'), -- id: 23
('Váy đầm liền thân', 'Váy đầm liền thân thanh lịch cho cô nàng công sở.', 'FEMALE', 299999, 15, 49, 'https://example.com/images/41.jpg', 6, 5, '2024-07-28 00:00:00'); -- id: 24

-- Bảng chi tiết mặt hàng trong giỏ hàng
CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    quantity INT NOT NULL
);
INSERT INTO cart_items(user_id, product_id, quantity) VALUES
(2, 1, 2), -- id: 1
(2, 2, 1), -- id: 2
(2, 3, 3), -- id: 3
(2, 4, 1), -- id: 4
(3, 5, 2), -- id: 5
(3, 6, 1), -- id: 6
(3, 1, 3), -- id: 7
(3, 2, 2), -- id: 8
(4, 3, 1), -- id: 9
(4, 4, 3), -- id: 10
(4, 5, 2), -- id: 11
(4, 6, 1), -- id: 12
(4, 1, 1), -- id: 13
(5, 2, 2), -- id: 14
(5, 3, 1), -- id: 15
(5, 4, 3), -- id: 16
(5, 5, 2), -- id: 17
(5, 6, 1), -- id: 18
(6, 1, 2), -- id: 19
(6, 2, 1), -- id: 20
(6, 3, 3), -- id: 21
(6, 4, 2), -- id: 22
(7, 5, 1), -- id: 23
(7, 6, 3), -- id: 24
(7, 1, 2), -- id: 25
(7, 2, 1), -- id: 26
(7, 3, 2), -- id: 27
(7, 4, 3), -- id: 28
(7, 5, 1); -- id: 29

-- Bảng hóa đơn
CREATE TABLE invoices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    total_amount BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO invoices(user_id, total_amount, status, created_at) VALUES
(2, 120000, 'DONE', '2024-01-15 10:20:00'), -- id: 1
(2, 80000, 'DONE', '2024-02-02 14:30:00'), -- id: 2
(2, 150000, 'DONE', '2024-03-05 11:45:00'), -- id: 3
(2, 100000, 'DONE', '2024-04-12 16:00:00'), -- id: 4
(3, 130000, 'DONE', '2024-05-22 09:00:00'), -- id: 5
(3, 200000, 'DONE', '2024-01-20 15:00:00'), -- id: 6
(3, 180000, 'DONE', '2024-02-10 12:00:00'), -- id: 7
(3, 220000, 'DONE', '2024-03-25 17:10:00'), -- id: 8
(3, 170000, 'DONE', '2024-04-15 14:50:00'), -- id: 9
(4, 150000, 'DONE', '2024-05-30 16:45:00'), -- id: 10
(4, 190000, 'DONE', '2024-01-25 13:00:00'), -- id: 11
(4, 210000, 'DONE', '2024-02-18 08:20:00'), -- id: 12
(4, 160000, 'DONE', '2024-03-05 10:40:00'), -- id: 13
(4, 140000, 'DONE', '2024-04-10 17:00:00'), -- id: 14
(4, 175000, 'DONE', '2024-05-17 11:35:00'), -- id: 15
(4, 125000, 'DONE', '2024-01-30 11:10:00'), -- id: 16
(5, 160000, 'DONE', '2024-02-25 14:20:00'), -- id: 17
(5, 180000, 'DONE', '2024-03-15 16:55:00'), -- id: 18
(5, 190000, 'DONE', '2024-04-18 13:40:00'), -- id: 19
(5, 210000, 'DONE', '2024-05-25 09:50:00'), -- id: 20
(5, 220000, 'DONE', '2024-01-10 12:30:00'), -- id: 21
(5, 130000, 'DONE', '2024-02-05 15:00:00'), -- id: 22
(5, 160000, 'DONE', '2024-03-20 17:25:00'), -- id: 23
(5, 110000, 'DONE', '2024-04-02 14:10:00'), -- id: 24
(5, 140000, 'DONE', '2024-05-15 08:40:00'), -- id: 25
(6, 180000, 'DONE', '2024-02-12 10:40:00'), -- id: 26
(6, 210000, 'DONE', '2024-03-10 11:50:00'), -- id: 27
(6, 200000, 'DONE', '2024-04-05 13:30:00'), -- id: 28
(6, 170000, 'DONE', '2024-05-22 17:15:00'), -- id: 29
(7, 150000, 'PENDING', '2024-02-22 12:50:00'), -- id: 30
(7, 120000, 'DONE', '2024-03-12 16:00:00'), -- id: 31
(7, 140000, 'PENDING', '2024-04-20 10:25:00'), -- id: 32
(7, 170000, 'CANCEL', '2024-05-07 13:40:00'), -- id: 33
(7, 180000, 'PENDING', '2024-05-25 14:30:00'); -- id: 34

-- Bảng chi tiết hóa đơn
CREATE TABLE invoice_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_id BIGINT NOT NULL REFERENCES invoices(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    price BIGINT NOT NULL,
    discount_percentage INT NOT NULL,
    quantity INT NOT NULL,
    total_price BIGINT NOT NULL
);
INSERT INTO invoice_details(invoice_id, product_id, price, discount_percentage, quantity, total_price) VALUES
(1, 1, 100000, 10, 2, 180000), -- id: 1
(1, 2, 150000, 5, 1, 142500), -- id: 2
(1, 3, 120000, 15, 3, 306000), -- id: 3
(2, 4, 110000, 20, 1, 88000), -- id: 4
(2, 5, 130000, 10, 2, 234000), -- id: 5
(3, 6, 140000, 5, 1, 133000), -- id: 6
(3, 1, 125000, 10, 2, 225000), -- id: 7
(4, 2, 105000, 10, 2, 189000), -- id: 8
(4, 3, 135000, 5, 1, 128250), -- id: 9
(5, 4, 150000, 10, 2, 270000), -- id: 10
(5, 5, 160000, 15, 1, 136000), -- id: 11
(6, 6, 110000, 20, 2, 176000), -- id: 12
(6, 1, 125000, 5, 1, 118750), -- id: 13
(6, 2, 130000, 10, 2, 234000), -- id: 14
(7, 3, 120000, 5, 3, 342000), -- id: 15
(7, 4, 140000, 10, 1, 126000), -- id: 16
(8, 5, 135000, 5, 2, 257250), -- id: 17
(8, 6, 150000, 20, 1, 120000), -- id: 18
(8, 1, 125000, 10, 2, 225000), -- id: 19
(9, 2, 145000, 15, 1, 123250), -- id: 20
(9, 3, 160000, 5, 3, 456000), -- id: 21
(10, 4, 110000, 10, 2, 198000), -- id: 22
(10, 5, 120000, 15, 1, 102000), -- id: 23
(11, 6, 130000, 5, 3, 369000), -- id: 24
(11, 1, 140000, 20, 2, 224000), -- id: 25
(12, 2, 125000, 10, 1, 112500), -- id: 26
(12, 3, 150000, 15, 2, 255000), -- id: 27
(12, 4, 135000, 5, 3, 382500), -- id: 28
(13, 5, 140000, 20, 1, 112000), -- id: 29
(13, 6, 160000, 10, 2, 288000), -- id: 30
(13, 1, 125000, 5, 3, 356250), -- id: 31
(14, 2, 120000, 10, 1, 108000), -- id: 32
(14, 3, 150000, 20, 2, 240000), -- id: 33
(14, 4, 130000, 15, 2, 221000), -- id: 34
(15, 5, 110000, 10, 2, 198000), -- id: 35
(15, 6, 120000, 20, 1, 96000), -- id: 36
(16, 1, 130000, 5, 1, 123500), -- id: 37
(16, 2, 140000, 10, 2, 252000), -- id: 38
(17, 3, 125000, 10, 3, 337500), -- id: 39
(17, 4, 150000, 5, 1, 142500), -- id: 40
(18, 5, 160000, 15, 2, 272000), -- id: 41
(18, 6, 110000, 10, 1, 99000), -- id: 42
(19, 1, 135000, 5, 3, 385500), -- id: 43
(19, 2, 150000, 20, 2, 240000), -- id: 44
(20, 3, 120000, 10, 1, 108000), -- id: 45
(20, 4, 130000, 15, 2, 221000), -- id: 46
(21, 5, 110000, 5, 3, 319500), -- id: 47
(21, 6, 140000, 10, 1, 126000), -- id: 48
(22, 1, 125000, 20, 2, 200000), -- id: 49
(22, 2, 150000, 10, 3, 405000), -- id: 50
(23, 3, 160000, 5, 2, 304000), -- id: 51
(23, 4, 120000, 10, 1, 108000), -- id: 52
(24, 5, 130000, 20, 1, 104000), -- id: 53
(24, 6, 140000, 5, 2, 266000), -- id: 54
(25, 1, 125000, 15, 1, 106250), -- id: 55
(25, 2, 150000, 10, 3, 405000), -- id: 56
(26, 3, 110000, 10, 2, 198000), -- id: 57
(26, 4, 160000, 20, 1, 128000), -- id: 58
(27, 5, 130000, 10, 1, 117000), -- id: 59
(27, 6, 140000, 5, 2, 266000), -- id: 60
(28, 1, 125000, 10, 2, 225000), -- id: 61
(28, 2, 150000, 15, 3, 382500), -- id: 62
(29, 3, 110000, 20, 1, 88000), -- id: 63
(29, 4, 120000, 5, 3, 342000), -- id: 64
(30, 5, 135000, 0, 1, 135000), -- id: 65
(30, 6, 125000, 5, 3, 356250), -- id: 66
(30, 1, 130000, 5, 2, 247000), -- id: 67
(30, 2, 150000, 20, 3, 360000), -- id: 68
(30, 3, 140000, 5, 2, 266000), -- id: 69
(30, 4, 125000, 20, 1, 100000), -- id: 70
(31, 5, 130000, 10, 2, 234000), -- id: 71
(31, 6, 140000, 15, 1, 119000), -- id: 72
(31, 1, 150000, 0, 1, 150000), -- id: 73
(31, 2, 120000, 20, 2, 192000), -- id: 74
(32, 3, 125000, 5, 3, 356250), -- id: 75
(32, 4, 145000, 10, 1, 130500), -- id: 76
(32, 5, 135000, 15, 1, 114750), -- id: 77
(32, 6, 155000, 5, 2, 294500), -- id: 78
(33, 1, 100000, 20, 3, 240000), -- id: 79
(33, 2, 140000, 10, 1, 126000), -- id: 80
(33, 3, 160000, 0, 1, 160000), -- id: 81
(33, 4, 110000, 5, 2, 209000), -- id: 82
(34, 5, 120000, 10, 1, 108000), -- id: 83
(34, 6, 140000, 15, 2, 238000), -- id: 84
(34, 1, 130000, 5, 1, 123500), -- id: 85
(34, 2, 150000, 0, 2, 300000); -- id: 86
