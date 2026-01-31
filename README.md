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