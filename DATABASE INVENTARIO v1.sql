-- difinicion de base de datos
DROP DATABASE IF EXISTS InventarioRopa;
CREATE DATABASE IF NOT EXISTS InventarioRopa;

USE InventarioRopa;

-- TABLAS
DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
id_usuario INT AUTO_INCREMENT KEY NOT NULL,
correo VARCHAR (30) NOT NULL,
contraseña VARCHAR (30) NOT NULL
);

DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria(
id_cat INT AUTO_INCREMENT KEY NOT NULL,
nombrec VARCHAR (20) NOT NULL,
descripcion VARCHAR (50) 
);

DROP TABLE IF EXISTS productos;
CREATE TABLE productos(
id_producto INT AUTO_INCREMENT KEY NOT NULL,
nombrep VARCHAR (20) NOT NULL,
precio INT NOT NULL,
id_cat INT,
FOREIGN KEY (id_cat) REFERENCES categoria (id_cat)
);

DROP TABLE IF EXISTS encargado;
CREATE TABLE encargado(
id_encargado INT AUTO_INCREMENT KEY NOT NULL,
nombres VARCHAR (30) NOT NULL,
apellidos VARCHAR (30) NOT NULL,
telefono INT NOT NULL
);

DROP TABLE IF EXISTS ubicacion;
CREATE TABLE ubicacion(
id_ubicacion INT AUTO_INCREMENT KEY NOT NULL,
direccion VARCHAR(50) NOT NULL,
telefono INT NOT NULL,
id_encargado INT,
FOREIGN KEY (id_encargado) REFERENCES encargado (id_encargado)
);

