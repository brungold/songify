INSERT INTO users (email, password, authorities, confirmation_token, enabled)
VALUES
    ('maciej.m.mysliwiec@gmail.com', '$2a$10$.zuqWbRdJAoQqonsXd7VrOfXe//IVNfVmaM5dPt7uwpj0MBuOpSK.', '{ROLE_ADMIN, ROLE_USER}', 'e2f92bb5-8fb3-4a9b-b5a2-0f35c2d4a7a1', true),
    ('john@gmail.com', '$2a$10$.zuqWbRdJAoQqonsXd7VrOfXe//IVNfVmaM5dPt7uwpj0MBuOpSK.', '{ROLE_USER}', '3f416fe0-95b7-42a2-b8e8-d839e1b2d8a9', true);