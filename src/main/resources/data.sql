
-- Populando temperaturas para os últimos 7 dias

INSERT INTO temperature_record (date, region, temperature) VALUES
                                                               ('2025-05-24', 'Centro', 29.5),
                                                               ('2025-05-24', 'Zona Norte', 30.2),
                                                               ('2025-05-25', 'Centro', 30.1),
                                                               ('2025-05-25', 'Zona Norte', 31.0),
                                                               ('2025-05-26', 'Centro', 29.8),
                                                               ('2025-05-26', 'Zona Norte', 30.5),
                                                               ('2025-05-27', 'Centro', 28.9),
                                                               ('2025-05-27', 'Zona Norte', 29.3),
                                                               ('2025-05-28', 'Centro', 27.5),
                                                               ('2025-05-28', 'Zona Norte', 28.0),
                                                               ('2025-05-29', 'Centro', 26.8),
                                                               ('2025-05-29', 'Zona Norte', 27.2),
                                                               ('2025-05-30', 'Centro', 27.1),
                                                               ('2025-05-30', 'Zona Norte', 28.4);


-- Populando alertas por região

INSERT INTO alert_record (date, region) VALUES
                                            ('2025-05-24', 'Centro'),
                                            ('2025-05-24', 'Centro'),
                                            ('2025-05-24', 'Zona Norte'),
                                            ('2025-05-25', 'Centro'),
                                            ('2025-05-25', 'Zona Norte'),
                                            ('2025-05-25', 'Zona Norte'),
                                            ('2025-05-26', 'Centro'),
                                            ('2025-05-27', 'Centro'),
                                            ('2025-05-27', 'Zona Sul'),
                                            ('2025-05-28', 'Zona Sul'),
                                            ('2025-05-29', 'Zona Norte'),
                                            ('2025-05-29', 'Zona Norte'),
                                            ('2025-05-30', 'Centro'),
                                            ('2025-05-30', 'Zona Norte');



-- Adicionando usuário por padrão: carlos@breeze.com.br / 123456

INSERT INTO users (full_name, email, password)
VALUES ('Carlos', 'carlos@breeze.com.br', '$2b$12$Up4pY0WJ6yJz2lYyI/uXFeCpww9lNYWX79QKHYb4Y9A2Cv22QuMmi');
