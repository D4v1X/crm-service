# --- !Ups

-- Account: User: superadmin, PASS: superadmin
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('42800f3f-718e-4daf-a7b7-40c7e547b5d6', 'superadmin', 'superadmin@gmail.com',
        '26b02e695a0c917d66e3e85580ebb578116b2bd1998cca8028815cc156b5df2a', 'd58a41cf-d882-40be-8278-cd8671141f95',
        'ADMIN');

-- Account: User: user, PASS: user
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('8abdff85-7a3a-41ee-9d4f-59e0bc686b40', 'user', 'user@gmail.com',
        'c0056e49bcdafe79bd823a54f1fb2ec29714b1cf52c62f0cc2fc91acc2318abb', 'dee03675-d8a3-4956-b543-3fb8d96b2c18',
        'USER');

-- Account: User: admin, PASS: admin
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('705477ee-f632-4b5c-b038-04296ef21236', 'admin', 'admin@gmail.com',
        'c7366ebba87d785ff5eaf99acf4750dae6dbdc7c1f711800066138ee3c4725d4', 'a23f232d-8eac-42e1-8c63-097175abec69',
        'ADMIN');

-- Account: User: change, PASS: change
INSERT INTO crm_schema."user" (uuid, name, email, password, secret, role)
VALUES ('ed9e44c5-fde3-465c-a97c-189d053ffc73', 'change', 'change@gmail.com',
        '4f32cbbb66ad3d02171458b970bf4f9c853944d036db439d27931780a56f0933', 'a52475a4-7509-489d-972a-90b5f06cee30',
        'ADMIN');

# --- !Downs

DELETE FROM crm_schema.user;