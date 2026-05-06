# GoStrong 💪

GoStrong is a modern Android application designed for managing workout routines and diets, providing a smooth and internationalized user experience.

## 🚀 Key Features

*   **Hybrid Synchronization (Local/Cloud):** Uses **Room Database** for local storage, ensuring the app works offline, and syncs in real-time with **Supabase** to back up data in the cloud.
*   **Modern and Inclusive UI:** Built entirely with **Jetpack Compose**. It includes dynamic themes and accessible (colorblind-friendly) color palettes using Material Theme.
*   **Multilingual Support:** Full internationalization, dynamically adapting the content of routine and diet catalogs based on the user's selected language.
*   **Clean Architecture:** Follows the MVVM (Model-View-ViewModel) pattern to separate business logic from the user interface.

## 🛠️ Technologies Used

*   **Language:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Local Database:** Room
*   **Backend as a Service (BaaS):** Supabase
*   **Architecture:** MVVM
*   **Dependency Management:** Gradle (KTS)

## 📱 Screenshots
*(Add application screenshots here)*

## ⚙️ Setup and Execution

To run this project locally, ensure you have **Android Studio** installed.

1.  Clone the repository:
    ```bash
    git clone https://github.com/FranFdez9/GoStrong.git
    ```
2.  Open the project in Android Studio.
3.  Create a `local.properties` file in the root of the project and add your Supabase credentials (if required by the project):
    ```properties
    SUPABASE_URL=your_url_here
    SUPABASE_KEY=your_key_here
    ```
4.  Sync Gradle and run the application on an emulator or physical device.

## 🤝 Contributions

Contributions are welcome. If you want to improve the application, please open an *issue* or submit a *pull request*.

## 📄 License

This project is licensed under the MIT License.
