INSERT INTO marcas (nombre) VALUES ('Ford');
INSERT INTO marcas (nombre) VALUES ('Peugeot');
INSERT INTO marcas (nombre) VALUES ('Citroen');

INSERT INTO modelos (nombre) VALUES ('Deportivo');
INSERT INTO modelos (nombre) VALUES ('Coupe');
INSERT INTO modelos (nombre) VALUES ('SUV');

INSERT INTO coches (color, matricula , cilindrada, velocidad,marca_id, modelo_id) VALUES ('Azul', '5555ABC', 55, 125,1, 1);
INSERT INTO coches (color, matricula , cilindrada, velocidad,marca_id, modelo_id) VALUES ('Verde', '5456CBA', 60, 130,1, 2);
INSERT INTO coches (color, matricula , cilindrada, velocidad,marca_id, modelo_id) VALUES ('Amarillo', '5657BAC', 65, 135,2, 1);
INSERT INTO coches (color, matricula , cilindrada, velocidad,marca_id, modelo_id) VALUES ('Rojo', '5859AAA', 70, 140,2, 2);

INSERT INTO usuarios (username,password,enabled) VALUES ('pelayo', '$2a$10$wBxwYIwfO5FQCq0lcBbZD.tXZ4H19yElt.1m2YJoX/kqV8/rkP96.', 1);
INSERT INTO usuarios (username,password,enabled) VALUES ('admin', '$2a$10$stYp6fpVECJ6SJWYOVX8leSGg9fgFdhkUZ4l8mDwbLmv/9R8RDldq', 1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);