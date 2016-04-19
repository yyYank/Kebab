![Kebab](./images/kebab.png)

# Kebab [![Circle CI](https://circleci.com/gh/yyYank/Kebab/tree/master.svg?style=shield)](https://circleci.com/gh/yyYank/Kebab/tree/master) 

Kebab is a browser automation solution. It brings together the power of WebDriver, the robustness of Page Object modelling and the expressiveness of the Kotlin language.
not Groovy.

# Note

Experimental version

It cannot recommended to use in production now.

# Usage

## Configuration

```Kotlin
    val config: Configuration by lazy {
        configuration {
            baseUrl = "http://www.google.co.jp/"

            driver = ChromeDriver()

            options {
                timeout {
                    implicitlyWait = 10L to TimeUnit.SECONDS
                }
            }
        }
    }
```

## Browser Test

```Kotlin
    browser.drive("http://www.google.co.jp/", {
            assertEquals("Google", title)
            val searchButton = find(By.cssSelector(".jsb > center:nth-child(1) > input:nth-child(1)"))
            searchButton.click()
        })
    browser.quit()
```

# Kebab VS Geb

The different point is Kebab is light weight.
Geb has wide range feature. But Kebab is simple feature,not exhaustive.

# How to contribute

## Build Environment

Kebab builds with Gradle. You do not need to have Gradle installed to work with the Kebab build as Gradle provides an executable wrapper that you use to drive the build.

On UNIX type environments this is gradlew and is gradlew.bat on Windows.

For example to run the Kebab test suite for the entire project you would run…

```Gradle
./gradlew test
```

# Licence

Apache License, Version 2.0
