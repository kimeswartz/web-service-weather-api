This application is a weather service that allows users to query weather data for different cities. 
It integrates with the OpenWeatherMap API to fetch real-time weather information and uses an H2 in-memory database to store and manage this data.

How it works:
1. The application uses the OpenWeatherMap API to get weather data. When a user requests weather information for a city, the app makes an HTTP request to the API.
2. The request URL is dynamically constructed using the base URL, the city name, the API key, and units. This URL retrieves weather data in JSON format.
3. The JSON response is mapped to a Java object (OpenWeatherMapResponse) to extract relevant details such as the city name, temperature, and weather description.

Data Storage:
The application uses an H2 in-memory database to store weather data. This is a lightweight, embedded database that runs in the application's memory, making it ideal for development and testing.
The data stored in the database includes weather details such as city name, temperature, and description.

Service Layer (WeatherService):
This is where the core business logic resides. It interacts with both the weather API and the H2 database.
1. Fetching Weather Data: When a request is made to get weather data for a city, the service queries the API, processes the response, and saves it to the database.
2. Retrieving Weather Data: The service can also retrieve weather information from the database using an ID.
3. Updating and Deleting: The service supports updating existing weather records and deleting them from the database.

Repository Layer (WeatherRepository:
This interface extends JpaRepository and provides methods to interact with the H2 database. It allows for basic CRUD operations (Create, Read, Update, Delete) on WeatherInformation entities.

Exception Handling (GlobalExceptionHandler):
Manages errors and exceptions that occur in the application. It returns appropriate HTTP status codes and messages, ensuring that users receive meaningful error responses.

Flow:
1. User Request: A user makes a request through the API (e.g., to get weather data for a city).
2. Controller Handling: The request hits the WeatherController, which calls the WeatherService.
3. API Call: The service constructs a URL, queries the OpenWeatherMap API, and gets the weather data.
4. Data Processing: The service processes the API response, creates a WeatherInformation object, and saves it to the H2 database.
5. Data Retrieval: For subsequent requests, the service can retrieve, update, or delete weather data from the H2 database.
6. Response: The controller returns the processed data to the user.
