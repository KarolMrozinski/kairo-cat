<h1 align="center">Kairo Cat</h1>

<p align="center">
  <img src="https://raw.githubusercontent.com/KarolMrozinski/kairo-cat/main/docs/Kairo_animated.gif" alt="Kairo desktop companion" width="520" />
</p>

<p align="center">
  <a href="#features"><img alt="Desktop companion" src="https://img.shields.io/badge/Type-Desktop%20Companion-8b5cf6" /></a>
  <a href="#requirements"><img alt="Java" src="https://img.shields.io/badge/Java-8%2B-007396" /></a>
  <a href="#license"><img alt="License" src="https://img.shields.io/badge/License-Limited-orange" /></a>
</p>

<h1 align="center">Kairo Cat</h1>

<p align="center">
  <img src="https://raw.githubusercontent.com/KarolMrozinski/kairo-cat/main/docs/Kairo_animated.gif" alt="Kairo desktop companion" width="520" />
</p>

<p align="center">
  <a href="#features"><img alt="Desktop companion" src="https://img.shields.io/badge/Type-Desktop%20Companion-8b5cf6" /></a>
  <a href="#requirements"><img alt="Java" src="https://img.shields.io/badge/Java-8%2B-007396" /></a>
  <a href="#license"><img alt="License" src="https://img.shields.io/badge/License-Limited-orange" /></a>
</p>

Kairo Cat is a refined desktop companion built with Java Swing for people who want a calmer, more intentional digital workspace. It quietly lives in the background as a responsive cat that follows the cursor, reacts to activity, and adds personality to the desktop without stealing focus from the work itself.

This project is not built to be loud or distracting. It is designed to feel present, but never invasive. The idea is simple: keep the character alive while respecting the user’s environment, their attention, and the structure of how they work on a computer every day.

## Why this project feels different

Most desktop companions either become noisy, decorative, or overly scripted. Kairo is designed around the idea that a digital companion should feel like a thoughtful product feature, not a gimmick. It moves with natural rhythm, responds to the environment, and keeps its personality without turning the desktop into a chaotic visual layer.

The project is intentionally shaped around a premium desktop experience: subtle motion, gentle behavior transitions, and a consistent visual identity make it feel more like a polished product than a quick prototype or novelty app.

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
          <p>Kairo shifts between calmer, more active, curious, and playful behaviors to feel more alive and less mechanical.</p>
        </div>
      </td>
      <td width="33%" valign="top">
        <div style="background: linear-gradient(135deg, #0f766e, #14b8a6); border-radius: 18px; padding: 18px; min-height: 170px; box-shadow: 0 14px 32px rgba(20,184,166,0.24); margin: 8px; color: white;">
          <h3>🪟 Desktop-aware behavior</h3>
          <p>It respects screen boundaries and the active workspace, behaving like a companion that stays nearby without obstructing work.</p>
        </div>
      </td>
    </tr>
  </table>
</div>

## What Kairo does

Kairo is designed to live on the desktop and feel like a small digital companion. It follows the pointer, reacts to user activity, and changes behavior depending on context. The result is an app that feels alive but not chaotic — intuitive, readable, and friendly without becoming intrusive.

The main behaviors include:

- following the pointer in a smooth and characterful way
- switching between calmer and more active states over time
- reacting to different desktop conditions and user interaction patterns
- staying within safe screen bounds and respecting desktop space
- supporting different presentation modes without disturbing the workspace
- keeping a consistent visual identity from the app itself to the project presentation

## Why it exists

The intention behind Kairo is not to build a random animation or a toy app. It is to create an ambient companion that makes a desktop feel more alive and human without turning the system into a distraction machine.

This matters because most productivity tools optimize for utility alone. Kairo brings personality into the environment without making the user fight against it. It sits with you, reacts softly, and contributes to the mood of the workspace without demanding attention.

In other words, Kairo is an experiment in thoughtful digital presence: not loud, not random, and not performance-driven for its own sake. It aims to feel premium and considered, with a personality that supports the work rather than interrupts it.

## Core features

- Follows the cursor across the desktop in a smooth, natural way
- Supports both floating overlay and framed window modes
- Uses idle animations such as resting, scratching, yawning, and attention shifts
- Includes personality-driven activity profiles and mood transitions
- Adjusts behavior with safer bounds, smart margins, and responsive desktop logic
- Lets users tune behavior through the in-app settings panel and configuration file
- Built as a lightweight Java Swing application with minimal external dependencies

## Project structure

- `src/main/java/` — application logic, controller behavior, window logic, and UI bootstrap
- `src/main/resources/` — runtime assets and default settings
- `docs/` — visual assets and supporting project documentation
- `pom.xml` — Maven project definition
- `LICENSE` — project licensing and usage posture

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

This keeps the experience engaging, intentional, and polished without turning the project into something generic or over-engineered.

## Project status

This project is in a mature polish phase: the core behavior is stable, the personality system is in place, and the interface is shaped as a thoughtful desktop experience rather than a one-off prototype.

The app is deliberately designed to feel considered rather than overloaded. Its strength lies in the balance between character and restraint: Kairo is present, but it does not dominate the screen or the user’s attention.

## License

This project is released under the LIMITED SOURCE-AVAILABLE & VIEW-ONLY LICENSE.

See [LICENSE](LICENSE) for more details.

