# Sonux

## Overview

Sonux is a Kotlin Multiplatform [KMP] project that converts an audio into its 8D form using the FFMPEG Library...

## Table of Contents

1. [Background](#Background)
2. [Architecture](#Architecture)
3. [Tests](#Tests)
4. [Screenshots](#Screenshots)
5. [Credits](#Credits)
6. [Trivia](#Trivia)
7. [Future](#Future)

## Background
## Architecture
## Tests
## Screenshots
## Credits
## Trivia

This is a Kotlin Multiplatform project targeting Android, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets...
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name...
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls...

* `/server` is for the Ktor server application...

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too...


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## Future