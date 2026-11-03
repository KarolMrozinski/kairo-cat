<h1 align="center">Kairo Cat</h1>

<p align="center">
  <img src="https://raw.githubusercontent.com/KarolMrozinski/kairo-cat/main/docs/Kairo_animated.gif" alt="Kairo desktop companion" width="520" />
</p>

<p align="center">
  <a href="#features"><img alt="Desktop Companion" src="https://img.shields.io/badge/Type-Desktop%20Companion-8b5cf6" /></a>
  <a href="#requirements"><img alt="Java" src="https://img.shields.io/badge/Java-8%2B-007396" /></a>
  <a href="#license"><img alt="License" src="https://img.shields.io/badge/License-Limited-orange" /></a>
</p>

Kairo is a polished desktop companion built in Java Swing. It lives on your screen as a calm, observant cat that follows your cursor, reacts to your presence, and settles into different idle moods depending on the atmosphere.

This project is designed to feel less like a demo and more like a thoughtful desktop experience: subtle motion, personal behavior, and a clean identity across code, UI, and documentation.

## Why Kairo

Kairo is meant to be present without being intrusive. It reacts gently to movement, exhibits personality through idle behavior, and supports both floating-overlay and framed window modes.

<div align="center">
  <table>
    <tr>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #1f2937, #111827); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 10px 28px rgba(15,23,42,0.28); margin: 8px; color: white;">
          <h3>✨ Calm presence</h3>
          <p>Subtle motion and gentle reactions make Kairo feel alive without distracting you from your work.</p>
        </div>
      </td>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #312e81, #1d4ed8); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 10px 28px rgba(59,130,246,0.28); margin: 8px; color: white;">
          <h3>🧠 Personality modes</h3>
          <p>Switch between calm, active, playful, curious, sleepy, and mischievous patterns to shape Kairo’s mood.</p>
        </div>
      </td>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #0f766e, #14b8a6); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 10px 28px rgba(20,184,166,0.28); margin: 8px; color: white;">
          <h3>🪟 Flexible layout</h3>
          <p>Use Kairo as a floating overlay or as a compact desktop window depending on your setup.</p>
        </div>
      </td>
    </tr>
  </table>
</div>

## Features

- Follows the pointer across the desktop and multi-monitor setups
- Supports both overlay and framed window presentation modes
- Uses idle animation states like scratching, yawning, and resting
- Ships with personality-driven activity modes and mood shifts
- Includes smarter screen-bound handling and safer repositioning logic
- Configurable via `kairo.properties` and the in-app settings dialog
- Lightweight, dependency-minimal Swing implementation

## Project structure

- `src/main/java/` — application logic, controller behavior, and UI bootstrap
- `src/main/resources/` — runtime assets and default settings
- `docs/` — product docs and supporting project content
- `pom.xml` — Maven project definition
- `LICENSE` — project licensing terms

## Requirements

- Java JDK 8 or newer
- Maven (optional, recommended for packaging)

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

The runtime behavior is driven by `src/main/resources/kairo.properties`, with additional runtime tuning available in the in-app settings dialog.

Key values include:

- `windowTitle`
- `triggerDistance`
- `runDistancePerFrame`
- `offsetx` / `offsety`
- animation timing values
- `activityMode`

## Design direction

Kairo is intentionally built as a desktop companion rather than a decorative toy. The design priorities are:

- calm presence over noisy motion
- personality over raw animation speed
- clarity over legacy complexity
- a consistent visual identity across code, UI, and documentation

## License

This project is released under the LIMITED SOURCE-AVAILABLE & VIEW-ONLY LICENSE.

See [LICENSE](LICENSE) for more details.

