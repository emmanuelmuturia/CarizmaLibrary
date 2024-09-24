#!/bin/bash

# (For Linux Users)
# Please grant the Execute permission before running this file using the following command:
# chmod +x codeInspector.sh

./gradlew ktlintFormat && ./gradlew ktlintCheck && ./gradlew detekt && ./gradlew spotlessApply && ./gradlew build