CREATE DATABASE glucaJava;
USE glucaJava;

CREATE TABLE medicoes(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    glicemia INT NOT NULL,
    estado ENUM("Lo","Hipoglicemia","Ok","Hiperglicemia significativa","Hiperglicemia severa","Hi"),
    dataMedicao DATE DEFAULT (CURRENT_DATE),
    horarioMedicao TIME DEFAULT (CURRENT_TIME)
);