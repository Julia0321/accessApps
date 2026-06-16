# accessApps

`accessApps` to projekt systemu kontroli dostępu do pomieszczeń, łączący aplikację serwerową z częścią sprzętową opartą o Arduino. Celem projektu jest umożliwienie zarządzania użytkownikami, rezerwacjami pomieszczeń oraz fizycznym dostępem do wybranych biur lub stref.

System składa się z dwóch głównych części:

- aplikacji backendowej `accessApp`,
- modułu sprzętowego `accessAppArduino`.

## Cel projektu

Projekt został stworzony jako system wspierający kontrolę dostępu do pomieszczeń. Może być wykorzystywany na przykład w biurach, salach, laboratoriach lub innych przestrzeniach, w których dostęp powinien być ograniczony i zależny od użytkownika lub aktywnej rezerwacji.

Główna idea systemu polega na tym, że użytkownik może otrzymać dostęp do konkretnego pomieszczenia na podstawie utworzonej rezerwacji lub przypisanych uprawnień. Aplikacja zarządza danymi, a część sprzętowa odpowiada za obsługę fizycznych elementów systemu, takich jak czujniki lub mechanizmy zamykania drzwi.

## Główne funkcjonalności

System umożliwia:

- zarządzanie użytkownikami,
- obsługę ról i uprawnień,
- zarządzanie budynkami oraz biurami,
- tworzenie i obsługę rezerwacji pomieszczeń,
- generowanie danych dostępowych,
- kontrolę dostępu do pomieszczeń,
- obsługę elementów sprzętowych,
- komunikację między aplikacją a urządzeniem,
- wysyłanie powiadomień e-mail,
- monitorowanie stanu wybranych czujników.

### Backend

- Java 17,
- Spring Boot,
- Spring Web,
- Spring Data JPA,
- Spring Security,
- Thymeleaf,
- PostgreSQL,
- Maven,
- Lombok.

### Część sprzętowa

- Arduino,
- język Arduino/C++,
- czujniki i elementy wykonawcze związane z kontrolą dostępu.

## Wymagania

Do uruchomienia projektu potrzebne są:

- Java 17 lub nowsza,
- Maven lub dołączony Maven Wrapper,
- PostgreSQL,
- Arduino IDE lub inne środowisko obsługujące pliki `.ino`,
- płytka Arduino zgodna z użytym programem,
- wymagane elementy elektroniczne, np. czujniki, zamek lub moduły komunikacyjne.
