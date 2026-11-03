# Kairo - szybki start od zera do uruchomienia w 5 minut

To jest najkrótsza instrukcja, która pozwala od razu zbudować i uruchomić aplikację.

## 1. Zainstaluj wymagania

- JDK 25
- Maven 3.9.16

## 2. Sklonuj projekt

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat\kairo-master
```

## 3. Ustaw Java 25

```powershell
$env:JAVA_HOME = "C:\Program Files\BellSoft\LibericaJDK-25"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

## 4. Zbuduj aplikację

```powershell
mvn package
```

## 5. Uruchom aplikację

```powershell
java -jar .\target\kairo-*.jar
```

## 6. Gotowe

Po chwili pojawi się Kairo na pulpicie.

## 7. Najważniejsze ustawienia

Plik z konfiguracją domyślną:

```text
src/main/resources/kairo.properties
```

Własne ustawienia użytkownika:

```text
C:\Users\<twoja_nazwa>\kairo.properties
```

## 8. Jeśli chcesz szybko sprawdzić działanie

- poruszaj myszą,
- kot powinien podążać za kursorem,
- przy braku ruchu powinien przejść do stanu odpoczynku,
- kliknij prawym przyciskiem na kocie, aby otworzyć ustawienia.

## 9. Jeśli pojawią się błędy

Sprawdź, czy:

- JDK 25 jest poprawnie ustawione,
- Maven 3.9.16 jest zainstalowany,
- jesteś w katalogu `kairo-master`,
- komenda `mvn package` wykonuje się bez błędów.

To jest najprostsza ścieżka do uruchomienia aplikacji bez dodatkowych komplikacji.
