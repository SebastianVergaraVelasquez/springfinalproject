INSERT INTO gammas(gamma_code, name, description) VALUES ('a','Herbaceas','Plantas para jardin decorativas');
INSERT INTO gammas(gamma_code, name, description) VALUES ('b','Herramientas','Herramientas para todo tipo de acción');
INSERT INTO gammas(gamma_code, name, description) VALUES ('c','Aromáticas','Plantas aromáticas');
INSERT INTO gammas(gamma_code, name, description) VALUES ('d','Frutales','Árboles pequeños de producción frutal');
INSERT INTO gammas(gamma_code, name, description) VALUES ('e','Ornamentales','Plantas vistosas para la decoración del jardín');

INSERT INTO products (product_code,name,gamma_code,stock,price,description,height,width,depth) VALUES ('PRD001', 'Ramo de Rosas Rojas', 'a', 50, 35.99, 'Ramo de rosas rojas frescas', 50,50,50);
INSERT INTO products (product_code,name,gamma_code,stock,price,description,height,width,depth) VALUES ('PRD002', 'Caja de Rosas Blancas', 'b', 30, 25.50, 'Caja de rosas blancas para regalo', 50,50,50);
INSERT INTO products (product_code,name,gamma_code,stock,price,description,height,width,depth)VALUES ('PRD003', 'Bouquet de Tulipanes', 'c', 40, 45.75, 'Bouquet de tulipanes variados', 50, 50, 50);