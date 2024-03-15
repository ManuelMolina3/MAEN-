# MAEN

MAEN es una aplicación para la gestión de las cuentas de su hogar la cual podrá comparar precios de por ejemplo la cesta de la compra comparando cada producto con los diferentes supermercados y tambien tu contrato de la luz con otras compañias de suministro de luz las cuales sean más baratas que la tuya. Esta aplicaión se ha desarrollado en dos partes en el frontend y backend, utilizando dicersas tecnologías para garantizar su correcto funcionamiento.

## Tecnologías utilizadas

Backend: El backend de MAEN se ha desarrollado utilizando Spring, un framework de Java, y empleando PostgreSQL como base de datos en producción y n desarrollo. Además, en producción se ha utilizado Docker para garantizar que el despliegue de la base de datos de PostgreSQL sea lo más sencillo posible.

Frontend: El frontend de MAEN se ha desarrollado utilizando Dart y Flutter utilizando la arquitectura BloC, un framework de desarrollo de aplicaciones móviles multiplataforma. De esta forma, se ha conseguido un desarrollo ágil y sencillo, permitiendo que la aplicación pueda ser ejecutada en distintos sistemas operativos.

## Estado actual del proyecto

El proyecto solo hay un post el cual es el registro y varios get los cuales muestran los productos y sus detalles, los contratos de la luz y sus detalles

## Contribuidores

Manuel Molina García

## Despliegue del Proyecto: tanto de la app Flutter como de la API

1. Debemos tener intalado docker desktop el cual lo que haremos sera iniciar el docker compose dentro de IntelliJ IDEA yla consola dentro de la aplicacion se iniciara el comando docker compose -d.
2. luego dandole dos veces al control pondremos el comando mvn spring-boot::run
3. Por ultimo entraremos en el visual studio code y dentro del main pulsaremos arriba a la derecha en la pestaña que poner run without debugging y se nos abrira solo el simulador de movil y podremos registranos o logearnos como usuarios,
4. Para loguearnos como usuario pondremos el username: pepeillo y la contraseña: 123456789
5. Luego ya podremos ver la aplicacion al completo.

## Licencia

Apache-2.0 license
