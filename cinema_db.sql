create database cinema_db;
use cinema_db;

-- Người dùng
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    role_name VARCHAR(50) NOT NULL
);

-- Phim
CREATE TABLE movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    image TEXT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    duration INT NOT NULL, -- in minutes
    release_date_at TIMESTAMP NOT NULL
);

-- Rạp
CREATE TABLE theaters (
    theater_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `location` VARCHAR(255) NOT NULL
);

-- Xuất chiếu
CREATE TABLE shows (
    show_id BIGINT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_at TIMESTAMP NOT NULL,
    price BIGINT NOT NULL,
    discount_percentage INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (theater_id) REFERENCES theaters(theater_id)
);

-- Chỗ ngồi
CREATE TABLE seats (
    seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    show_id BIGINT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    available BIT NOT NULL,
    FOREIGN KEY (show_id) REFERENCES shows(show_id)
);

-- Sản phẩm (bánh + nước)
CREATE TABLE products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    image TEXT NOT NULL,
    price BIGINT NOT NULL,
    discount_percentage INT NOT NULL,
    stock INT NOT NULL
);

-- Vé
CREATE TABLE tickets (
    ticket_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    total_amount BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (theater_id) REFERENCES theaters(theater_id)
);

-- Vé - chi tiết chỗ ngồi
CREATE TABLE ticket_seats (
    ticket_seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    price BIGINT NOT NULL,
    discount_percentage INT NOT NULL,
    total_price BIGINT NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);

-- Vé - chi tiết sản phẩm
CREATE TABLE ticket_products (
    ticket_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price BIGINT NOT NULL,
    discount_percentage INT NOT NULL,
    quantity INT NOT NULL,
    total_price BIGINT NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- INSERT DATA

INSERT INTO users (full_name, email, username, `password`, role_name) VALUES
('Phạm Thái Duy', 'phamthaiduy@gmail.com', 'phamthaiduy', '123', 'ADMIN'),
('Trần Anh Tài', 'trananhtai@gmail.com', 'trananhtai', '123', 'CUSTOMER'),
('Lê Hoàng Thục', 'lehoangthuc@gmail.com', 'lehoangthuc', '123', 'CUSTOMER'),
('Nguyễn Hoàng Phúc', 'nguyenhoangphuc@gmail.com', 'nguyenhoangphuc', '123', 'CUSTOMER'),
('Lê Văn Hiệp', 'levanhiep@gmail.com', 'levanhiep', '123', 'CUSTOMER'),
('Nguyễn Thị Hồng', 'nguyenthihong@gmail.com', 'nguyenthihong', '123', 'CUSTOMER');

INSERT INTO movies (title, image, genre, duration, release_date_at) VALUES
('Edge of Tomorrow', 'image.png', 'Sci-Fi', 113, '2014-06-06'),
('The Godfather', 'image.png', 'Crime', 175, '1972-03-24'),
('Inception', 'image.png', 'Action', 148, '2010-07-16'),
('Finding Nemo', 'image.png', 'Animation', 100, '2003-05-30'),
('The Dark Knight', 'image.png', 'Action', 152, '2008-07-18');

INSERT INTO theaters (`name`, location) VALUES
('Cinema 1', 'Downtown, City Center'),
('Cinema 2', 'Uptown Mall'),
('Cinema 3', 'Eastside Cinema Complex');

INSERT INTO shows (show_id, movie_id, theater_id, start_at, price, discount_percentage) VALUES
(10000000000, 1, 1, '2025-05-22 17:00:00', 45000, 10),
(10000000001, 1, 1, '2025-05-23 21:00:00', 45000, 10),
(10000000002, 2, 1, '2025-05-24 17:00:00', 45000, 10),
(10000000003, 2, 1, '2025-05-23 22:00:00', 45000, 10),
(10000000004, 3, 1, '2025-05-24 17:00:00', 45000, 10),
(10000000005, 3, 1, '2025-05-25 23:00:00', 45000, 10),
(10000000006, 4, 1, '2025-05-23 17:00:00', 45000, 10),
(10000000007, 4, 1, '2025-05-24 13:00:00', 45000, 10),
(10000000008, 5, 1, '2025-05-25 17:00:00', 45000, 10),
(10000000009, 5, 1, '2025-05-23 09:00:00', 45000, 10),
(10000000010, 1, 2, '2025-05-24 17:00:00', 45000, 10),
(10000000011, 1, 2, '2025-05-23 21:00:00', 45000, 10),
(10000000012, 2, 2, '2025-05-24 17:00:00', 45000, 10),
(10000000013, 2, 2, '2025-05-23 22:00:00', 45000, 10),
(10000000014, 3, 3, '2025-05-24 17:00:00', 45000, 10),
(10000000015, 3, 3, '2025-05-23 23:00:00', 45000, 10),
(10000000016, 4, 3, '2025-05-24 17:00:00', 45000, 10),
(10000000017, 4, 3, '2025-05-25 13:00:00', 45000, 10),
(10000000018, 5, 3, '2025-05-23 17:00:00', 45000, 10);

INSERT INTO seats (show_id, seat_number, available) VALUES
(10000000000, 'A1', true),
(10000000000, 'A2', true),
(10000000000, 'B1', true),
(10000000000, 'B2', true),
(10000000000, 'C1', true),
(10000000000, 'C2', true),
(10000000001, 'A1', true),
(10000000001, 'A2', true),
(10000000001, 'B1', true),
(10000000001, 'B2', true),
(10000000001, 'C1', true),
(10000000001, 'C2', true),
(10000000002, 'A1', true),
(10000000002, 'A2', true),
(10000000002, 'B1', true),
(10000000002, 'B2', true),
(10000000002, 'C1', true),
(10000000002, 'C2', true),
(10000000003, 'A1', true),
(10000000003, 'A2', true),
(10000000003, 'B1', true),
(10000000003, 'B2', true),
(10000000003, 'C1', true),
(10000000003, 'C2', true),
(10000000004, 'A1', true),
(10000000004, 'A2', true),
(10000000004, 'B1', true),
(10000000004, 'B2', true),
(10000000004, 'C1', true),
(10000000004, 'C2', true),
(10000000005, 'A1', true),
(10000000005, 'A2', true),
(10000000005, 'B1', true),
(10000000005, 'B2', true),
(10000000005, 'C1', true),
(10000000005, 'C2', true),
(10000000006, 'A1', true),
(10000000006, 'A2', true),
(10000000006, 'B1', true),
(10000000006, 'B2', true),
(10000000006, 'C1', true),
(10000000006, 'C2', true),
(10000000007, 'A1', true),
(10000000007, 'A2', true),
(10000000007, 'B1', true),
(10000000007, 'B2', true),
(10000000007, 'C1', true),
(10000000007, 'C2', true),
(10000000008, 'A1', true),
(10000000008, 'A2', true),
(10000000008, 'B1', true),
(10000000008, 'B2', true),
(10000000008, 'C1', true),
(10000000008, 'C2', true),
(10000000009, 'A1', true),
(10000000009, 'A2', true),
(10000000009, 'B1', true),
(10000000009, 'B2', true),
(10000000009, 'C1', true),
(10000000009, 'C2', true),
(10000000010, 'A1', true),
(10000000010, 'A2', true),
(10000000010, 'B1', true),
(10000000010, 'B2', true),
(10000000010, 'C1', true),
(10000000010, 'C2', true),
(10000000011, 'A1', true),
(10000000011, 'A2', true),
(10000000011, 'B1', true),
(10000000011, 'B2', true),
(10000000011, 'C1', true),
(10000000011, 'C2', true),
(10000000012, 'A1', true),
(10000000012, 'A2', true),
(10000000012, 'B1', true),
(10000000012, 'B2', true),
(10000000012, 'C1', true),
(10000000012, 'C2', true),
(10000000013, 'A1', true),
(10000000013, 'A2', true),
(10000000013, 'B1', true),
(10000000013, 'B2', true),
(10000000013, 'C1', true),
(10000000013, 'C2', true),
(10000000014, 'A1', true),
(10000000014, 'A2', true),
(10000000014, 'B1', true),
(10000000014, 'B2', true),
(10000000014, 'C1', true),
(10000000014, 'C2', true),
(10000000015, 'A1', true),
(10000000015, 'A2', true),
(10000000015, 'B1', true),
(10000000015, 'B2', true),
(10000000015, 'C1', true),
(10000000015, 'C2', true),
(10000000016, 'A1', true),
(10000000016, 'A2', true),
(10000000016, 'B1', true),
(10000000016, 'B2', true),
(10000000016, 'C1', true),
(10000000016, 'C2', true),
(10000000017, 'A1', true),
(10000000017, 'A2', true),
(10000000017, 'B1', true),
(10000000017, 'B2', true),
(10000000017, 'C1', true),
(10000000017, 'C2', true),
(10000000018, 'A1', true),
(10000000018, 'A2', true),
(10000000018, 'B1', true),
(10000000018, 'B2', true),
(10000000018, 'C1', true),
(10000000018, 'C2', true);

INSERT INTO products (`name`, image, price, discount_percentage, stock) VALUES
('Popcorn Combo', 'image.png', 50000, 10, 100),
('Coke Large', 'image.png', 30000, 0, 200),
('Nachos with Cheese', 'image.png', 60000, 15, 80),
('Sprite Medium', 'image.png', 25000, 0, 150),
('Hotdog', 'image.png', 45000, 5, 120);
