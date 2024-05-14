-- PROCEDIMIENTOS ALMACENADOS 
use inventarioropa;

-- TABLA USUARIO
DELIMITER //
DROP PROCEDURE IF EXISTS nuevoUsuario //
CREATE PROCEDURE nuevoUsuario(
	IN correoU VARCHAR(30),
    IN contraseñaU VARCHAR(150)
)
BEGIN 
	INSERT INTO usuarios(correo, contraseña)
    VALUES (correoU, SHA2('contraseñaU',256));
END //
DELIMITER ;


-- TABLA PRODUCTO
DELIMITER //
DROP PROCEDURE IF EXISTS listarProducto //
CREATE PROCEDURE listarProducto()
BEGIN
		SELECT * FROM productos;
        END //
DELIMITER ;


DELIMITER //
DROP PROCEDURE IF EXISTS nuevoProducto //
CREATE PROCEDURE nuevoProducto(
	IN nombre VARCHAR (150),
    IN precio_p INT,
    IN id_categoria INT
)
BEGIN
	INSERT INTO productos(nombrep,precio,id_cat)
    VALUES (nombre,precio_p,id_categoria);
    END //
DELIMITER ;

-- TABLA CATEGORIA
DELIMITER //
DROP PROCEDURE IF EXISTS nuevaCategoria //
CREATE PROCEDURE nuevaCategoria(
	IN nombre_cat VARCHAR (20),
    IN descrip VARCHAR (50)
)
BEGIN 
	INSERT INTO categoria(nombrec, descripcion) VALUES (nombre_cat, descrip);
    END //
DELIMITER ;





-- NOMBRES PROCEDIMIENTOS ALMACENADOS
/* 
nuevoUsuario
nuevoProducto
nuevaCategoria
listarProducto
*/