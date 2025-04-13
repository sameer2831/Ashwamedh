# AshwamedhApp 🎉📱

AshwamedhApp is an Android application developed for **AISSMS COE Pune** to support and publicize the annual **Ashwamedh Sports and Cultural Festival**. It serves as a digital hub for students and participants to explore events, register, and stay updated throughout the fest.

---

## ✨ Features

- 🏁 View categorized event listings (Sports and Cultural)
- 📸 Browse event posters and get full event details
- 📝 Register for events directly through the app
- 🔔 Receive live updates and announcements
- 🧭 Smooth and user-friendly navigation
- 🌐 Firebase integration for real-time data and authentication

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Architecture:** Modular + MVVM-ready
- **UI Design:** XML with ViewBinding
- **Backend:** Firebase Firestore, Firebase Auth
- **Image Loading:** Glide
- **Libraries Used:**
  - [Glide](https://github.com/bumptech/glide) – Image loading
  - AndroidX, Material Components
  - Firebase SDKs

---

## 📷 Screenshots

> *(Coming soon!)*

---

## 🚀 Getting Started

Follow these steps to get the app running on your local machine.

### 1. Clone the repository

```bash
 git clone https://github.com/sameer2831/Ashwamedh.git
cd Ashwamedh
```
### 2. Open the project in Android Studio
Make sure you are using the latest stable version of Android Studio.

### 3. Set up Firebase
- Add your `google-services.json` file in the `app/` directory.
- Enable **Firestore** and **Authentication** in your Firebase Console.

### 4. Sync and Build
- Let Gradle sync and resolve all dependencies.
- Click **Run** or press **Shift + F10** to launch the app.

---

## 📁 Project Structure

```bash
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cis600/ashwamedh/
│   │   │   │   ├── adapter/
│   │   │   │   ├── model/
│   │   │   │   ├── ui/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle
├── build.gradle
└── settings.gradle
```
