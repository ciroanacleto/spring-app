create table if not exists usuario
(
    codigo bigint       not null,
    nome   varchar(50)  not null,
    email  varchar(50)  not null,
    senha  varchar(150) not null
);

create unique index if NOT EXISTS usuario_codigo_uindex
    on usuario (codigo);

ALTER TABLE usuario
    DROP CONSTRAINT IF EXISTS usuario_pk;
alter table if exists usuario
    add constraint usuario_pk primary key (codigo);

create table if not exists permissao
(
    codigo    bigint      not null,
    descricao varchar(50) not null
);

create unique index if not exists permissao_codigo_uindex on permissao (codigo);

alter table if exists permissao
    drop constraint if exists permissao_pk;
alter table if exists permissao
    add constraint permissao_pk primary key (codigo);

create table if not exists usuario_permissao
(
    codigo_usuario   bigint not null
        constraint codigo_usuario_fk
            references usuario (codigo),
    codigo_permissao bigint not null
        constraint codigo_permissao_fk
            references permissao (codigo),
    constraint usuario_permissao_pk
        primary key (codigo_usuario, codigo_permissao)
);

----

INSERT INTO usuario (codigo, nome, email, senha)
select 1, 'Administrador', 'admin@spring.com', '$2a$10$uqEk5ma018hu4.bvbNvyo.fS.3MqkLaBCNbbNCFsStnv4ZhYDacga'
where not exists(select 1 from usuario where codigo = 1);

INSERT INTO usuario (codigo, nome, email, senha)
select 2, 'Maria', 'maria@spring.com', '$2a$10$OHGiKaf2AhGXok4GOFBlS.WNDs6ykfhvMzVysM55H9jcAMqBZnyx.'
where not exists(select 1 from usuario where codigo = 2);

INSERT INTO permissao (codigo, descricao)
select 1, 'ROLE_CADASTRAR_CATEGORIA'
where not exists(select 1 from permissao where codigo = 1);

INSERT INTO permissao (codigo, descricao)
select 2, 'ROLE_PESQUISAR_CATEGORIA'
where not exists(select 1 from permissao where codigo = 2);

INSERT INTO permissao (codigo, descricao)
select 3, 'ROLE_CADASTRAR_PESSOA'
where not exists(select 1 from permissao where codigo = 3);

INSERT INTO permissao (codigo, descricao)
select 4, 'ROLE_REMOVER_PESSOA'
where not exists(select 1 from permissao where codigo = 4);

INSERT INTO permissao (codigo, descricao)
select 5, 'ROLE_PESQUISAR_PESSOA'
where not exists(select 1 from permissao where codigo = 5);

INSERT INTO permissao (codigo, descricao)
select 6, 'ROLE_CADASTRAR_LANCAMENTO'
where not exists(select 1 from permissao where codigo = 6);

INSERT INTO permissao (codigo, descricao)
select 7, 'ROLE_REMOVER_LANCAMENTO'
where not exists(select 1 from permissao where codigo = 7);

INSERT INTO permissao (codigo, descricao)
select 8, 'ROLE_PESQUISAR_LANCAMENTO'
where not exists(select 1 from permissao where codigo = 8);

---- ADMIN

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 1
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 1);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 2
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 2);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 3
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 3);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 4
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 4);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 5
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 5);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 6
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 6);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 7
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 7);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 1, 8
where not exists(select 1 from usuario_permissao where codigo_usuario = 1 and codigo_permissao = 8);

---- MARIA

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 2, 2
where not exists(select 1 from usuario_permissao where codigo_usuario = 2 and codigo_permissao = 2);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 2, 5
where not exists(select 1 from usuario_permissao where codigo_usuario = 2 and codigo_permissao = 5);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
select 2, 8
where not exists(select 1 from usuario_permissao where codigo_usuario = 2 and codigo_permissao = 8);