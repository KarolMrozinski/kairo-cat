<h1 align="center">Kairo Cat</h1>

<p align="center">
  <img src="https://raw.githubusercontent.com/KarolMrozinski/kairo-cat/main/docs/Kairo_animated.gif" alt="Kairo desktop companion" width="520" />
</p>

<p align="center">
  <a href="#features"><img alt="Desktop companion" src="https://img.shields.io/badge/Type-Desktop%20Companion-8b5cf6" /></a>
  <a href="#requirements"><img alt="Java" src="https://img.shields.io/badge/Java-8%2B-007396" /></a>
  <a href="#license"><img alt="License" src="https://img.shields.io/badge/License-Limited-orange" /></a>
</p>

Kairo Cat is a polished desktop companion built in Java Swing. It lives on your screen as a calm, observant cat that follows your cursor, reacts to your presence, and settles into distinct idle moods depending on your working rhythm.

The project is intentionally designed to feel thoughtful rather than noisy: subtle motion, personality-driven behavior, and a clean visual identity make it feel more like a premium desktop companion than a simple demo app.

## Why this project feels different

Kairo is built to be present without being intrusive. It keeps its own personality while staying respectful of your workspace, moving around the desktop in a calm, natural way instead of feeling artificial or distracting.

<div align="center">
  <table>
    <tr>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #111827, #1f2937); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 14px 32px rgba(15,23,42,0.28); margin: 8px; color: white;">
          <h3>✨ Calm presence</h3>
          <p>Subtle motion and gentle reactions keep Kairo alive without stealing focus from the work at hand.</p>
        </div>
      </td>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #312e81, #2563eb); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 14px 32px rgba(37,99,235,0.26); margin: 8px; color: white;">
          <h3>🧠 Personality modes</h3>
          <p>Switch between calm, active, playful, curious, sleepy, and mischievous behavior to shape the cat’s mood.</p>
        </div>
      </td>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #0f766e, #14b8a6); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 14px 32px rgba(20,184,166,0.24); margin: 8px; color: white;">
          <h3>🪟 Desktop-aware behavior</h3>
          <p>Kairo supports floating and framed modes while respecting screen boundaries, active windows, and a respectful desktop rhythm.</p>
        </div>
      </td>
    </tr>
  </table>
</div>

## Features

- Follows the pointer across the desktop in a smooth, characterful way
- Supports both floating overlay and framed window modes
- Uses idle animations such as scratching, yawning, resting, and attention shifts
- Includes personality-driven activity profiles and mood transitions
- Adjusts behavior with safer bounds, smart margins, and responsive desktop logic
- Configurable through the in-app settings dialog and `kairo.properties`
- Built as a lightweight Java Swing project with minimal external dependencies

## Project structure

- `src/main/java/` — application logic, controller behavior, window logic, and UI bootstrap
- `src/main/resources/` — runtime assets and default settings
- `docs/` — supporting visuals and project documentation
- `pom.xml` — Maven project definition
- `LICENSE` — licensing and project posture

## Requirements

- Java JDK 8 or newer
- Maven (recommended, optional for simple builds)

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

The runtime behavior is driven by `src/main/resources/kairo.properties`, with additional tuning available in the in-app settings panel.

Key values include:

- `windowTitle`
- `triggerDistance`
- `runDistancePerFrame`
- `offsetx` / `offsety`
- animation timing values
- `activityMode`

## Design direction

Kairo is intentionally built as a desktop companion rather than a decorative toy. The product goal is simple:

- calm presence over noisy motion
- personality over raw animation speed
- clarity over legacy complexity
- a consistent visual identity across code, UI, and presentation

This keeps the experience fun, intentional, and polished without turning the project into something generic or over-engineered.

## Project status

This project is in a mature polish phase: the main behavior is stable, the personality system is in place, and the interface is intentionally shaped as a polished desktop experience rather than a prototype.

## License

This project is released under the LIMITED SOURCE-AVAILABLE & VIEW-ONLY LICENSE.

See [LICENSE](LICENSE) for more details.

