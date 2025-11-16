# Oishii Sushi Desktop

Oishii Sushi Desktop es la aplicación de escritorio del sistema de gestión del restaurante Oishii Sushi.  
Esta aplicación está diseñada para que los empleados del restaurante puedan **recibir, visualizar y gestionar las comandas** enviadas desde la APP móvil de los clientes(https://github.com/SpookyyMoon/OishiSushi).

Ambas aplicaciones (móvil y escritorio) están conectadas mediante una **API REST desarrollada con Express y MongoDB**(https://github.com/SpookyyMoon/OishiSushiBackend), permitiendo una sincronización en tiempo real del estado de mesas y pedidos.

---

## Características principales

- Recepción de comandas en tiempo real – La aplicación obtiene las comandas enviadas desde la APP móvil mediante peticiones REST al backend.

- Visualización de pedidos – Se muestran todas las comandas pendientes, incluyendo platos, cantidades y mesa correspondiente.

- Actualización del estado de la comanda – Los empleados pueden marcar una comanda como atendida, actualizando el backend y sincronizando la app móvil.

- Gestión del estado de las mesas – Desde la aplicación se puede ver si las mesas están ocupadas o libres según los pedidos recibidos.

- Sincronización con MongoDB Atlas – Todos los cambios quedan reflejados en la base de datos gracias a la conexión con la API en Express.

- Arquitectura por capas** – Separación entre interfaz (FXML), lógica de negocio (controlador JavaFX) y acceso a datos (Retrofit).

- Interfaz sencilla y funcional – Una ventana única con la tabla de comandas, botones de actualización y opciones de gestión.

---

## Tecnologías utilizadas

- **Lenguaje principal:** Java  
- **Interfaz gráfica:** JavaFX + FXML  
- **Comunicación con backend:** Retrofit  
- **Backend:** Node.js + Express + MongoDB Atlas (API REST)  
- **Gestión de datos:** Mongoose  

---

## Imágenes en ejecución:

### Vista principal
<img width="1917" height="1078" alt="{673A44AC-1EA8-4EF1-AA64-66C68BAB87BA}" src="https://github.com/user-attachments/assets/3ed609ac-9e18-480f-b219-52bd57cb15e0" />


### Ventana de comandas
<img width="1919" height="1077" alt="{3563D093-309E-449E-8923-90334982CC75}" src="https://github.com/user-attachments/assets/94f2b26b-5219-4b45-9d56-4d6ae50ba6d5" />

---

## Video de demostración (Interacción entre APP móvil y escritorio)

[Ver video de demostración]([./OishiiSushiDemo.mp4](https://github.com/SpookyyMoon/OishiSushi/blob/master/OishiiSushiDemo.mp4))

---

## Documentación en PDF (Latex)

[Ver documentación en PDF]([./docu.pdf](https://github.com/SpookyyMoon/OishiSushi/blob/master/docu.pdf))

