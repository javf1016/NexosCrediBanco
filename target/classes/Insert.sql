-- Insertar titulares de cuentas
INSERT INTO account_holders (first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO account_holders (first_name, last_name) VALUES ('Jane', 'Smith');

-- Insertar productos relacionados con los titulares
INSERT INTO products (product_name, account_holder_id) VALUES ('Product 1', 1);
INSERT INTO products (product_name, account_holder_id) VALUES ('Product 2', 2);