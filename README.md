
<h1 align="center">Kairo Cat</h1>

<p align="center">
  <img src="https://raw.githubusercontent.com/KarolMrozinski/kairo-cat/main/docs/Kairo_animated.gif" alt="Kairo desktop companion" width="520" />
</p>

<p align="center">
  <a href="#features"><img alt="Desktop companion" src="https://img.shields.io/badge/Type-Desktop%20Companion-8b5cf6" /></a>
  <a href="#requirements"><img alt="Java" src="https://img.shields.io/badge/Java-8%2B-007396" /></a>
  <a href="#license"><img alt="License" src="https://img.shields.io/badge/License-Limited-orange" /></a>
</p>

Kairo Cat is a Java Swing desktop companion that follows the cursor, reacts to activity, and brings personality to the desktop through a small animated cat character. It is designed as a functional desktop application with a polished presentation, smooth movement, and configurable behavior that can be adjusted in-app.

## Product overview

Kairo is a desktop companion application focused on personality, motion, and ambient presence. The cat moves around the screen, reacts to user activity, and switches between different behavior states such as active tracking, idling, and attention-based reactions. The app is built to feel alive without becoming disruptive, while still remaining a complete working desktop experience.

## How it works

The application runs as a lightweight desktop utility and continuously tracks the mouse cursor position. Based on the distance and behavior state, the cat decides whether to move toward the cursor, remain idle, or react to user interaction. It also respects screen edges and desktop boundaries so the character remains in a stable and usable position while moving across the environment.

Its behavior is driven by a set of runtime parameters and the current activity mode, which makes the app easy to tune and extend. The in-app settings dialog allows adjustments to movement, timing, offset values, and interaction preferences without altering the core logic of the program.

## Features

- Smooth pointer-following behavior with responsive motion
- Character-driven idle and activity states
- Animated desktop companion behavior with personality shifts
- Screen-aware movement and boundary handling
- Floating and framed desktop presentation modes
- In-app configuration for behavior tuning and runtime settings
- Lightweight Java Swing implementation with minimal external dependencies

## Project structure

- `src/main/java/` — application logic, controller behavior, and window bootstrap
- `src/main/resources/` — runtime assets and default properties
- `docs/` — supporting visual assets and project documentation
- `pom.xml` — Maven project definition
- `LICENSE` — project licensing and usage terms

## Requirements

- Java JDK 8 or newer
- Maven (recommended, optional for building the project)

## Run

### With Maven

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

The runtime behavior is controlled by `src/main/resources/kairo.properties`, and additional tuning is available through the in-app settings panel.

Key values include:

- `windowTitle`
- `triggerDistance`
- `runDistancePerFrame`
- `offsetx` / `offsety`
- animation timing values
- `activityMode`

## Project status

Kairo is a complete and working desktop companion application with polished motion, personality-driven behavior, and configuration support. It is structured as a functional Java desktop app and is suitable for presentation, experimentation, and further refinement as a user-facing desktop utility.

## License

This project is released under the LIMITED SOURCE-AVAILABLE & VIEW-ONLY LICENSE.

See [LICENSE](LICENSE) for more details.

