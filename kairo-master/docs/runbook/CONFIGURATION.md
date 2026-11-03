# Kairo - konfiguracja aplikacji

Ten dokument opisuje, jak działa konfiguracja Kairo i jakie ustawienia mogą być modyfikowane lokalnie.

## Główne pliki konfiguracyjne

### 1. Domyślna konfiguracja projektu

```text
src/main/resources/kairo.properties
```

To jest plik źródłowy z ustawieniami domyślnymi.

### 2. Nadpisanie użytkownika

Na systemie Windows plik nadpisujący może znajdować się pod ścieżką:

```text
C:\Users\<nazwa_uzytkownika>\kairo.properties
```

Na Linux/macOS:

```text
~/kairo.properties
```

Jeżeli taki plik istnieje, ma wyższy priorytet niż konfiguracja z zasobów projektu.

## Przykład pliku konfiguracyjnego

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
sharpenFramerate=3
scratchFramerate=10
loadFramerate=20
sleepDelay=1200
yawnDelay=1500
surpriseDelay=1000
```

## Najważniejsze ustawienia

### UI / nazwa okna

```properties
windowTitle=Kairo Cat
```

Zmienia nazwę okna aplikacji.

### Ruch i odległości

```properties
triggerDistance=64
runDistancePerFrame=16
catchDistance=2
offsetx=-2
offsety=-3
```

- `triggerDistance` – odległość, przy której kot budzi się lub przestaje być w stanie drzemki,
- `runDistancePerFrame` – prędkość poruszania się kota na klatkę,
- `offsetx` / `offsety` – przesunięcie celu względem kursora,
- `catchDistance` – margines niepewności przy doganianiu kursora.

### Animacja i framery

```properties
maxFramerate=10
runFramerate=8
sitFramerate=3
sharpenFramerate=3
scratchFramerate=10
loadFramerate=20
```

To są ustawienia szybkości animacji i liczby klatek na sekundę.

### Opóźnienia animacji

```properties
sleepDelay=1200
yawnDelay=1500
surpriseDelay=1000
```

Ustawienia czasów odpoczynku oraz reakcji na wydarzenia.

## Jak zmieniać konfigurację w praktyce

1. Otwórz plik `src/main/resources/kairo.properties` albo utwórz własny `~/kairo.properties`.
2. Zmień wybrane wartości.
3. Uruchom aplikację ponownie.
4. Sprawdź, czy zmiana została załadowana.

## Jak sprawdzić, czy plik użytkownika działa

W pliku `kairo.properties` jest zakomentowana opcja:

```properties
# hello=Hello, world!
```

Możesz ją odkomentować i ustawić własną wiadomość, aby sprawdzić, czy plik z nadpisaniem jest faktycznie używany.

## Uwaga dotycząca modyfikacji

Zmienianie konfiguracji w `src/main/resources` jest wygodne do testów, ale przy własnych ustawieniach lepiej korzystać z pliku w katalogu użytkownika:

```text
~/kairo.properties
```

Dzięki temu dane nie będą nadpisywane przy aktualizacji projektu i łatwiej utrzymać lokalną konfigurację.
