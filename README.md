# AccessApp

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
- wymagane elementy elektroniczne, czujnik PIR, kontaktron, przekaźnik z optoizolacją
- zamek elektromagnetyczny
- klawiatura numeryczna

## Schemat podłączenia

 <img width="642" height="647" alt="image" src="https://github.com/user-attachments/assets/8622933a-a86d-4907-9514-6d74730f205e" />

 ## Do uruchomienia projektu trzeba uzupełnić:
- **Adres IP backendu w Arduino** — w pliku `accessAppArduino/accessAppArduino.ino` należy ustawić adres IP komputera, na którym działa aplikacja Spring Boot, np. `192.168.1.100`.
- **Dane Wi-Fi dla Arduino** — należy utworzyć lub uzupełnić plik `arduino_secrets.h` i wpisać w nim nazwę sieci oraz hasło do Wi-Fi.
- **Adres IP Arduino w backendzie** — w aplikacji Spring Boot trzeba ustawić aktualny adres IP Arduino, aby backend mógł wysyłać do niego kod dostępu i komendy sterujące zamkiem.
- **Dane bazy PostgreSQL** — w pliku `application.yml` trzeba ustawić adres bazy danych, port, nazwę bazy, login i hasło.
- **Adres e-mail** — w pliku `application.yml` trzeba ustawić właściwy adres e-mail używany przez aplikację.
- **Port aplikacji** — domyślnie backend działa na porcie `8080`; jeśli zostanie zmieniony, ten sam port trzeba ustawić również w Arduino.
- **Kod wejścia / kod domyślny** — w Arduino można zmienić domyślny kod dostępu, obecnie ustawiony jako `12345`.
- **Piny podłączonych elementów Arduino** — jeśli czujnik PIR, kontaktron, klawiatura lub sterowanie zamkiem są podłączone do innych pinów, trzeba zmienić numery pinów w programie Arduino.
- **Adres i port PostgreSQL** — jeśli baza działa na innym porcie niż wpisany w konfiguracji, np. `5432` zamiast `5433`, trzeba to poprawić.
- **Uprawnienia/firewall** — trzeba upewnić się, że komputer z backendem pozwala na połączenia przychodzące na port `8080`, żeby Arduino mogło wysyłać dane do aplikacji.
- **Ta sama sieć dla Arduino i backendu** — Arduino oraz komputer z aplikacją muszą być podłączone do tej samej sieci Wi-Fi/LAN.
