# accessApp

`accessApp` to system do zarządzania dostępem do pomieszczeń oraz rezerwacjami. Aplikacja została przygotowana jako projekt Spring Boot i odpowiada za obsługę użytkowników, budynków, biur, rezerwacji oraz urządzeń związanych z kontrolą dostępu.

## Do czego służy ta część projektu?

Ta część projektu pełni rolę głównej aplikacji serwerowej. Jej zadaniem jest przechowywanie danych, obsługa logiki biznesowej oraz udostępnianie funkcji potrzebnych do działania systemu kontroli dostępu.

Aplikacja pozwala między innymi na:

- zarządzanie użytkownikami,
- obsługę ról i uprawnień,
- zarządzanie budynkami oraz biurami,
- tworzenie i kontrolowanie rezerwacji,
- generowanie oraz obsługę danych dostępowych,
- komunikację z elementami systemu kontroli wejścia,
- obsługę czujników i zamków,
- wysyłanie wiadomości e-mail związanych z dostępem lub rezerwacjami.

## Główne funkcjonalności

### Użytkownicy i bezpieczeństwo

Aplikacja posiada mechanizmy logowania oraz obsługi użytkowników. Dostęp do wybranych funkcji może być kontrolowany na podstawie przypisanych ról.

### Rezerwacje

System umożliwia tworzenie i zarządzanie rezerwacjami pomieszczeń. Rezerwacja może być powiązana z konkretnym użytkownikiem, biurem oraz terminem.

### Budynki i biura

Aplikacja przechowuje informacje o budynkach oraz znajdujących się w nich biurach. Dzięki temu możliwe jest przypisywanie rezerwacji do konkretnych lokalizacji.

### Kontrola dostępu

Projekt obsługuje elementy związane z fizycznym dostępem do pomieszczeń, takie jak:

- zamki,
- kontaktrony,
- czujniki PIR,
- dane dostępowe użytkowników.

Dzięki temu aplikacja może stanowić centralny element systemu sterowania dostępem.

### Powiadomienia e-mail

Aplikacja posiada obsługę wysyłki wiadomości e-mail, która może być wykorzystywana na przykład do przesyłania informacji o rezerwacji, przypomnień lub danych dostępowych.

## Technologie

Projekt wykorzystuje:

- Java 17,
- Spring Boot,
- Spring Web,
- Spring Data JPA,
- Spring Security,
- Thymeleaf,
- PostgreSQL,
- Maven,
- Lombok.

## Baza danych

Aplikacja korzysta z bazy danych PostgreSQL.
