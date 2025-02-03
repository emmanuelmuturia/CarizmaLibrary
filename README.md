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

- The Sonux Project was forged as a spin-off to [Carizma](https://github.com/emmanuelmuturia/Carizma) with the main objective being researching [The 8D Effect](https://www.epidemicsound.com/blog/8d-audio-what-is-it-and-whats-the-hype-all-about/)...
- The initial iteration of The Sonux Project was powered by [MediaPlayer](https://developer.android.com/reference/android/media/MediaPlayer) but the current one relies on [FFmpeg Kit](https://github.com/arthenica/ffmpeg-kit) for Playback Control...
- The Sonux Project was purposed to become an Android Library but has been set as a KMP project for now...

## Architecture

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

## Tests
## Screenshots
## Credits
## Trivia



## Future