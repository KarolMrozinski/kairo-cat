# Kairo — setup and run guide

This repository is intended to be a complete, reusable project snapshot that can be cloned from GitHub and rebuilt on any machine without relying on local-only files.

## Requirements

- Java JDK 25
- Maven 3.9.16
- IntelliJ IDEA 2026.2 (recommended, but optional)

## Clone the project from GitHub

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat
```

The project sources are stored in the nested folder `kairo-master`:

```powershell
cd .\kairo-master
```

## Set Java 25 in PowerShell

If JDK 25 is installed locally, point `JAVA_HOME` to it before building:

```powershell
$env:JAVA_HOME = "C:\Program Files\BellSoft\LibericaJDK-25"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -version
```

You should see Java 25 reported in the output.

## Verify Maven

```powershell
mvn -version
```

If Maven is installed correctly, it should report version 3.9.16.

## Build the project

From the `kairo-master` directory:

```powershell
mvn package
```

This will compile the application and place the runnable JAR in the `target` directory.

## Run the application

```powershell
java -jar .\target\kairo-*.jar
```

If the exact jar name is different, list the folder first:

```powershell
Get-ChildItem .\target
```

## Optional: run with direct Java compilation

```powershell
javac -d out $(Get-ChildItem -Recurse -Filter *.java -Path .\src\main\java | ForEach-Object { $_.FullName })
java -cp out Kairo
```

## If the local folder is deleted

Because the project is preserved in GitHub, you can always recover it with:

```powershell
git clone https://github.com/KarolMrozinski/kairo-cat.git
cd .\kairo-cat\kairo-master
mvn package
```

This gives you a clean, rebuildable version of the project without needing any local-only files.
