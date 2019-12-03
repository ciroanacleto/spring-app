alter table pessoa
    add column if not exists data_criacao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists data_alteracao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists usuario_criacao bigint,
    add column if not exists usuario_alteracao bigint,
    drop constraint if exists usuario_criacao_fk,
    drop constraint if exists usuario_alteracao_fk,
    add constraint usuario_criacao_fk foreign key (usuario_criacao) references usuario,
    add constraint usuario_alteracao_fk foreign key (usuario_alteracao) references usuario;

alter table lancamento
    add column if not exists data_criacao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists data_alteracao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists usuario_criacao bigint,
    add column if not exists usuario_alteracao bigint,
    drop constraint if exists usuario_criacao_fk,
    drop constraint if exists usuario_alteracao_fk,
    add constraint usuario_criacao_fk foreign key (usuario_criacao) references usuario,
    add constraint usuario_alteracao_fk foreign key (usuario_alteracao) references usuario;

alter table categoria
    add column if not exists data_criacao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists data_alteracao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists usuario_criacao bigint,
    add column if not exists usuario_alteracao bigint,
    drop constraint if exists usuario_criacao_fk,
    drop constraint if exists usuario_alteracao_fk,
    add constraint usuario_criacao_fk foreign key (usuario_criacao) references usuario,
    add constraint usuario_alteracao_fk foreign key (usuario_alteracao) references usuario;

alter table usuario
    add column if not exists data_criacao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists data_alteracao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists usuario_criacao bigint,
    add column if not exists usuario_alteracao bigint,
    drop constraint if exists usuario_criacao_fk,
    drop constraint if exists usuario_alteracao_fk,
    add constraint usuario_criacao_fk foreign key (usuario_criacao) references usuario,
    add constraint usuario_alteracao_fk foreign key (usuario_alteracao) references usuario;

alter table permissao
    add column if not exists data_criacao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists data_alteracao timestamp without time zone NOT NULL DEFAULT now(),
    add column if not exists usuario_criacao bigint,
    add column if not exists usuario_alteracao bigint,
    drop constraint if exists usuario_criacao_fk,
    drop constraint if exists usuario_alteracao_fk,
    add constraint usuario_criacao_fk foreign key (usuario_criacao) references usuario,
    add constraint usuario_alteracao_fk foreign key (usuario_alteracao) references usuario;
