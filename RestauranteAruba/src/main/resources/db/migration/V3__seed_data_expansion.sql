-- 1. DISTRITOS FALTANTES DE LIMA METROPOLITANA
-- Ya existen: Miraflores, San Isidro, Surco (Santiago de Surco)

INSERT INTO distrito (nomdis, estdis) VALUES
                                          ('Ancón', 1),
                                          ('Ate', 1),
                                          ('Barranco', 1),
                                          ('Breña', 1),
                                          ('Carabayllo', 1),
                                          ('Chaclacayo', 1),
                                          ('Chorrillos', 1),
                                          ('Cieneguilla', 1),
                                          ('Comas', 1),
                                          ('El Agustino', 1),
                                          ('Independencia', 1),
                                          ('Jesús María', 1),
                                          ('La Molina', 1),
                                          ('La Victoria', 1),
                                          ('Lima', 1),
                                          ('Lince', 1),
                                          ('Los Olivos', 1),
                                          ('Lurigancho', 1),
                                          ('Lurín', 1),
                                          ('Magdalena del Mar', 1),
                                          ('Pachacámac', 1),
                                          ('Pucusana', 1),
                                          ('Pueblo Libre', 1),
                                          ('Puente Piedra', 1),
                                          ('Punta Hermosa', 1),
                                          ('Punta Negra', 1),
                                          ('Rímac', 1),
                                          ('San Bartolo', 1),
                                          ('San Borja', 1),
                                          ('San Juan de Lurigancho', 1),
                                          ('San Juan de Miraflores', 1),
                                          ('San Luis', 1),
                                          ('San Martín de Porres', 1),
                                          ('San Miguel', 1),
                                          ('Santa Anita', 1),
                                          ('Santa María del Mar', 1),
                                          ('Santa Rosa', 1),
                                          ('Surquillo', 1),
                                          ('Villa El Salvador', 1),
                                          ('Villa María del Triunfo', 1);

-- 2. MESAS ADICIONALES (de M-01 a M-03 ya existen; se completa hasta M-20)

INSERT INTO mesa (nummes, capmes, estmes) VALUES
                                              ('M-04', 4, 'DISPONIBLE'),
                                              ('M-05', 6, 'DISPONIBLE'),
                                              ('M-06', 2, 'DISPONIBLE'),
                                              ('M-07', 4, 'DISPONIBLE'),
                                              ('M-08', 6, 'DISPONIBLE'),
                                              ('M-09', 2, 'DISPONIBLE'),
                                              ('M-10', 4, 'DISPONIBLE'),
                                              ('M-11', 6, 'DISPONIBLE'),
                                              ('M-12', 2, 'DISPONIBLE'),
                                              ('M-13', 4, 'DISPONIBLE'),
                                              ('M-14', 6, 'DISPONIBLE'),
                                              ('M-15', 2, 'DISPONIBLE'),
                                              ('M-16', 4, 'DISPONIBLE'),
                                              ('M-17', 6, 'DISPONIBLE'),
                                              ('M-18', 2, 'DISPONIBLE'),
                                              ('M-19', 4, 'DISPONIBLE'),
                                              ('M-20', 6, 'DISPONIBLE');

-- 3. METODO DE PAGO ADICIONAL

INSERT INTO metodopago (nommet, estmet) VALUES
                                            ('Plin', 1);

-- 4. CLIENTES ADICIONALES
-- coddis 4-8 = Ancón, Ate, Barranco, Breña, Carabayllo (recien insertados arriba)

INSERT INTO cliente (numdoc, nomcli, apepcli, apemcli, telcli, dirclir, estcli, coddis, codtipdoc) VALUES
                                                                                                       ('74567890', 'Ricardo', 'Flores', 'Salazar', '987654324', 'Av. Salaverry 210', 1, 4, 1),
                                                                                                       ('75678901', 'Patricia', 'Nuñez', 'Vega', '987654325', 'Jr. Tacna 315', 1, 5, 1),
                                                                                                       ('76789012', 'Jorge', 'Castillo', 'Rios', '987654326', 'Av. Brasil 450', 1, 6, 1),
                                                                                                       ('77890123', 'Fiorella', 'Chavez', 'Mendez', '987654327', 'Calle Las Begonias 88', 1, 7, 1),
                                                                                                       ('78901234', 'Diego', 'Guerrero', 'Palacios', '987654328', 'Av. Angamos 620', 1, 8, 1);

-- 5. EMPLEADOS ADICIONALES (Meseros)
-- coddis 9-10 = Chaclacayo, Chorrillos

INSERT INTO empleado (numdoc, nomemp, apepemp, apememp, caremp, telemp, diremp, estemp, coddis, codtipdoc) VALUES
                                                                                                               ('70444555', 'Pedro', 'Huaman', 'Quispe', 'Mesero', '999888780', 'Av. Colonial 500', 1, 9, 1),
                                                                                                               ('70555666', 'Rosa', 'Delgado', 'Fernandez', 'Mesero', '999888781', 'Jr. Ucayali 120', 1, 10, 1);
