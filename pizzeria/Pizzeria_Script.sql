INSERT INTO  db_pizzeria.pizza (descrizione, foto, nome, price)
values ("pomodoro san marzano e mozzarella fior di latte", "/img/margherita.jpeg", "Margherita", "5");

SELECT  *
from db_pizzeria.pizza p ;

DELETE FROM db_pizzeria.pizza
WHERE id=2;

DELETE FROM db_pizzeria.pizza
WHERE id=1;

DELETE FROM db_pizzeria.pizza
WHERE id=3;

INSERT INTO  db_pizzeria.pizza (descrizione, foto, nome, price)
values ("pomodoro san marzano e mozzarella fior di latte", "/img/margherita.jpeg", "Margherita", "5"),
("pomodoro, mozzarella, melanzane fritte, ricotta salata, basilico", "", "Norma", "8"),
("pesto di pistacchio, mozzarella, mortadella, crema di pistacchio, granella di pistacchio", "", "Pistacchiosa", "10"),
("pesto di basilico, datterino confit, basilico", "", "Basilico", "7");

INSERT INTO  db_pizzeria.pizza (descrizione, foto, nome, price)
values ("pomodoro, mozzarella, melanzane fritte, ricotta salata, basilico", "", "Norma", "8");

DELETE FROM db_pizzeria.pizza
WHERE id=10
OR id=11
or id=12
or id=13;

UPDATE db_pizzeria.pizza
SET descrizione = 'pomodoro, mozzarella, melanzane fritte, ricotta salata, basilico', 
    foto = '/img/norma.png', 
    nome = 'Norma', 
    price = 8
WHERE id = 9;

UPDATE db_pizzeria.pizza
SET descrizione = 'pesto di pistacchio, mozzarella, mortadella, crema di pistacchio, granella di pistacchio', 
    foto = '/img/pistacchiosa.png', 
    nome = 'Pistacchiosa', 
    price = 12
WHERE id = 7;

UPDATE db_pizzeria.pizza
SET descrizione = 'pesto di basilico, datterino confit, basilico', 
    foto = '/img/basilico.jpg', 
    nome = 'Basilico', 
    price = 7
WHERE id = 8;

INSERT INTO  db_pizzeria.pizza (descrizione, foto, nome, price)
values ("pomodoro, mozzarella, melanzane fritte, ricotta salata, basilico", "/img/norma.png", "Norma", "8");


