--create database PurchaseOrderSystem;

create table Users (
	user_id SERIAL primary key,
	user_name VARCHAR(30) unique not null,
	account_type VARCHAR(30) not null,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	email VARCHAR(50) not null,
	salt VARCHAR(690) not null,
	hashed_password VARCHAR(90) not null
);

create table Product (
	product_id SERIAL primary key,
	product_name VARCHAR(50) not null,
	dimension VARCHAR(30),
	weight VARCHAR(30),
	quantity Integer not null
);

create table Order_Table (
	order_id SERIAL primary key,
	user_id Integer references Users(user_id),
	product_id Integer references Product(product_id),
	shipping_address VARCHAR(100) not null,
	status VARCHAR(30) not null
);

--Admin Password: Abc123456
INSERT INTO Users (user_name, email, salt, hashed_password, account_type) VALUES ('freeleons', 'freeleons@gmail.com', 'K4cz8ld+7Q3YY+w/3DObyhnRHX8ENZfgMa5U8ucYRGH8aIR2havp56nDbjIGOGfi18lpUKPPDx7OUBd47LVFPOf5Wm6vX+Ge2CdBrEGIrIsW5f3V1/DdIM9o9Fe/VUpcpZ+LTOLyQx7nwsXAnATfLODzHxM8Yew2hBjJ56rWC2b5fdRTGIUVBf62BicLDB1deRdhENVpnUiaRwZEAVsRW8j+Xe4u8MHTYKRBi6cwGfTIIOBqF+TDJrCnBTZr3C7fq+CrjYhmhvUNECEB/Ufp3hrK6Go403pb4t9dkHfL0aUmZHulY85p3dVFghGVBcqE6VRdFKUWQDFMzfUF0tC85y0mOLHSxVWWldGsOIfVpMSFCILNVJLIzHrmbfonT2utxbfRPB09P1hvYKxJaRKoZdq6kFHXbDJYLaJgV1d9oVk7KHf6hUuf8Bf3Ja/w9kcCIMSW8m6U/TOHAoG3/ZqoWZY6GZHBMLpcwL6kxtlj/+ixkkWqw66amvAcJbXomb7GX5n7CYgWCbrg+cYgoRxI0mSqVoHELK08yfaKWbrfcl3JRfzHHWPAF074nCEbAECLJSWJ5Wr0+kxqG1xiJA6E4dHBmuIwTPZsQ/3pacKcJAC3mETGWuP9USpEId3y1G5jl+VyKSRwzqmomxCrQTBHRg60CBGCkcLIN3koi/TumHc=', '6qoe1keTn/hDoLxF8lB3uu4Bux0i1rq6kzPJxujOSlIzq7Xl6OreNvsap/m1xZMGK4qMQA0kq7GRumo0icDZUQ==', 'Admin');

SELECT * FROM Users WHERE user_name = 'freeleons'


