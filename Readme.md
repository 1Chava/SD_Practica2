# Practica 2: Cuatro Relojes Independientes
## Materia: Reloj huso horario a distancia
## Alumnos:  
    Cortés Francisco Juan Salvador 
    Esquivel Sánchez Luis Rodrigo
    Jurado Cortés Christian Jonathan
    Loyola Diaz Jennifer Michelle
##
### Fecha de asignación: 29/08/18
### Lenguaje de programación: Java
### Fecha de termino: En progreso

### Objetivo de la práctica 
Que el alumno comprenda la importancia de la programación distribuida y el trabajo en equipo.
### Tecnologías a aplicar: 
Sockets, RMI, SOAP, Multi-cast, Hilos (threads), POO, Protocolos de comunicación. 
### Actividades
A partir de la práctica 1, desarrollará una aplicación para controlar un reloj con huso horario a distancia. De acuerdo a los siguientes requerimientos: 

![alt text][logo]

##### Requerimientos funcionales
- [ ] Los cuatro relojes están en diferentes computadoras (Máquinas virtuales).
- [ ] En el maestro hay tres relojes digitales, uno principal y dos secundarios. 
- [ ] El primer reloj secundario tiene la misma hora del principal más una hora. 
- [ ] El segundo reloj secundario tiene la misma hora del principal menos una hora y media.
- [ ] Cada reloj en el maestro tiene asociado una computadora diferente. 
- [ ] En el maestro hay un botón de Modificar y de Enviar. 
- [ ] El botón Modificar, permitirá modificar el reloj principal.
- [ ] El botón Enviar enviará la modificación a las demás computadoras. 
- [ ] El botón Enviar también modificará la hora de los relojes secundarios 
- [ ] Inicialmente el segundero cambia cada segundo. 
- [ ] El formato de hora es de 24 hrs.

##### Requerimientos no funcionales
- [ ] Al pulsar el botón modificar, se detendrá el reloj principal los demás relojes seguirán corriendo.
- [ ] No se pueden modificar los relojes principales y secundarios en las computadoras correspondientes.
- [ ] Al inicio el reloj principal tendrá una hora elegida al azar. La cual enviará directamente a la computadora principal y las secundarias. 
- [ ] Se deberá cambiar la velocidad de actualización del segundero mediante código.

#### Investigación
- [ ] Investiga cómo hacer esta práctica para enviar datos de forma bidireccional.

[logo]: /img/figura1.png "Figura 1. Cuatro relojes con maestro"
