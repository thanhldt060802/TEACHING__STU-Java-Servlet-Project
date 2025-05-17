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
    release_date_at DATE NOT NULL
);

-- Rạp
CREATE TABLE theaters (
    theater_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `location` VARCHAR(255) NOT NULL
);

-- Xuất chiếu
CREATE TABLE shows (
    show_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_at DATETIME NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (theater_id) REFERENCES theaters(theater_id)
);

-- Chỗ ngồi
CREATE TABLE seats (
    seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    show_id BIGINT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    price BIGINT NOT NULL,
    discount INT NOT NULL,
    available BIT NOT NULL,
    FOREIGN KEY (show_id) REFERENCES shows(show_id)
);


-- Sản phẩm (bánh + nước)
CREATE TABLE products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    discount INT NOT NULL,
    stock INT NOT NULL
);

-- Vé
CREATE TABLE tickets (
    ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    show_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    total_amount BIGINT NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (theater_id) REFERENCES theaters(theater_id),
    FOREIGN KEY (show_id) REFERENCES shows(show_id),
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);

-- Vé - chi tiết chỗ ngồi
CREATE TABLE ticket_seats (
    ticket_seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    price BIGINT NOT NULL,
    discount INT NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);

-- Vé - chi tiết sản phẩm
CREATE TABLE ticket_products (
    ticket_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price BIGINT NOT NULL,
    discount INT NOT NULL,
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