CREATE TABLE item
(
    id         BINARY(16) NOT NULL,
    product_id BINARY(16) NULL,
    unit_price DECIMAL NULL,
    nr_units   DECIMAL NULL,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id            BINARY(16)   NOT NULL,
    total         DECIMAL NULL,
    `description` VARCHAR(255) NULL,
    date          datetime NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE order_items
(
    order_id BINARY(16) NOT NULL,
    items_id BINARY(16) NOT NULL
);

CREATE TABLE product
(
    id            BINARY(16)   NOT NULL,
    `description` VARCHAR(255) NULL,
    price         DECIMAL NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE order_items
    ADD CONSTRAINT uc_order_items_items UNIQUE (items_id);

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE order_items
    ADD CONSTRAINT fk_ordite_on_item FOREIGN KEY (items_id) REFERENCES item (id);

ALTER TABLE order_items
    ADD CONSTRAINT fk_ordite_on_order FOREIGN KEY (order_id) REFERENCES `order` (id);