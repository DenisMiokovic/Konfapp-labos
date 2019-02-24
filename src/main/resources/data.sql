insert into Korisnik (username, password, enabled)
 values ('admin', '$2a$04$p.60.3VGyzaJ3u9VTjqQ/.8iFdsxKhPLATv3gNfU6yAfYWPJuH.v2', 1);
 
insert into Korisnik (username, password, enabled)
 values ('student', '$2a$04$yLgQNZTQJrxO7l6g9DrgVeyVasGzbEae5ufn9HurPPbvvC3z4UifK', 1);
 
insert into KorisnikPrava (username, authority) values ('admin', 'ROLE_ADMIN');
insert into KorisnikPrava (username, authority) values ('admin', 'ROLE_USER');
insert into KorisnikPrava (username, authority) values ('student', 'ROLE_USER');