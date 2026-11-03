# Kairo - instrukcja dla początkującego użytkownika

To jest prosty przewodnik, jak uruchomić Kairo i zacząć z nim pracować bez komplikacji.

## 1. Co to jest Kairo?

Kairo to mały "kot na pulpicie", który porusza się za kursorem myszy. Może:

- podążać za myszą,
- odpoczywać, gdy nic się nie dzieje,
- zmieniać swoje zachowanie w zależności od aktywności,
- być konfigurowany przez prosty plik ustawień.

To nie jest aplikacja webowa, nie potrzebuje serwera i nie ma bazy danych.

## 2. Czego potrzebujesz?

Na Windowsie potrzebujesz:

- JDK 25
- Maven 3.9.16

Jeśli nie masz ich zainstalowanych, zainstaluj najpierw Java 25 i Maven.

## 3. Sklonuj projekt

W PowerShell wpisz:

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat
cd .\kairo-master
```

## 4. Ustaw Java 25

```powershell
$env:JAVA_HOME = "C:\Program Files\BellSoft\LibericaJDK-25"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -version
```

Powinno pokazać wersję Java 25.

## 5. Zbuduj aplikację

```powershell
mvn package
```

To uruchomi kompilację projektu.

## 6. Uruchom program

```powershell
java -jar .\target\kairo-*.jar
```

Po chwili pojawi się kot na pulpicie.

## 7. Jak go używać

- poruszaj myszą,
- kot będzie podążał za kursorem,
- jeśli nic się nie dzieje, przechodzi w stan odpoczynku,
- kliknij prawym przyciskiem myszy na kocie, aby otworzyć ustawienia,
- kliknij lewym przyciskiem, aby uaktywnić reakcję.

## 8. Gdzie są ustawienia

Najważniejszy plik konfiguracyjny to:

```text
src/main/resources/kairo.properties
```

Jeśli chcesz nadpisać ustawienia lokalnie, możesz utworzyć plik:

```text
C:\Users\<twoja_nazwa>\kairo.properties
```

## 9. Co możesz zmienić

Najczęściej zmieniasz:

- nazwę okna,
- prędkość ruchu kota,
- odległość od kursora,
- tryb zachowania (calm, active, playful, curious, sleepy, mischievous).

## 10. Jeśli coś nie działa

Sprawdź:

1. czy Java 25 jest poprawnie ustawiona,
2. czy Maven 3.9.16 jest zainstalowany,
3. czy komenda `mvn package` nie zwraca błędów,
4. czy jesteś w katalogu `kairo-master`.

## 11. Tylko najważniejsze rzeczy

Jeśli chcesz najprostszą ścieżkę:

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat\kairo-master
mvn package
java -jar .\target\kairo-*.jar
```

To wystarczy do uruchomienia aplikacji.
