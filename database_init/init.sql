DROP DATABASE IF EXISTS clothing_store;
CREATE DATABASE clothing_store;
\c clothing_store;

CREATE TABLE clothing (
    "id" SERIAL PRIMARY KEY,
    "item_name" VARCHAR(255) NOT NULL,
    "price" DECIMAL(12, 2) NOT NULL,
    "manufacturer" VARCHAR(255) NOT NULL,
    "type" VARCHAR(50) NOT NULL,
    "created_at" DATE NOT NULL DEFAULT CURRENT_DATE
);

INSERT INTO clothing (item_name, price, manufacturer, "type") VALUES
('REGULAR-FIT SUIT IN MICRO-PATTERNED VIRGIN WOOL', 72900, 'Hugo Boss', 'Suit'),
('EXTRA-SLIM-FIT SUIT IN A WOOL BLEND', 48600, 'Hugo Boss', 'Suit'),
('SLIM-FIT SUIT IN STRETCH VIRGIN WOOL', 60800, 'Hugo Boss', 'Suit'),
('Napoli Line double-breasted cashmere and silk suit', 860000, 'Giorgio Armani', 'Suit'),
('Shirt in linen canvas', 97000, 'Giorgio Armani', 'Shirt'),
('Slim-fit striped cotton-blend jersey shirt', 89900, 'Giorgio Armani', 'Shirt'),
('Cupro monogram tie', 21000, 'Giorgio Armani', 'Tie'),
('FINE POPLIN SHELTON SUIT', 800000, 'Tom Ford', 'Suit'),
('GRAND CHECK SHELTON SUIT', 571000, 'Tom Ford', 'Suit'),
('BOLD STRIPE TIE', 30500, 'Tom Ford', 'Tie');

CREATE TYPE order_status AS ENUM ('ORDERED', 'CANCELLED', 'IN_PROGRESS');

CREATE TABLE orders (
    "id" SERIAL PRIMARY KEY,
    "status" order_status DEFAULT 'IN_PROGRESS',
    "amount" DECIMAL(10, 2) NOT NULL
);

CREATE TABLE "order_clothing" (
	"id" SERIAL PRIMARY KEY,
    "order_id" INT REFERENCES orders(id),
    "clothing_id" INT REFERENCES clothing(id)
);

CREATE TABLE "users" (
    "id" SERIAL PRIMARY KEY,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) UNIQUE NOT NULL,
    "phone" VARCHAR(50),
    "address" TEXT,
    "password_hash" TEXT NOT NULL
);

CREATE TABLE user_orders (
	 "id" SERIAL PRIMARY KEY,
    "user_id" INT REFERENCES users(id),
    "order_id" INT REFERENCES orders(id)
);


CREATE TABLE order_reviews (
    "id" SERIAL PRIMARY KEY,
    "grade" INT NOT NULL,
    "comment" TEXT,
    "order_id" INT REFERENCES orders(id)
);

CREATE TABLE clothing_reviews (
    "id" SERIAL PRIMARY KEY,
    "grade" INT NOT NULL,
    "comment" TEXT,
    "clothing_id" INT REFERENCES clothing(id)
);

CREATE TABLE favorites (
	"id" SERIAL PRIMARY KEY,
    "user_id"  INT REFERENCES users(id),
    "clothing_id" INT REFERENCES clothing(id)
);

CREATE TABLE sizes (
    "id" SERIAL PRIMARY KEY,
    size VARCHAR(10) NOT NULL
);


INSERT INTO sizes (size) VALUES
('XS'), ('S'), ('M'), ('L'), ('XL');

CREATE TABLE clothing_sizes (
		"id" SERIAL PRIMARY KEY,
    "clothing_id" INT REFERENCES clothing(id),
    "size_id" INT REFERENCES sizes(id)
);

INSERT INTO clothing_sizes (clothing_id, size_id) VALUES
(1,1), (1,2), (1,3), (1,4),(1,5),
(2,1), (2,2), (2,3), (2,4),(2,5),
(3,1), (3,2), (3,3), (3,4),(3,5),
(4,1), (4,2), (4,3), (4,4),(4,5),
(5,1), (5,2), (5,3), (5,4),(5,5),
(6,1), (6,2), (6,3), (6,4),(6,5),
(8,1), (8,2), (8,3), (8,4),(8,5),
(9,1), (9,2), (9,3), (9,4),(9,5);


CREATE TABLE clothing_colors (
    "id" SERIAL PRIMARY KEY,
    "color" VARCHAR(50) NOT NULL,
    "clothing_id" INT REFERENCES clothing(id),
    "image_src" TEXT NOT NULL
);

INSERT INTO clothing_colors (color, clothing_id, image_src) VALUES
('Blue', 1, 'https://images.hugoboss.com/is/image/boss/hbeu50514634_438_170?$re_fullPageZoom$&qlt=85&fit=crop,1&align=1,1&bgcolor=ebebeb&lastModified=1718027670000&wid=1440&hei=2182&fmt=webp'),
 ('Light Green', 1, 'https://images.hugoboss.com/is/image/boss/hbeu50514634_374_170?$re_fullPageZoom$&qlt=85&fit=crop,1&align=1,1&bgcolor=ebebeb&lastModified=1717510513000&wid=1440&hei=2182&fmt=webp'),
 ('Dark Grey', 2, 'https://images.hugoboss.com/is/image/boss/hbeu50450994_028_170?$re_fullPageZoom$&qlt=85&fit=crop,1&align=1,1&bgcolor=ebebeb&lastModified=1717767199000&wid=1440&hei=2182&fmt=webp'), 
 ('Dark Blue', 2, 'https://images.hugoboss.com/is/image/boss/hbeu50450994_405_170?$re_fullPageZoom$&qlt=85&fit=crop,1&align=1,1&bgcolor=ebebeb&lastModified=1717767199000&wid=1440&hei=2182&fmt=webp'), 
 ('Black', 2, 'https://images.hugoboss.com/is/image/boss/hbeu50450994_001_170?$re_fullPageZoom$&qlt=85&fit=crop,1&align=1,1&bgcolor=ebebeb&lastModified=1717767199000&wid=1440&hei=2182&fmt=webp'),
 ('Black', 3, 'https://images.hugoboss.com/is/image/boss/hbeu50493667_001_170?$re_fullPageZoom$&qlt=85&fit=crop,1&align=1,1&bgcolor=ebebeb&lastModified=1717765427000&wid=1440&hei=2182&fmt=webp'), 
 ('Black', 4, 'https://www.armani.com/variants/images/1647597327989770/F/w1920.jpg'), 
 ('Black', 5, 'https://www.armani.com/variants/images/1647597339593217/F/w1920.jpg'),
 ('Biege', 5, 'https://www.armani.com/variants/images/1647597339593085/F/w1920.jpg'),
 ('Azure', 6, 'https://www.armani.com/variants/images/1647597339605274/F/w1920.jpg'),
 ('Midnight Blue', 7, 'https://www.armani.com/variants/images/1647597327971593/F/w1920.jpg'),
 ('Blue', 8, 'https://cdn.media.amplience.net/i/tom_ford/2HSP01-CSS01_HB407_OS_A?w=1280'),
 ('Black', 9, 'https://cdn.media.amplience.net/i/tom_ford/2LSP01-WOP11_ZGREY_OS_A'),
 ('Blue', 10, 'https://cdn.media.amplience.net/i/tom_ford/STN001-SPP65_HB825_OS_B?w=1280');