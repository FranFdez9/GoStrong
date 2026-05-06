# GoStrong 💪

GoStrong es una aplicación Android moderna diseñada para la gestión de rutinas de entrenamiento y dietas, proporcionando una experiencia de usuario fluida e internacionalizada.

## 🚀 Características Principales

*   **Sincronización Híbrida (Local/Nube):** Utiliza **Room Database** para el almacenamiento local, garantizando que la app funcione sin conexión, y se sincroniza en tiempo real con **Supabase** para respaldar los datos en la nube.
*   **Interfaz Moderna e Inclusiva:** Construida íntegramente con **Jetpack Compose**. Incluye temas dinámicos y paletas de colores accesibles (colorblind-friendly) utilizando Material Theme.
*   **Soporte Multilingüe:** Internacionalización completa, adaptando dinámicamente el contenido de catálogos de rutinas y dietas según el idioma seleccionado por el usuario.
*   **Arquitectura Limpia:** Sigue el patrón MVVM (Model-View-ViewModel) para separar la lógica de negocio de la interfaz gráfica.

## 🛠️ Tecnologías Utilizadas

*   **Lenguaje:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Base de Datos Local:** Room
*   **Backend as a Service (BaaS):** Supabase
*   **Arquitectura:** MVVM
*   **Gestión de Dependencias:** Gradle (KTS)

## 📱 Capturas de Pantalla
*(Añadir capturas de pantalla de la aplicación aquí)*

## ⚙️ Configuración y Ejecución

Para ejecutar este proyecto localmente, asegúrate de tener instalado **Android Studio**.

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/GoStrong.git
    ```
2.  Abre el proyecto en Android Studio.
3.  Crea un archivo `local.properties` en la raíz del proyecto y añade tus credenciales de Supabase (si es requerido por el proyecto):
    ```properties
    SUPABASE_URL=tu_url_aqui
    SUPABASE_KEY=tu_key_aqui
    ```
4.  Sincroniza Gradle y ejecuta la aplicación en un emulador o dispositivo físico.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Si deseas mejorar la aplicación, por favor abre un *issue* o envía un *pull request*.

## 📄 Licencia

Este proyecto está bajo la licencia MIT.
