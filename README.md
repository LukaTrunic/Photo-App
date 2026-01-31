# 📸 PhotoApp – Design Patterns Overview

This project applies multiple **design patterns** across different layers.

## Implemented Design Patterns

### 1. Strategy Pattern  
**Layer:** Data and service layer  

**Where:**  
- `PhotoStorageStrategy`  
- `LocalPhotoStorageStrategy`  
- Used in `PhotoServiceImpl`

**Description:**  
Photo storage behavior is behind a common interface, allowing storage implementations to be changed without modifying business logic.

---

### 2. Facade Pattern  
**Layer:** Business logic layer  

**Where:**  
- `PhotoServiceImpl`  
- `UserServiceImpl`

**Description:**  
Service classes act as facades that hide complex workflows (repositories, storage, validation, logging) behind a simplified API used by controllers.

---

### 3. Adapter Pattern  
**Layer:** Data and service layer  

**Where:**  
- `CustomUserDetails`

**Description:**  
Adapts the application's `User` domain model to Spring Security’s `UserDetails` interface without modifying the original entity.

---

### 4. Builder Pattern  
**Layer:** Business logic layer  

**Where:**  
- `User.builder()`  
- `Photo.builder()`

**Description:**  
Used to create complex objects with many optional fields in a readable and safe way, avoiding large constructors.

---

### 5. Singleton Pattern  
**Layer:** Data and service layer  

**Where:**  
- Spring `@Service` beans  
- `FirebaseAuth.getInstance()`

**Description:**  
Ensures a single shared instance of services and infrastructure components managed by the framework.

---

### 6. Chain of Responsibility Pattern  
**Layer:** Presentation layer

**Where:**  
- `SecurityConfig` – `SecurityFilterChain`

**Description:**  
HTTP requests pass through a chain of security filters, where each filter can process or stop the request.

---

### 7. Proxy Pattern  
**Layer:** Data and service layer  

**Where:**  
- Spring Data JPA repositories (e.g. `PhotoRepository`)

**Description:**  
Spring generates runtime proxy implementations for repository interfaces to intercept calls and handle database access.

---

### 8. Decorator Pattern  
**Layer:** Presentation layer  

**Where:**  
- Thymeleaf fragments (e.g. `navbar.html`)

**Description:**  
UI fragments are attached to pages dynamically, adding responsibilities such as navigation without modifying page logic.

---

## Firebase Configuration

This project uses **Firebase Authentication** and the **Firebase Admin SDK**.

For security reasons, sensitive credentials are **not included** in this repository.

---

### 1️Firebase Service Account (Backend Configuration)

The backend requires a Firebase **service account key** for server-side authentication and token verification.

#### How to obtain `firebase-service-account.json`:
1. Open **Firebase Console**
2. Select your project
3. Go to **Project Settings → Service Accounts**
4. Click **Generate new private key**
5. Download the JSON file
6. Rename it to: `firebase-service-account.json`
7. Where to place the file: `src/main/resources/firebase-service-account.json` instead of `firebase-service-account.json.example`

---

### Firebase Web Configuration (Frontend – Login)

Firebase web configuration keys are used for **client-side authentication** and are **public by design**.

#### How to obtain the Firebase web configuration:
1. Open **Firebase Console**
2. Go to **Project Settings**
3. Scroll to **Your apps**
4. Select the **Web App**
5. Copy the Firebase configuration object

#### Where to add the configuration: `src/main/resources/templates/login.html` Inside the script section

