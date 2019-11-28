CREATE TABLE if NOT EXISTS public.pessoa
(
    codigo      SERIAL PRIMARY KEY NOT NULL,
    nome        VARCHAR(50)        NOT NULL,
    logradouro  VARCHAR(30),
    numero      VARCHAR(10),
    complemento VARCHAR(30),
    bairro      VARCHAR(30),
    cep         VARCHAR(8),
    cidade      VARCHAR(20),
    estado      VARCHAR(20),
    ativo       BOOLEAN DEFAULT TRUE
);
drop INDEX IF EXISTS pessoa_codigo_uindex;
CREATE UNIQUE INDEX pessoa_codigo_uindex ON public.pessoa (codigo);

INSERT INTO pessoa (nome)
select 'Fulano'
where not exists(select 1 from pessoa where nome = 'Fulano');

INSERT INTO pessoa (nome)
select 'Beltrano'
where not exists(select 1 from pessoa where nome = 'Beltrano');
