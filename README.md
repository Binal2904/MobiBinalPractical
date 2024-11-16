# Square Inc. Repositories App

An Android application showcasing Square Inc.'s open-source GitHub repositories using modern Android development practices.

## Features

1. **Repositories Screen**:
   - Display a list of Square Inc.'s public repositories from GitHub.
   - Show repository name and the number of stars.
   - Support for paginated data loading.
   - Offline data storage using Room.

2. **Repository Details**:
   - View details such as repository name and star count.
   - Add or remove repositories from bookmarks.
   - Bookmarked repositories are stored locally.

3. **Bookmarks**:
   - Easily manage bookmarks from the details screen.
   - Bookmarked repositories are marked with an icon in the list.

## Architecture

- **MVVM**: Ensures a clear separation between UI components and data/business logic.
- **Repository Pattern**: For data abstraction and handling multiple data sources.

## Libraries & Tools

- **Hilt**: Dependency Injection
- **Retrofit**: REST API requests
- **Room**: Local data storage
- **Coroutines**: Asynchronous operations
- **LiveData**: Observable data holder
- **ViewModel**: Lifecycle-aware UI-related data holder

## Project Structure

```plaintext
com.example.squareincapp
│
├── data
│   ├── model
│   
│   
│
├── di
├── ui
│   ├── activities
│   ├── fragments
│   ├── adapters
│   └── viewmodels
└── utils
