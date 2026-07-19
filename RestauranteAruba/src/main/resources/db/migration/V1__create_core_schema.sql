CREATE TABLE tipodocumento (
                               codtipdoc INT AUTO_INCREMENT PRIMARY KEY,
                               nomtipdoc VARCHAR(50) NOT NULL,
                               esttipdoc BIT NOT NULL
);

CREATE TABLE distrito (
                          coddis INT AUTO_INCREMENT PRIMARY KEY,
                          nomdis VARCHAR(100) NOT NULL,
                          estdis BIT NOT NULL
);

CREATE TABLE mesa (
                      codmes INT AUTO_INCREMENT PRIMARY KEY,
                      nummes VARCHAR(10) NOT NULL,
                      capmes INT NOT NULL,
                      estmes VARCHAR(20) NOT NULL
);

CREATE TABLE metodopago (
                            codmet INT AUTO_INCREMENT PRIMARY KEY,
                            nommet VARCHAR(50) NOT NULL,
                            estmet BIT NOT NULL
);

CREATE TABLE categoria (
                           codcat INT AUTO_INCREMENT PRIMARY KEY,
                           nomcat VARCHAR(30) NOT NULL,
                           estcat BIT NOT NULL
);

CREATE TABLE proveedor (
                           codpro INT AUTO_INCREMENT PRIMARY KEY,
                           rucpro CHAR(11) NOT NULL,
                           nompro VARCHAR(150) NOT NULL,
                           apeppro VARCHAR(100) NULL,
                           apempro VARCHAR(100) NULL,
                           telpro VARCHAR(15) NOT NULL,
                           corpro VARCHAR(50) NOT NULL,
                           estpro BIT NOT NULL
);

-- 2. TABLAS DEPENDIENTES

CREATE TABLE cliente (
                         codcli INT AUTO_INCREMENT PRIMARY KEY,
                         numdoc VARCHAR(15) NOT NULL,
                         nomcli VARCHAR(150) NOT NULL,
                         apepcli VARCHAR(100) NOT NULL,
                         apemcli VARCHAR(100) NOT NULL,
                         telcli VARCHAR(15) NOT NULL,
                         dirclir VARCHAR(100) NOT NULL,
                         estcli BIT NOT NULL,
                         coddis INT NOT NULL,
                         codtipdoc INT NOT NULL,
                         FOREIGN KEY (coddis) REFERENCES distrito(coddis),
                         FOREIGN KEY (codtipdoc) REFERENCES tipodocumento(codtipdoc)
);

CREATE TABLE empleado (
                          codemp INT AUTO_INCREMENT PRIMARY KEY,
                          numdoc VARCHAR(15) NOT NULL,
                          nomemp VARCHAR(150) NOT NULL,
                          apepemp VARCHAR(100) NOT NULL,
                          apememp VARCHAR(100) NOT NULL,
                          caremp VARCHAR(50) NOT NULL,
                          telemp VARCHAR (20) NOT NULL,
                          diremp VARCHAR (100) NOT NULL,
                          estemp BIT NOT NULL,
                          coddis INT NOT NULL,
                          codtipdoc INT NOT NULL,
                          FOREIGN KEY (coddis) REFERENCES distrito(coddis),
                          FOREIGN KEY (codtipdoc) REFERENCES tipodocumento(codtipdoc)
);

CREATE TABLE producto (
                          codprd INT AUTO_INCREMENT PRIMARY KEY,
                          nomprd VARCHAR(100) NOT NULL,
                          preprd DECIMAL(10,2) NOT NULL,
                          estprd BIT NOT NULL,
                          codcat INT NOT NULL,
                          FOREIGN KEY (codcat) REFERENCES categoria(codcat)
);

CREATE TABLE insumo (
                        codins INT AUTO_INCREMENT PRIMARY KEY,
                        nomins VARCHAR(100) NOT NULL,
                        stoins DECIMAL(10,2) NOT NULL,
                        unimed VARCHAR(20) NOT NULL,
                        fecvenins DATE NULL,
                        estins BIT NOT NULL,
                        codpro INT NOT NULL,
                        FOREIGN KEY (codpro) REFERENCES proveedor(codpro)
);

-- 3. TABLAS TRANSACCIONALES

CREATE TABLE pedido (
                        codped INT AUTO_INCREMENT PRIMARY KEY,
                        fecped DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        estped VARCHAR(20) NOT NULL,
                        codemp INT NOT NULL,
                        codmes INT NOT NULL,
                        codcli INT NULL,
                        FOREIGN KEY (codemp) REFERENCES empleado(codemp),
                        FOREIGN KEY (codmes) REFERENCES mesa(codmes),
                        FOREIGN KEY (codcli) REFERENCES cliente(codcli)
);

CREATE TABLE detallepedido (
                               coddet INT AUTO_INCREMENT PRIMARY KEY,
                               codped INT NOT NULL,
                               codprd INT NOT NULL,
                               canped INT NOT NULL,
                               preuni DECIMAL(10,2) NOT NULL,
                               notadet VARCHAR(255) NULL,
                               FOREIGN KEY (codped) REFERENCES pedido(codped),
                               FOREIGN KEY (codprd) REFERENCES producto(codprd)
);

CREATE TABLE comprobante (
                             codcom INT AUTO_INCREMENT PRIMARY KEY,
                             tipcom VARCHAR(20) NOT NULL,
                             numser CHAR(4) NOT NULL,
                             numcom VARCHAR(8) NOT NULL,
                             feccom DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             totcom DECIMAL(10,2) NOT NULL,
                             estcom BIT NOT NULL,
                             codped INT NOT NULL,
                             codmet INT NOT NULL,
                             FOREIGN KEY (codped) REFERENCES pedido(codped),
                             FOREIGN KEY (codmet) REFERENCES metodopago(codmet)
);