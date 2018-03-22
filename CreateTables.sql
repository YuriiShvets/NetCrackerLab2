DROP TABLE Types CASCADE CONSTRAINTS;
DROP TABLE Objects CASCADE CONSTRAINTS;
DROP TABLE Attributes CASCADE CONSTRAINTS;
DROP TABLE Relations CASCADE CONSTRAINTS;
DROP TABLE Parameters CASCADE CONSTRAINTS;

CREATE TABLE Types
    (TypesID NUMBER NOT NULL,
        TypesName VARCHAR2(15) NOT NULL,
        Description VARCHAR2(50));

ALTER TABLE Types ADD CONSTRAINT TypesPK PRIMARY KEY(TypesID);

CREATE TABLE Objects
    (ObjectsID NUMBER NOT NULL,
        TypesID NUMBER NOT NULL,
        ObjectsName VARCHAR2(15) NOT NULL);

ALTER TABLE Objects ADD CONSTRAINT ObjectPK PRIMARY KEY(ObjectsID);
ALTER TABLE Objects ADD CONSTRAINT ObjectFKTypes FOREIGN KEY(TypesID) REFERENCES Types;

CREATE TABLE Attributes
    (AttributesID NUMBER NOT NULL,
        TypesID NUMBER NOT NULL,
        AttributesName VARCHAR2(15) NOT NULL);

ALTER TABLE Attributes ADD CONSTRAINT AttributesPK PRIMARY KEY(AttributesID);
ALTER TABLE Attributes ADD CONSTRAINT AttributesFKTypes FOREIGN KEY(TypesID) REFERENCES Types;

CREATE TABLE Parameters
    (ParametersID NUMBER NOT NULL,
        ObjectsID NUMBER NOT NULL,
        AttributesID NUMBER NOT NULL,
        NumbersData NUMBER,
        StringData VARCHAR2(50),
        DateData Date,
        Relation NUMBER
    );

ALTER TABLE Parameters ADD CONSTRAINT uniqueRelation UNIQUE (Relation);
ALTER TABLE Parameters ADD CONSTRAINT ParametersPK PRIMARY KEY(ParametersID);
ALTER TABLE Parameters ADD CONSTRAINT ParametersFKObjects FOREIGN KEY(ObjectsID) REFERENCES Objects;
ALTER TABLE Parameters ADD CONSTRAINT ParametersFKAttributes FOREIGN KEY(AttributesID) REFERENCES Attributes;

CREATE TABLE Relations
    (RelationsID NUMBER NOT NULL,
        Relation NUMBER NOT NULL,
        ObjectsID NUMBER);

ALTER TABLE Relations ADD CONSTRAINT RelationsPK PRIMARY KEY(RelationsID);
ALTER TABLE Relations ADD CONSTRAINT RelationsFKObjects FOREIGN KEY(ObjectsID) REFERENCES Objects;
ALTER TABLE Relations ADD CONSTRAINT RelationsFKParameters FOREIGN KEY(Relation) REFERENCES Parameters(Relation);

COMMIT;