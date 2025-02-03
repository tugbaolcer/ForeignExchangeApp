# ForeignExchangeApp 🌍💱

**ForeignExchangeApp** is a modern Android application designed to let users monitor foreign exchange rates with ease. With a sleek design and seamless functionality, users can explore exchange rates in real-time and manage their preferences effectively.

## 🚀 Technologies and Tools

### Preview



https://github.com/user-attachments/assets/5dfd530f-792c-4fbb-a6f3-3a721948ea68



### Android
- **Jetpack Compose**: Used for building the entire UI with a declarative approach.
- **MVVM (Model-View-ViewModel)**: Ensures a clean separation of concerns and testable architecture.
- **Clean Architecture**: Adopts a multi-layered structure for modular and maintainable code.
- **Navigation Component**: Simplifies navigation between screens with Compose integration.

### Data and Backend
- **Retrofit**: Handles API requests and JSON parsing efficiently.
- **Firebase Authorization**: Provides secure user authentication and sign-in options.
- **Coroutines**: Manages background operations for a smooth and responsive user experience.
- **Flow**: Manages real-time asynchronous data streams.

### Additional Libraries and Tools
- **Hilt**: Simplifies dependency injection.
- **Material Design**: Offers a polished and user-friendly UI design.
- **Google Fonts**: Enhances the visual experience with custom font styles.

## ✨ Features
- **Real-Time Exchange Rates**: Browse current exchange rates for various currencies.
- **User Authentication**: Sign up and log in securely with Firebase Authorization.

## 📂 Project Structure
```plaintext
- data
   - remote: API services and data models
- domain
   - model: Data models for domain layer
   - repository: Interfaces for repository implementations
   - usecase: Business logic classes
- presentation
   - view: UI screens using Jetpack Compose
   - viewmodel: State management with ViewModel
- di: Dependency Injection modules (Hilt)

```
📲 Setup
To run the project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/tugbaolcer/ForeignExchangeApp.git
   ```

2. Open the project in Android Studio.

3. Make sure to sync the project with Gradle files.

4. Add your API key for the news service (if required) in the  `local.properties` or directly in the code:
     ```properties
   API_KEY="your_api_key"
   ```
5. Run the project on an emulator or a physical device.
