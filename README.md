# Weather City

## Web app to check the weather

The project aims to recreate a simple weather site. So by searching for a city or, eventually, a smaller lesser known place it should give back the weather and other useful information about the place.

## How it works
The app simply works by retrieving places and weather data using the online free available [Nominatim Openstreetmap](https://nominatim.org/release-docs/develop/api/Overview/) and [Open Weather](https://openweathermap.org/current) API.
- The Nominatim API does not need any API key or registration to retrieve information about coordinates of specific places
- The Open Weather API does need an api key to be able to retrieve weather information (api key not given at the current time)
- Alternatively it is possible to use the [FccWeather](https://fcc-weather-api.glitch.me/) API which does not required any registration and api key
  - However it seems to not work always correctly
  
## What is missing?
As of now is missing the `src/main/resources/application.yaml` file. Which contains all the data used by the Open Feign client.

### Steps
-[x] Retrieve a place by the name
-[ ] Visualize important weather data for the place written
-[ ] Creating an API
-[ ] Add a front end part
-[ ] Making available the application.yaml