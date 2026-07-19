-- 1. TABLAS SIMPLES

INSERT INTO tipodocumento (nomtipdoc, esttipdoc) VALUES
                                                     ('DNI', 1),
                                                     ('RUC', 1),
                                                     ('Pasaporte', 1);

INSERT INTO distrito (nomdis, estdis) VALUES
                                          ('Miraflores', 1),
                                          ('San Isidro', 1),
                                          ('Surco', 1);

INSERT INTO mesa (nummes, capmes, estmes) VALUES
                                              ('M-01', 4, 'DISPONIBLE'),
                                              ('M-02', 6, 'OCUPADA'),
                                              ('M-03', 4, 'DISPONIBLE');

INSERT INTO metodopago (nommet, estmet) VALUES
                                            ('Efectivo', 1),
                                            ('Tarjeta', 1),
                                            ('Yape', 1);

INSERT INTO categoria (nomcat, estcat) VALUES
                                           ('Bebidas', 1),
                                           ('Platos', 1),
                                           ('Postres', 1);

INSERT INTO proveedor (rucpro, nompro, apeppro, apempro, telpro, corpro, estpro) VALUES
                                                                                     ('20111111111', 'Distribuidora Lima SAC', NULL, NULL, '999111111', 'ventas@dlima.com', 1),
                                                                                     ('20222222222', 'Alimentos Andinos SAC', NULL, NULL, '999222222', 'contacto@andinos.com', 1),
                                                                                     ('20333333333', 'Lacteos del Sur SAC', NULL, NULL, '999333333', 'ventas@lacteosdelsur.com', 1);


-- 2. TABLAS DOBLES

INSERT INTO cliente (numdoc, nomcli, apepcli, apemcli, telcli, dirclir, estcli, coddis, codtipdoc) VALUES
                                                                                                       ('71234567', 'Juan', 'Perez', 'Gomez', '987654321', 'Av. Larco 123', 1, 1, 1),
                                                                                                       ('72345678', 'Maria', 'Lopez', 'Diaz', '987654322', 'Calle Los Pinos 456', 1, 2, 1),
                                                                                                       ('73456789', 'Carlos', 'Ramirez', 'Torres', '987654323', 'Jr. Primavera 789', 1, 3, 1);

INSERT INTO empleado (numdoc, nomemp, apepemp, apememp, caremp, telemp, diremp, estemp, coddis, codtipdoc) VALUES
                                                                                                               ('70111222', 'Ana', 'Vargas', 'Rojas', 'Mesero', '999888777', 'Av. Benavides 100', 1, 1, 1),
                                                                                                               ('70222333', 'Luis', 'Mendoza', 'Castro', 'Cajero', '999888778', 'Av. Arequipa 200', 1, 2, 1),
                                                                                                               ('70333444', 'Sofia', 'Paredes', 'Leon', 'Administrador', '999888779', 'Av. Primavera 300', 1, 3, 1);

INSERT INTO producto (nomprd, preprd, estprd, codcat) VALUES
                                                          ('Inca Kola 500ml', 5.50, 1, 1),
                                                          ('Lomo Saltado', 28.00, 1, 2),
                                                          ('Cheesecake', 12.00, 1, 3),
                                                          ('Coca Cola 500ml', 5.50, 1, 1),
                                                          ('Aji de Gallina', 25.00, 1, 2);

INSERT INTO insumo (nomins, stoins, unimed, fecvenins, estins, codpro) VALUES
                                                                           ('Arroz', 50.00, 'kg', '2027-01-15', 1, 2),
                                                                           ('Leche', 30.00, 'lt', '2026-12-20', 1, 3),
                                                                           ('Bebida Gaseosa', 100.00, 'und', '2027-06-30', 1, 1),
                                                                           ('Carne de Res', 25.00, 'kg', '2026-11-10', 1, 2);

-- 3. TABLAS TRANSACCIONALES

-- PEDIDOS
INSERT INTO pedido (estped, codemp, codmes, codcli) VALUES
                                                        ('ENTREGADO', 1, 1, 1),
                                                        ('ENTREGADO', 1, 2, 2),
                                                        ('PENDIENTE', 2, 3, 3);

-- DETALLE DE PEDIDOS
-- Pedido 1: Juan
INSERT INTO detallepedido (codped, codprd, canped, preuni, notadet) VALUES
                                                                        (1, 2, 1, 28.00, 'Sin cebolla'),
                                                                        (1, 1, 2, 5.50, NULL);

-- Pedido 2: Maria
INSERT INTO detallepedido (codped, codprd, canped, preuni, notadet) VALUES
                                                                        (2, 5, 1, 25.00, 'Extra aji'),
                                                                        (2, 3, 1, 12.00, NULL),
                                                                        (2, 4, 1, 5.50, NULL);

-- Pedido 3: Carlos
INSERT INTO detallepedido (codped, codprd, canped, preuni, notadet) VALUES
                                                                        (3, 2, 2, 28.00, NULL),
                                                                        (3, 3, 2, 12.00, NULL);

-- COMPROBANTES
INSERT INTO comprobante (tipcom, numser, numcom, totcom, estcom, codped, codmet) VALUES
                                                                                     ('Boleta', 'B001', '00000001', 39.00, 1, 1, 1),
                                                                                     ('Boleta', 'B001', '00000002', 42.50, 1, 2, 2),
                                                                                     ('Factura', 'F001', '00000001', 80.00, 1, 3, 3);