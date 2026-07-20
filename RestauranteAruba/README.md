# RestauranteAruba

Proyecto web de restaurante desarrollado con Spring Boot, Maven y MySQL. La aplicacion expone una base para gestionar datos del restaurante como clientes, empleados, mesas, productos, insumos, pedidos, detalles de pedidos y comprobantes.

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Maven Wrapper 3.3.4 con Maven 3.9.16
- Spring Web MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Lombok

## Estructura principal

```text
RestauranteAruba/
  pom.xml
  mvnw
  mvnw.cmd
  src/main/java/pe/com/restaurantearuba/
  src/main/resources/application.properties
```

## Configuracion de base de datos

La aplicacion usa MySQL y espera una base de datos local llamada `aruba_db`.

Antes de ejecutar el proyecto, crea la base de datos:

```sql
CREATE DATABASE aruba_db;
```

`src/main/resources/application.properties` trae valores por defecto para un MySQL
local recien instalado (`root` sin clave). **No edites ese archivo con tus credenciales**,
porque es un archivo compartido por todo el equipo. Si tu usuario, clave o puerto son
distintos, sobreescribelos con variables de entorno antes de correr la app (Spring Boot
las toma automaticamente):

```bash
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=tu_clave
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/aruba_db
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
$env:SPRING_DATASOURCE_USERNAME = "root"
$env:SPRING_DATASOURCE_PASSWORD = "tu_clave"
$env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/aruba_db"
.\mvnw.cmd spring-boot:run
```

O configuralas como variables de entorno en la configuracion de ejecucion de tu IDE
(Run/Debug Configuration en IntelliJ o Eclipse).

## Base de datos en despliegue (AWS)

El esquema y los datos ya se aplican directamente sobre la instancia (RDS) mediante
script, fuera de la aplicacion. Spring Boot/Hibernate solo valida (`ddl-auto=validate`)
que las entidades coincidan con lo ya creado; no se ejecuta ninguna migracion al
levantar la app ni al generar el jar.

## Ejecutar el proyecto

Desde la carpeta `RestauranteAruba`:

```bash
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicacion queda disponible en:

```text
http://localhost:8090/aruba
```

## Compilar y probar

```bash
# Ejecutar pruebas
./mvnw test

# Compilar el proyecto
./mvnw clean package
```

En Windows PowerShell:

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
```

## Notas de historial

Segun el historial de Git, el proyecto inicio con un primer commit y luego se agrego la configuracion de Flyway junto con los scripts de migracion para facilitar la preparacion del entorno del equipo.
