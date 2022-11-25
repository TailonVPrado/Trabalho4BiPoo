/*TABELA COR*/
CREATE TABLE COR (
    ID int auto_increment NOT NULL,
    DESCRICAO varchar(60)
);
alter table COR
  add constraint PK_COR primary key (ID);

/*TABELA MARCA*/
CREATE TABLE MARCA(
    ID int auto_increment NOT NULL,
    DESCRICAO varchar(60)
);
alter table MARCA
  add constraint PK_MARCA primary key (ID);

/*TABELA MODELO*/
CREATE TABLE MODELO(
    ID int auto_increment NOT NULL,
    DESCRICAO varchar(60),
    ID_MARCA int NOT NULL
);
alter table MODELO
  add constraint PK_MODELO primary key (ID);
alter table MODELO
  add constraint FK_MARCA_MODELO foreign key (ID_MARCA)
  references MARCA (ID);
