# Kairo - instrukcja uruchomienia i działania

Ten folder zawiera dokumentację uruchamiania, pracy aplikacji i konfiguracji Kairo.

## Co robi aplikacja

Kairo to mały desktopowy towarzysz w stylu Swing, który:

- śledzi pozycję myszy na ekranie,
- porusza się po pulpicie z lekkim opóźnieniem i animacją,
- przechodzi między stanami aktywności i odpoczynku,
- reaguje na ruch czy brak ruchu myszy,
- zmienia zachowanie według trybu aktywności (`calm`, `active`, `playful`, `curious`, `sleepy`, `mischievous`),
- może być ustawiany w trybie okna, floatingu albo trybie osadzenia na pulpicie.

Aplikacja jest przeznaczona do pracy jako niewielki "kot na pulpicie" — nie jest to framework, nie ma serwera i nie wymaga bazy danych.

## Jak to działa

Dla początkującego użytkownika zobacz [BEGINNER.md](./BEGINNER.md).
Dla dewelopera zobacz [DEVELOPER.md](./DEVELOPER.md).
Dla szybki start 5 minut zobacz [START-HERE-5-MINUTES.md](./START-HERE-5-MINUTES.md).

Po starcie program:

1. ładuje konfigurację z zasobów projektu (`src/main/resources/kairo.properties`),
2. sprawdza, czy w katalogu użytkownika istnieje nadpisanie `~/kairo.properties`,
3. tworzy okno aplikacji i kontroler zwierzęcia,
4. nasłuchuje położenia kursora,
5. oblicza odległość do myszy, a następnie decyduje o ruchu lub stanie spoczynku,
6. przechodzi w stan "sen" lub "neutralny" przy braku aktywności,
7. pozwala użytkownikowi zmieniać ustawienia z menu kontekstowego / prawym kliknięciem.

## Uruchomienie

### 1. Wymagania

- Java JDK 25
- Maven 3.9.16
- opcjonalnie: IntelliJ IDEA 2026.2

### 2. Klonowanie repo

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat
cd .\kairo-master
```

### 3. Ustawienie JDK 25

```powershell
$env:JAVA_HOME = "C:\Program Files\BellSoft\LibericaJDK-25"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -version
```

### 4. Budowanie projektu

```powershell
mvn package
```

### 5. Uruchomienie aplikacji

```powershell
java -jar .\target\kairo-*.jar
```

Jeżeli chcesz uruchomić bez JAR-a, możesz też odpalić klasę `Kairo` z IntelliJ lub ręcznie przez javac.

## Tryby pracy aplikacji

Aplikacja ma kilka ważnych zachowań:

- `Follow mouse` – kot podąża za kursorem,
- `Idle reactions` – reaguje na bezczynność,
- `Tail swing` – animuje ogon,
- `Click-to-toggle` – kliknięcie przełącza tryb okna,
- `Smart monitor bounds` – uwzględnia granice monitora,
- `Window transparency` – przezroczystość okna,
- `Compact mode` – bardziej oszczędny wygląd,
- `Keep off editor` – odstęp od obszaru edytora,
- `Context menu` – menu kontekstowe.

## Jak włączyć ustawienia

Kliknij prawym przyciskiem myszy na kota lub na jego oknie, aby otworzyć okno ustawień. Z poziomu okna ustawień możesz zmieniać:

- Trigger distance
- Catch distance
- Run distance
- Offset X / Offset Y
- Cat scale
- Motion intensity
- Desktop margin
- Editor padding
- Activity mode
- Personality profile

## Kontrola zachowania

Najważniejsze wartości wpływające na działanie:

- `triggerDistance` – odległość, po której kot budzi się od spania,
- `runDistancePerFrame` – prędkość ruchu w pikselach na klatkę,
- `offsetx` / `offsety` – przesunięcie celu ruchu względem myszy,
- `catchDistance` – margines, gdy kot ma już prawie dogonić kursor,
- `sleepDelay`, `yawnDelay`, `surpriseDelay` – opóźnienia animacji.

## Gdzie są pliki konfiguracyjne

Główna konfiguracja projektu znajduje się tutaj:

- `src/main/resources/kairo.properties`

Dodatkowe ustawienia użytkownika mogą być zapisane do pliku:

- `%USERPROFILE%\kairo.properties` na Windowsie,
- `~/kairo.properties` na Linux/macOS.

To zachowanie jest realizowane przez klasę `Settings`, która najpierw wczytuje zasoby wbudowane, a potem nadpisuje je wartościami z pliku użytkownika.

## Ważne informacje

- Wartości z pliku `~/kairo.properties` mają pierwszeństwo przed ustawieniami domyślnymi.
- Plik `kairo.properties` jest odczytywany przy starcie aplikacji i po zmianach trybu okna.
- Aplikacja nie wymaga zewnętrznej bazy ani usługi sieciowej.
- Repo jest przeznaczone do łatwego odzyskania z GitHub.

## Dalsze sprawdzenie

Jeżeli chcesz dodatkowo ustawić własne parametry, zobacz:

- [CONFIGURATION.md](./CONFIGURATION.md)
- [kairo.properties.example](./kairo.properties.example)
