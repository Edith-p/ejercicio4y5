# Ejercicio 4 y 5 - Herencia y Polimorfismo
**Nombre completo:** Edith Estefany Pérez Trujillo
**Carné:** 26952
**Nombre completo:** Emily Lucila Menchú Menchú 
**Carné:** 20472


## Descripción
El programa fue diseñado con el objetivo de administrar de forma ordenada y efectiva el alquiler de vehiculos de la empresa RentaMovil para lograr mantener la información actualizada, calcular
los cobros correctamente, saber que vehiculos pueden alquilarse y demás. 

Para lograrlo se hizo uso de herencia y polimorfismo, ya que cuenta con la clase abstracta Vehiculo que contiene los datos comunes de toda la flota y de ella nacen
Automovil, Motocicleta, CamionetaCarga y Microbus, los cuales sobreescriben los metodos de cobro, licencia requerida y el mantenimiento. Al igual se cuenta con la clase Abstracta Cliente, 
de la cual nacen ClienteIndividual y ClienteCorporativo, los cuales sobreescriben metodos para descuentos y limites de alquiler. Al utilizar polimorfismo en el programa, es posible almacenar
todos los vehiculos en Vehiculo y todos los cientes en Cliente por medio de ArrayList.

El programa permite registrar vehiculos/clientes, consulta de flota y clientes, cotizacion de alquileres, confimacion o cancelacion 
de un alquiler, registrar devoluciones, enviar de manera automatica el vehiculo a mantenimiento al llegar a su umbral y darle fin al mantenimiento, consultar vehiculos por categoria y estado
consultar ingresos, totales y por categoria, consultar descuentos y alquileres activos y consultar el historial de un cliente especifico. De la misma manera el programa maneja errores y excepciones de manera 
que el programa no pare repentinamente. 




## Cómo ejecutar
```bash
javac -d bin src/*.java
java -cp bin Main
```
