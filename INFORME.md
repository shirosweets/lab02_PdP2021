# Laboratorio 2: Programación Orientada a Objetos con Scala

## Grupo 10

## Integrantes:
- Agustin Silva
- Valentina Solange Vispo
- Alejandro Claudio Spitale

---
## Contenido
- [Parte 1](#parte-1)
    - [Respuesta P1-1](#respuesta-p1-1)
    - [Respuesta P1-2](#respuesta-p1-2)
- [Parte 2](#parte-2)
    - [Respuesta P2-1](#respuesta-p2-1)
    - [Respuesta P2-2](#respuesta-p2-2)
- [Parte 3](#parte-3)
    - [Respuesta P3-1](#respuesta-p3-1)
    - [Respuesta P3-2](#respuesta-p3-2)
    - [Respuesta P3-3](#respuesta-p3-3)
- [Puntos estrella](#puntos-estrella)

---

## Parte 1

### Preguntas:

> 1. ¿Cómo es la interfaz de la clase u objeto que implementaron para el primer componente?

#### Respuesta P1-1
La interfaz que implementamos para el primer componente consta de **2 métodos**: uno para hacer el **pedido HTTP**, y otro para **parsear la respuesta del pedido**.

> 2. ¿Utilizaron una clase o un objeto? ¿Por qué?

#### Respuesta P1-2
Utilizamos una **clase** porque queríamos crear la estructura que tendrá toda la información necesaria que necesite el objeto que luego se instanciará con la clase, para que pueda utilizar los métodos definidos.

## Parte 2

### Preguntas:
> 1. ¿Por qué utilizar literales como Strings para diferenciar comportamientos similares crea acoplamiento?

#### Respuesta P2-1
Primero porque si le pasamos un *tipo que no existe*, genera que se rompa la función.

Segundo porque es difícil extender a otros tipos de URLs. Ya que habría que modificar el nombre de la clase para definirla nuevamente.

Por ejemplo: si antes teniamos `TextPaser_RSS_REDDIT`, y queremos agregar un tipo más de url además de modificar el método debemos cambiar el nombre de la clase, por lo que se vuelve ineficiente.

Tercero, corremos el riesgo de romper la clase al modificarla ya que si se quiere agregar una nueva url lo mejor sería crear una nueva clase y usar `TextParser` como **clase madre**.

> 2. ¿Qué concepto(s) de la Programación Orientada a Objetos utilizaron para resolver este problema?

#### Respuesta P2-2
Utilizamos herencia, es decir, creamos una **clase madre URL_R** que solo se encargará de **recibir la respuesta HTTP**, y **dos clases hijas, RSS_Parse y REDDIT_Parse** para las diferentes opciones de **parsear** las respuestas.

Al hacer uso de la herencia, generamos dos subtipos de la *clase madre*.

Utilizamos el concepto de *clase abstracta*, para que la clase madre no pueda ser instanciada.

## Parte 3

### Preguntas:
> 1. ¿Qué clase almacena las URLs?

#### Respuesta P3-1
La clase que almacena las URLs se llama `FeedService`.

> 2. ¿Cómo funciona el polimorfismo de clases en su implementación?

#### Respuesta P3-2
En nuestra implementación funciona pasándole al **tipo de la función** `suscribe` el **tipo** `URL_R`, y al utilizar **herencia** decimos que las *clases hijas* son **subtipos** de la *clase madre*.
Así podemos pasarle los diferentes tipos de parsers de URLs definidos en la parte anterior.

> 3. ¿Qué desventaja tiene pasar al método que subscribe un parámetro de tipo string que pueda tomar los valores “rss” y “reddit”, y dejar que decida qué tipo de parser usar?

#### Respuesta P3-3
La desventaja es que nos estaría creando **más acoplamiento**, cuando lo que queremos es que *solo una clase se encargue de decidir que tipo de parser usar*.

---

## Puntos estrella

> Se encuentran en la branch `puntos-estrella`

Punto estrella: `Selección de campos`

La responsabilidad de almacenar esta información es de cada clase de parser.

La recibe a través de un nuevo argumento el cuál será una lista con los campos solicitados.

Luego, dentro de cada parser verificamos si la lista está vacía, si lo está devuelve el resultado por defecto. Por el contrario, si la lista tiene elementos devuelve los campos solicitados.

Punto estrella: `Mejor modelo`

La responsabilidad de realizar esta acción la tiene el main, ya que es una cuestión de printeo solamente.