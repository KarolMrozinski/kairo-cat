# Kairo

Kairo is a tiny desktop companion that follows your mouse pointer around the screen, naps when idle, and can be toggled between floating overlay mode and windowed mode.

This project is a rebranded fork of the classic desktop cat toy idea, adapted into a Java Swing application.

## Features

- Follows the mouse cursor across the desktop
- Supports windowed and floating modes
- Idle animations, scratching, yawning, and sleeping states
- Works with multiple monitors via screen-aware positioning
- Simple configuration via `kairo.properties`
- Lightweight Swing-based implementation

## Project structure

- `src/main/java/` — application source code
- `src/main/resources/` — configuration and runtime assets
- `docs/` — documentation and packaged release artifact
- `pom.xml` — Maven build configuration

## Requirements

- Java JDK 8 or newer
- Maven (optional, recommended)

## Run

### Using Maven

```bash
mvn package
java -jar target/kairo-*.jar
```

### Direct Java compilation

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out Kairo
```

## Configuration

The behavior is configurable through `src/main/resources/kairo.properties`.

Key settings include:

- `windowTitle`
- `triggerDistance`
- `runDistancePerFrame`
- `offsetx` / `offsety`
- animation timing values

## Credits

- Original concept: classic desktop cat / neko-style cursor toy
- Java port inspiration: Werner Randelshofer
- This fork: Gerald Reno and contributors
- Image assets: public domain / historical material

## License

This project is released under the Unlicense.

See [LICENSE](LICENSE) for details.

