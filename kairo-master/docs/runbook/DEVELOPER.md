# Kairo - instrukcja dla dewelopera

Ten dokument jest przeznaczony dla osób, które chcą rozwijać projekt, modyfikować konfigurację i uruchamiać aplikację z poziomu projektu Java.

## 1. Cel projektu

Kairo jest małym desktopowym aplikacyjnym towarzyszem napisanym w Java Swing. Projekt ma zachować główną logikę działania bez rozbudowy do osobnej platformy, backendu czy bazy danych.

## 2. Wymagania środowiska

- JDK 25
- Maven 3.9.16
- IntelliJ IDEA 2026.2 (opcjonalnie, ale rekomendowane)
- Git

## 3. Struktura projektu

Najważniejsze katalogi:

```text
kairo-master/
├── src/
│   ├── main/java/
│   └── main/resources/
├── docs/
├── pom.xml
├── README.md
└── LICENSE
```

Najważniejsze klasy:

- `Kairo` – główny punkt wejścia aplikacji,
- `KairoController` – logika ruchu, trybów stanów i reakcji,
- `KairoSettings` – ustawienia i zapis stanu,
- `Settings` – wczytywanie właściwości z pliku zasobów i pliku użytkownika.

## 4. Uruchamianie z poziomu Maven

```powershell
cd .\kairo-master
mvn package
java -jar .\target\kairo-*.jar
```

## 5. Uruchamianie w IntelliJ IDEA

1. Otwórz katalog `kairo-master` jako projekt.
2. Ustaw SDK na JDK 25.
3. Otwórz klasę `Kairo`.
4. Kliknij Run/Debug.
5. Zadbaj o poprawne ustawienie `main class`.

## 6. Konfiguracja runtime

Główna konfiguracja jest w:

```text
src/main/resources/kairo.properties
```

Dodatkowe ustawienia użytkownika mogą być zapisane w:

```text
C:\Users\<username>\kairo.properties
```

To zachowanie jest realizowane przez `Settings`, który najpierw wczytuje zasoby wbudowane, a następnie nadpisuje wartości z katalogu użytkownika.

## 7. Kluczowe parametry

Najważniejsze parametry wpływające na zachowanie:

```properties
windowTitle=Kairo Cat
triggerDistance=64
runDistancePerFrame=16
offsetx=-2
offsety=-3
catchDistance=2
maxFramerate=10
runFramerate=8
sitFramerate=3
sleepDelay=1200
yawnDelay=1500
surpriseDelay=1000
```

## 8. Zachowanie aplikacji

Aplikacja korzysta z trybów aktywności:

- `calm`
- `active`
- `playful`
- `curious`
- `sleepy`
- `mischievous`

Tryby te są obsługiwane w `KairoController`, a ustawienia są dostępne w oknie konfiguracji aplikacji.

## 9. Dodatkowe obserwacje dla rozwoju

Podczas pracy z projektem warto pamiętać:

- nie zmieniać zasadniczo mechaniki ruchu bez potrzeby,
- nie dodawać nowych warstw technologicznych bez uzasadnienia,
- zachowywać kompatybilność z JDK 25,
- nie usuwać funkcjonalności, która w praktyce jest już działająca,
- robić lokalne testy po każdej zmianie zachowania.

## 10. Podstawowe testy po modyfikacji

Po wprowadzeniu zmian:

```powershell
mvn clean package
```

Jeżeli w IntelliJ, uruchom projekt i sprawdź:

- czy kot nadal podąża za myszą,
- czy tryby aktywności działają,
- czy okno ustawień otwiera się bez błędów,
- czy rajd po pulpicie nie powoduje problemów z ramką okna.

## 11. Dobre praktyki

- trzym się prostoty JVM/Swing,
- modyfikuj ustawienia przez `~/kairo.properties` zamiast zmieniać domyślne pliki zasobów,
- zapisuj zmiany w Git lokalnie,
- nie rozbudowuj projektu w kierunku backendu lub frameworków, jeśli nie są potrzebne.

## 12. Szybki start dewelopera

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat\kairo-master
mvn clean package
```

Następnie uruchom klasę `Kairo` w IntelliJ lub:

```powershell
java -jar .\target\kairo-*.jar
```
