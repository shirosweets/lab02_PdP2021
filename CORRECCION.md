# Grupo 10				
## Corrección				
	Tag o commit corregido:	1.0		
			Pesos internos	
### Entrega		100,00%		
	Commits de cada integrante	100,00%	0,4	0,4
	En tiempo y con tag correcto	100,00%	0,3	0,3
	Commits frecuentes y con nombres significativos	100,00%	0,3	0,3
### Informe		100,00%		
	Parte 1: ¿Como es la interfaz de la clase u objeto que implementaron para el primer componente?	100,00%	0,2	0,2
	Parte 1: ¿Utilizaron una clase o un objeto? ¿Por qué?	100,00%	0,2	0,2
	Parte 2: ¿Por qué es una mala opción usar literales?	100,00%	0,2	0,2
	Parte 2: ¿Qué concepto(s) de la Programación Orientada a Objetos utilizaron?	100,00%	0,15	0,15
	Parte 3: ¿Cómo funciona el polimorfismo de clases en su implementación?	100,00%	0,15	0,15
	Parte 3: ¿Qué clase almacena las URLs?	100,00%	0,1	0,1
### Funcionalidad		100,00%		
	Manejan el caso de error en la consulta HTTP devolviendo una lista vacía	100,00%	0,2	0,2
	Parsean correctamente los artículos de Reddit y de RSS	100,00%	0,2	0,2
	Reemplazan las url en los artículos de Reddit	100,00%	0,2	0,2
	Realizan consultas a múltiples URLs subscriptas	100,00%	0,2	0,2
	Aceptan correctamente los distintos parámetros de las URLs	100,00%	0,2	0,2
### Modularización y diseño		100,00%		
	Evitan repetir código que realiza el request HTTP y maneja los errores aprovechando la herencia.	100,00%	0,3	0,3
	Utilizan clases distintas para parsear los feed RSS y Reddit	100,00%	0,25	0,25
	Utilizan una clase distinta de los parsers para administrar las subscripciones	100,00%	0,2	0,2
	La clase que administra las subscripciones no está acoplada a los distintos tipos de url. Es decir, si se agrega un nuevo tipo de url, no es necesario cambiar esta clase.	100,00%	0,25	0,25
### Calidad de código		70,00%		
	Utilizan el paradigma funcional cuando es posible.	100,00%	0,25	0,25
	Estructuras de código simples	80,00%	0,25	0,2
	Reutilizan funciones de librería, por ejemplo para serializar y deserializar Json	100,00%	0,25	0,25
	Estilo: no tienen errores ni warnings al hacer `sbt scalastyle`, indentación consistente, líneas de menos de 80 caracteres. Nombre de variable en camelCase	0,00%	0,25	0
### Opcionales				
	Punto estrella 1: Elección de campos	100,00%		
	Punto estrella 2: Normalización de conteos	50,00%		
	Punto estrella 3: StackExchange feed	0,00%		
				
# Nota Final		10		
				
				
# Comentarios				
	En la pregunta P1-1, responden correctamente por qué utilizan una clase, pero eso no justifica por qué no utilizan un objeto.			
	Líneas de más de 80 caracteres, indentación inconsistente. Scala se indenta con 2 espacios, no 4. Uso de nombre snake_case y no camelCase			
	La función `FeedService.get_result` es un poco superflua			
	El nombre `URL_R` no es muy indicativo			
	Los archivos referentes a parsear urls no deberían ir en el paquete `nermodel`, que tiene lo relativo al modelo de entidades nombradas			
