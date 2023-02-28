![GitHub release (latest by date)](https://img.shields.io/github/v/release/touchlab/KMMBridge)
![GitHub](https://img.shields.io/github/license/touchlab/KMMBridge)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

# KMMBridge for Teams

![KMMBridge for Teams](kmmbridge-announcement.png)

KMMBridge is a set of Gradle tooling that facilitates publishing and consuming pre-built KMM (Kotlin Multiplatform Mobile) Xcode Framework binaries.

## Documentation Website

See [https://touchlab.github.io/KMMBridge/intro/](https://touchlab.github.io/KMMBridge/intro/) for setup and detailed documentation.

To provide feedback about your experience with KMMBridge join the conversation in the Kotlinlang [#touchlab-tools](https://kotlinlang.slack.com/archives/CTJB58X7X) Slack channel.

## Quick Start Post

[https://touchlab.co/quick-start-with-kmmbridge-1-hour-tutorial/](https://touchlab.co/quick-start-with-kmmbridge-1-hour-tutorial/)

## Announcement Post

[https://touchlab.co/introducing-kmmbridge-teams/](https://touchlab.co/introducing-kmmbridge-teams/)

> ## Subscribe!
>
> We build solutions that get teams started smoothly with Kotlin Multiplatform Mobile and ensure their success in production. Join our community to learn how your peers are adopting KMM.
 [Sign up here](https://go.touchlab.co/newsletter-gh)!

Add the plugin

Take the `build.gradle(.kts)` file located at the root of your Kotlin Multiplatform project,
and add the following at the top of the file:

```kts
plugins {
    id("io.github.wjf510.kmmbridge") version "0.3.5"
}
```
