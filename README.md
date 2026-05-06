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
<p align="center">
  <img src="<img width="521" height="880" alt="Captura home GoStrong" src="https://github.com/user-attachments/assets/e88ac6ea-9937-4151-b9a6-121816a94835" />
" width="22%" />
  <img src="<img width="480" height="891" alt="rutinas GoStrong" src="https://github.com/user-attachments/assets/87296281-c152-4181-8795-46350dbe2d6c" />
" width="22%" />
  <img src="<img width="525" height="904" alt="captura salud GoStrong" src="https://github.com/user-attachments/assets/20ffe5f1-9652-436f-a19b-e9729f12cf7b" />
" width="22%" />
  <img src="<img width="592" height="903" alt="Dietas GoStrong" src="https://github.com/user-attachments/assets/147bc03c-511f-4962-a3d5-074edf26b16c" />
" width="22%" />
</p>

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
