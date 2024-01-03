create table recipe
(
    id         UUID                     NOT NULL,
    version    INTEGER,
    created_at TIMESTAMP with time zone NOT NULL,
    updated_at TIMESTAMP with time zone NOT NULL,
    owner_id   UUID                     NOT NULL,
    title      varchar(511),
    state      varchar(255),
    PRIMARY KEY (id)
);


create table ingredient_type
(
    id           UUID                     NOT NULL,
    version      INTEGER,
    created_at   TIMESTAMP with time zone NOT NULL,
    updated_at   TIMESTAMP with time zone NOT NULL,
    name         varchar(511) unique      NOT NULL,
    default_unit varchar(255),
    PRIMARY KEY (id)
);


create table ingredient
(
    id                  UUID                     NOT NULL,
    version             INTEGER,
    created_at          TIMESTAMP with time zone NOT NULL,
    updated_at          TIMESTAMP with time zone NOT NULL,
    amount              INTEGER                  NOT NULL,
    unit                varchar(255)             NOT NULL,
    ingredient_group_id UUID                     NOT NULL,
    ingredient_type_id  UUID                     NOT NULL,
    PRIMARY KEY (id)
);



create table ingredient_group
(
    id         UUID                     NOT NULL,
    version    INTEGER,
    created_at TIMESTAMP with time zone NOT NULL,
    updated_at TIMESTAMP with time zone NOT NULL,
    recipe_id  UUID                     NOT NULL,
    name       varchar(255),
    position   INTEGER                  NOT NULL,
    PRIMARY KEY (id)
);

alter table ingredient add constraint fk_ingredient_recipe_ingredient_id foreign key (ingredient_type_id) references ingredient_type (id);
alter table ingredient_group add constraint fk_ingredient_group_recipe_id foreign key (recipe_id) references recipe (id);
