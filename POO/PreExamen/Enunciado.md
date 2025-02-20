# 📜 Enunciado del Examen: Sistema de Reservas de Vuelos ✈️

## 📌 Descripción
Se debe desarrollar un sistema de gestión de reservas de vuelos donde los pasajeros puedan reservar asientos en diferentes vuelos. El sistema debe permitir la gestión de pasajeros y vuelos, asegurando que no se sobrepasen los asientos disponibles en cada vuelo.

## 📌 Requisitos del Programa
El sistema debe implementar las siguientes funcionalidades:

### 1️⃣ Gestión de Pasajeros
- Permitir registrar un nuevo pasajero, almacenando su **ID** (DNI/Pasaporte), **nombre** y **edad**.
- Almacenar los pasajeros en un `HashMap<String, Pasajero>`, donde la clave es el **ID**.

### 2️⃣ Gestión de Vuelos
- Permitir crear nuevos vuelos, con información como **código de vuelo**, **origen**, **destino**, **fecha** y **capacidad máxima**.
- Almacenar los vuelos en un `ArrayList<Vuelo>`.

### 3️⃣ Reservas
- Permitir que un pasajero **reserve un asiento en un vuelo**, siempre que haya disponibilidad.
- Si el vuelo está lleno, el pasajero debe ser agregado a una **lista de espera** (`Queue`).
- Permitir que un pasajero **cancele su reserva**, liberando un asiento y asignándolo al primer pasajero en lista de espera (si hay).

### 4️⃣ Mostrar Información
- Mostrar la **lista de vuelos disponibles**.
- Mostrar la **lista de reservas en un vuelo específico**.
- Mostrar la **lista de pasajeros en lista de espera**.

### 5️⃣ Persistencia de Datos
- Guardar y cargar información de **pasajeros, vuelos y reservas** desde un archivo (`reservas.dat`).

---

## 📜 Ejemplo de Funcionamiento

### Menú Principal
```text
Bienvenido al Sistema de Reservas de Vuelos ✈️
1. Registrar pasajero
2. Crear vuelo
3. Reservar asiento
4. Cancelar reserva
5. Mostrar vuelos
6. Mostrar reservas en un vuelo
7. Guardar datos
8. Cargar datos
9. Salir
Seleccione una opción:
```

### Ejemplo de Operaciones

#### Registrar pasajero:
```text
ID: 12345678A
Nombre: Javier Pérez
Edad: 30
```

#### Crear vuelo:
```text
Código: MAD-NYC101
Origen: Madrid
Destino: Nueva York
Fecha: 15/03/2025
Capacidad: 2
```

#### Reservar asiento:
```text
ID Pasajero: 12345678A
Vuelo: MAD-NYC101
Resultado: Reserva confirmada.
```

#### Lista de reservas en MAD-NYC101:
```text
1. Javier Pérez (ID: 12345678A)
