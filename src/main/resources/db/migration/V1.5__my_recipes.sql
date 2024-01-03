create table recipe_list
(
    id         UUID                     NOT NULL,
    version    INTEGER,
    created_at TIMESTAMP with time zone NOT NULL,
    updated_at TIMESTAMP with time zone NOT NULL,
    owner_id   VARCHAR(255)             NOT NULL,
    title      VARCHAR(255)             NOT NULL,
    PRIMARY KEY (id)
);

create table recipe_list_content
(
    id                UUID                     NOT NULL,
    version           INTEGER,
    created_at        TIMESTAMP with time zone NOT NULL,
    updated_at        TIMESTAMP with time zone NOT NULL,
    recipe_id         UUID                     NOT NULL,
    recipe_list_id UUID                     NOT NULL,
    PRIMARY KEY (id)
);


alter table recipe_list_content
    add constraint fk_recipe_recipe_list_recipe foreign key (recipe_id) references recipe;
alter table recipe_list_content
    add constraint fk_recipe_recipe_list_recipe_list foreign key (recipe_list_id) references recipe_list;