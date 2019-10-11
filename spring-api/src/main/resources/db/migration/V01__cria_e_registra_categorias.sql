CREATE TABLE if not exists public.categoria
(
    codigo SERIAL PRIMARY KEY NOT NULL,
    nome   VARCHAR(50)        NOT NULL
);
CREATE UNIQUE INDEX if not exists categoria_codigo_uindex ON public.categoria (codigo);

INSERT INTO categoria (nome)
select 'Lazer'
where not exists(select 1 from categoria where nome = 'Lazer');

INSERT INTO categoria (nome)
select 'Alimentação'
where not exists(select 1 from categoria where nome = 'Alimentação');

INSERT INTO categoria (nome)
select 'Supermercado'
where not exists(select 1 from categoria where nome = 'Supermercado');

INSERT INTO categoria (nome)
select 'Farmácia'
where not exists(select 1 from categoria where nome = 'Farmácia');

INSERT INTO categoria (nome)
select 'Outros'
where not exists(select 1 from categoria where nome = 'Outros');

