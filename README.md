# Kairo

Kairo is a lightweight desktop companion for Java Swing. It follows your cursor, settles into idle moods, and can be used as either a floating overlay or a framed desktop window.

The project is designed around a calm, playful personality: Kairo feels observant, reactive, and quietly present rather than merely decorative.

## Features

- Follows the mouse cursor across the desktop
- Supports overlay and windowed presentation modes
- Idle behavior with scratching, yawning, and sleep-like states
- Personality modes: calm, active, and playful
- Multi-monitor-aware positioning and bounds handling
- Runtime configuration via `kairo.properties` and the in-app settings dialog
- Lightweight Swing implementation

## Project structure

- `src/main/java/` — application source code
- `src/main/resources/` — configuration and runtime assets
- `docs/` — supporting documentation and release notes
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

The app behaviour is configured from `src/main/resources/kairo.properties` and can also be adjusted through the in-app settings dialog.

Notable settings include:

- `windowTitle`
- `triggerDistance`
- `runDistancePerFrame`
- `offsetx` / `offsety`
- animation timing values
- `activityMode`

## Design direction

Kairo is intentionally presented as a desktop companion rather than a toy demo. The project emphasizes:

- gentle presence over noisy motion
- personality over raw animation speed
- clarity over legacy complexity
- a single, coherent brand identity across code, UI, and documentation

## License

This project is released under the LIMITED SOURCE-AVAILABLE & VIEW-ONLY LICENSE.

See [LICENSE](LICENSE) for details.

