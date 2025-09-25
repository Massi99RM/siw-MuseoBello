-- UTENTI
INSERT INTO users (id, username, password, role) VALUES (1, 'Valerio', '$2a$10$/NxylownsxOMdl6zjhFkmOAGukyVWcweh.cwhi7wq1yaq.FcMo9G.', 'ADMIN');
INSERT INTO users (id, username, password, role) VALUES (2, 'Riccardo', '$2a$10$6KTe5Mj8EIL0WBTII3RZR.X1J3HqAuPDjtF6FS8ExuKBLiXM1vDMS', 'USER');

-- ARTISTI
INSERT INTO artists (id, name, biography, image_url) VALUES (1, 'Leonardo da Vinci', 'Pittore e inventore rinascimentale italiano.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Leonardo_self.jpg/440px-Leonardo_self.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (2, 'Vincent van Gogh', 'Pittore olandese post-impressionista.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_%28454045%29.jpg/440px-Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_%28454045%29.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (3, 'Pablo Picasso', 'Pittore e scultore spagnolo, padre del cubismo.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Pablo_picasso_1.jpg/440px-Pablo_picasso_1.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (4, 'Claude Monet', 'Fondatore dell impressionismo francese.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Claude_Monet_1899_Nadar_crop.jpg/440px-Claude_Monet_1899_Nadar_crop.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (5, 'Michelangelo Buonarroti', 'Scultore, pittore e architetto rinascimentale italiano.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Michelangelo_Daniele_da_Volterra_%28dettaglio%29.jpg/440px-Michelangelo_Daniele_da_Volterra_%28dettaglio%29.jpg');

-- SALE (ogni artista ha una sala unica)
INSERT INTO rooms (id, name, artist_id) VALUES (1, 'Sala Leonardo', 1);
INSERT INTO rooms (id, name, artist_id) VALUES (2, 'Sala Van Gogh', 2);
INSERT INTO rooms (id, name, artist_id) VALUES (3, 'Sala Picasso', 3);
INSERT INTO rooms (id, name, artist_id) VALUES (4, 'Sala Monet', 4);
INSERT INTO rooms (id, name, artist_id) VALUES (5, 'Sala Michelangelo', 5);

-- OPERE - Leonardo da Vinci
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (1, 'Mona Lisa', '1503', 'Ritratto famoso conservato al Louvre.', 1, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/687px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (2, 'L Ultima Cena', '1498', 'Affresco a Milano, Santa Maria delle Grazie.', 1, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/%C3%9Altima_Cena_-_Da_Vinci_5.jpg/1280px-%C3%9Altima_Cena_-_Da_Vinci_5.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (3, 'Uomo Vitruviano', '1490', 'Disegno iconico delle proporzioni umane.', 1, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Da_Vinci_Vitruve_Luc_Viatour.jpg/694px-Da_Vinci_Vitruve_Luc_Viatour.jpg');

-- OPERE - Vincent van Gogh
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (4, 'Notte stellata', '1889', 'Celebre dipinto post-impressionista.', 2, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg/1280px-Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (5, 'Girasoli', '1888', 'Serie di nature morte con girasoli.', 2, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Vincent_Willem_van_Gogh_127.jpg/785px-Vincent_Willem_van_Gogh_127.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (6, 'Campo di grano con corvi', '1890', 'Uno degli ultimi dipinti di Van Gogh.', 2, 2, 'https://www.artesvelata.it/wp-content/uploads/2020/05/Vincent-Van-Gogh-Campo-di-grano-con-volo-di-corvi-luglio-1890-Arte-Svelata.jpg');

-- OPERE - Pablo Picasso
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (7, 'Guernica', '1937', 'Grande murale che denuncia la guerra.', 3, 3, 'https://upload.wikimedia.org/wikipedia/en/thumb/7/74/PicassoGuernica.jpg/1280px-PicassoGuernica.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (8, 'Les Demoiselles d Avignon', '1907', 'Opera rivoluzionaria del cubismo.', 3, 3, 'https://upload.wikimedia.org/wikipedia/en/thumb/4/4c/Les_Demoiselles_d%27Avignon.jpg/640px-Les_Demoiselles_d%27Avignon.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (9, 'Il vecchio chitarrista', '1903', 'Dipinto del periodo blu.', 3, 3, 'https://www.arteworld.it/wp-content/themes/yootheme/cache/29/Il-vecchio-chitarrista-cieco-Pablo-Picasso-analisi-29750ed5.webp');

-- OPERE - Claude Monet
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (10, 'Impressione, levar del sole', '1872', 'Dipinto che ha dato il nome all impressionismo.', 4, 4, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Monet_-_Impression%2C_Sunrise.jpg/1280px-Monet_-_Impression%2C_Sunrise.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (11, 'Ninfee', '1916', 'Serie di dipinti con ninfee.', 4, 4, 'https://upload.wikimedia.org/wikipedia/commons/9/95/Claude_monet%2C_ninfee%2C_1916.JPG');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (12, 'La Cattedrale di Rouen', '1894', 'Serie di vedute della cattedrale.', 4, 4, 'https://www.analisidellopera.it/wp-content/uploads/2018/03/Monet_Cattedrale_Rouen_pieno_sole.jpg');

-- OPERE - Michelangelo Buonarroti
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (13, 'David', '1504', 'Famosa scultura in marmo.', 5, 5, 'https://www.girovagandoioete.it/wp-content/uploads/2020/06/Dove-si-trova-il-David-di-Michelangelo-il-David-650x479.jpg');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (14, 'La Creazione di Adamo', '1512', 'Affresco nella Cappella Sistina.', 5, 5, 'https://dq7j96arldrqf.cloudfront.net/cappellasistinaroma/images/la-creazione-di-adamo-cappella-sistina.webp');
INSERT INTO artworks (id, title, year, description, artist_id, room_id, image_url) VALUES (15, 'Pieta', '1499', 'Scultura in marmo nella Basilica di San Pietro.', 5, 5, 'https://upload.wikimedia.org/wikipedia/commons/f/f1/Michelangelo%27s_Piet%C3%A0%2C_St_Peter%27s_Basilica_%281498%E2%80%9399%29.jpg');

-- Reset sequence
SELECT setval('artists_seq', (SELECT MAX(id) FROM artists));
SELECT setval('artworks_seq', (SELECT MAX(id) FROM artworks));
SELECT setval('users_seq', (SELECT MAX(id) FROM users));
SELECT setval('rooms_seq', (SELECT MAX(id) FROM rooms));
