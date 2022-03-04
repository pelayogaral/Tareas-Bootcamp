INSERT INTO departamentos (nombre, ubicacion) VALUES ('Recursos Humanos', 'Planta 2');
INSERT INTO departamentos (nombre, ubicacion) VALUES ('Transporte', 'Planta -1');
INSERT INTO departamentos (nombre, ubicacion) VALUES ('Informática', 'Planta 3');
INSERT INTO departamentos (nombre, ubicacion) VALUES ('Formación', 'Planta 1');
INSERT INTO departamentos (nombre, ubicacion) VALUES ('Mantenimiento', 'Planta 0');

INSERT INTO empleados (dni, nombre, salario, telefono, id_departamento) VALUES ('71907401A', 'Pelayo', 314.15, 123456, 1);
INSERT INTO empleados (dni, nombre, salario, telefono, id_departamento) VALUES ('71907402B', 'Ana', 400.83, 789654, 1);
INSERT INTO empleados (dni, nombre, salario, telefono, id_departamento) VALUES ('71907403C', 'Andrés', 360.25, 653986 , 3);
INSERT INTO empleados (dni, nombre, salario, telefono, id_departamento) VALUES ('71907404D', 'Sergio', 500, 342093, 2);
INSERT INTO empleados (dni, nombre, salario, telefono, id_departamento) VALUES ('71907405E', 'María', 453.76, 324195, 4);

INSERT INTO jefes (dni, nombre, salario, telefono, id_departamento) VALUES ('71907301E', 'Miguel', 700.32, 342093, 1);
INSERT INTO jefes (dni, nombre, salario, telefono, id_departamento) VALUES ('71907302D', 'Carolina', 768.43, 653986, 3);
INSERT INTO jefes (dni, nombre, salario, telefono, id_departamento) VALUES ('71907303C', 'Andrea', 754.54, 123456, 2);
INSERT INTO jefes (dni, nombre, salario, telefono, id_departamento) VALUES ('71907304B', 'Jorge', 743.89, 324195, 5);
INSERT INTO jefes (dni, nombre, salario, telefono, id_departamento) VALUES ('71907305A', 'Diana', 790.43, 789654, 4);

INSERT INTO usuarios (username, password, enabled) VALUES ('pelayo', '$2a$10$ksR221U3k4v5Mgle9/AjnOBZSrMml8oicUW9QDYcpJWmxrUcXsJyC',1);
INSERT INTO usuarios (username, password, enabled) VALUES ('ruben', '$2a$10$kNLliNNFzf5xMakXjWx7GOTSOp1Z7c4/XSJ5kJRhnvQapC31jgUQu',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);