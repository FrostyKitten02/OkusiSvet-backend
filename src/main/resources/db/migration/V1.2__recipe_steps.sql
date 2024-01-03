create table recipe_step
(
    id           UUID                     NOT NULL,
    version      INTEGER,
    created_at   TIMESTAMP with time zone NOT NULL,
    updated_at   TIMESTAMP with time zone NOT NULL,
    step_number  INTEGER                  NOT NULL,
    title        VARCHAR(255),
    instructions TEXT                     NOT NULL,
    recipe_id    UUID                     NOT NULL,
    PRIMARY KEY (id)
);

alter table recipe add column comment TEXT;


alter table recipe_step add constraint fk_recipe_step_recipe foreign key (recipe_id) references recipe (id);