# progra2-2c-2020-tpo
Trabajo Práctico Obligatorio - UADE Programación 2 - 2do Cuatrimestre - Jueves-Noche

## Objetivos

- Cumplir con los Objetivos del Trabajo Práctico
- Compartir conocimiento y experiencia.
- Promocionar la Materia ?: )


_**¿Qué se puede hacer?**_

- Crear ramas.
- Crear forks.
- Hacer push a ramas personales.


_**¿Qué no se puede hacer?**_

- Push a master.
- Push a ramas de otros.*

_**Cualquier consulta me puenden enviar un mensaje por:**_

**Teams** Leonardo Pardo
**Mail:** lepardo@uade.edu.ar

<hr>

# Normas de presentación
El trabajo práctico se realizará en forma grupal. Cada grupo constará de 3 a 4 integrantes.
Una vez entregado el enunciado de Trabajo Práctico no se permitirá cambiar la constitución de los grupos, a menos que esté plenamente justificado. Mientras en un grupo quede al menos un integrante, éste deberá continuar solo con la realización del trabajo Práctico. Las fechas de pre-entrega aseguran que ante un caso como el mencionado, el integrante restante disponga de tiempo para completarlo. 

## El TP consta de 3 puntos a entregar.

### Fechas
- La fecha de entrega parcial no obligatoria de los puntos 1 y 2, junto a la prueba integral de estas partes será la semana de entrega preliminar.
- La fecha de entrega final será antes del inicio de la clase de la semana 17.

### Aprobación
- Se deberá aprobar el 50% de cada punto y el 60% de los conceptos evaluados.
- Se recomienda realizar la entrega preliminar pues son temas que se evaluarán dentro del único parcial.
- Restricciones del uso del lenguaje de programación java
Por utilizarse este lenguaje como herramienta para comprobar el funcionamiento de los TDA definidos en el curso, en este curso no utilizaremos los siguientes componentes del lenguaje de programación JAVA. En caso de usarlos, se deberá justificar plenamente su empleo mediante un comentario dentro del código.
	- Interfaces suministradas por el entorno como Collection (ArrayList, Stack, List, Map, ArrayDeque)
	- Uso de tipos de datos genéricos (Template)
	- Manejo de errores. El no uso permite visualizar mejor el algoritmo utilizado.
	- Patrones de diseño
	- Para prueba de algoritmos, no se requiere
		- Ingreso de valores por teclado (class Scanner u otras)
		- Valores generados al azar (class Random u otras)
		- Interfaces visuales (Swing u otras)
		- Uso de archivos de texto, sólo para realizar pruebas de métodos solicitados por el TPO (class FileReader, BufferedReader u otras) para ingresar un volumen de datos que permita mostrar el funcionamiento del código.
	- Sólo se usarán vectores y/o matrices como tipos de datos privados dentro de las implementaciones.

## Consideraciones para un buen funcionamiento del grupo

### Formato
- Nombres de paquetes, interfases, implementaciones, nombre métodos
- Respetar los nombres establecidos.
- Paquetes
Los proyectos finales para entregar, generados con el IDE Eclipse, contendrán los siguientes paquetes, sólo con los archivos requeridos del trabajo práctico:
- miApi: contendrá los archivos de interfase de los TDA. Un archivo por TDA.
- misAplicaciones: contendrá dos programas uno para el punto 1-2 y otro para el punto 3.
- misImplementaciones: contendrá los archivos de las implementaciones. Uno por cada TDA.
- misAlgoritmos: contendrá los métodos usados en los algoritmos. No contendrá más de tres clases.

### Documentación
Cada uno de los archivos deberá estar documentado de la siguiente manera:
Todos los archivos deberán incluir los nombres de los participantes del grupo realizadores del trabajo práctico.
- Se deberá incluir en los archivos de:
- Interfases. Definición del TDA. Para cada método, tarea y precondiciones correspondientes.
- Implementación. Tipo de implementación, breve descripción de la estrategia implementada y costos temporales preponderantes para cada método.
- Algoritmos. Para cada método: tarea, precondiciones y costo temporal preponderante y espacial.

### TDA
Se entrega junto con estas normas las definiciones de los TDA a utilizar, en las que no podrán modificarse las operaciones y precondiciones establecidas en el documento.
Tanto las interfases como las implementaciones podrán incluir las entregadas en Tareas de Microsoft Teams o partes parciales de las publicadas en los archivos de clases.

### Proyecto exportado
Los dos proyectos que se entregarán serán exportados del IDE Eclipse, con extensión “zip”, dentro de estos archivos sólo se entregarán los archivos fuentes (extensión java) y los archivos utilizados de prueba.
En caso de que, se entreguen archivos de prueba modificados, se deberá documentar en un archivo de extensión “.txt” el que se deberá incluir en el proyecto exportado.

<hr>

# Enunciado
Una persona desea realizar un estudio sobre uso de streaming ofrecido por empresas. Para ello posee los siguientes datos
- Tabla de proveedores de Streaming, que contiene los códigos de los proveedores. Los códigos de los proveedores son únicos.
- Tabla de películas, que contiene los códigos de las películas. Los códigos de las películas son únicos.
- Lista de solicitudes de Streaming (Películas) con las personas que realizaron el pedido, en el orden en que se recibieron. La información incluida es: ID de la persona, código de proveedor, código de película como un número entero compuesto por la concatenación de los tres números. (IIIPPSSSS), donde III es el identificador de persona, PP es el código de proveedor y SSSS es el código de película.

Los rangos son:
- ID de la persona, entre 0 y 999. El Identificador (ID) es único y es el mismo para todas las empresas por lo tanto nunca se podrá repetir.
- Código de proveedor es de 0 a 99. Este es único.
- Código de películas es de 0 a 9999. Este es único

Se sabe además que
- Una película puede ser ofrecida por más de un proveedor de streaming.
- Una persona puede solicitar más de una vez la misma película a un proveedor.
- Una persona puede solicitar la misma película en diferente momento a empresas de Streaming distintas.

Se pide un algoritmo que a partir de los datos de las tablas que están cargadas en un TDA tablas y los movimientos están cargados en un TDA cola, guarde en TDAs adecuados la siguiente información:
- Lista de los 10 últimos movimientos ordenadas de los más recientes a los menos recientes
- Listado de personas que solicitaron la mayor cantidad de películas, ordenadas por identificador de personas
- Lista de películas que fueron más solicitadas, junto a que empresas fueron solicitadas cada una.
- Ranking de las 10 películas más solicitadas, independientemente a la compañía solicitada ordenadas por cantidad de veces que fueron solicitadas.
- Lista de personas que solicitaron películas iguales a distintas compañías, indicando película y compañía.

**Sugerencia:** 
Si se requiere identificar que una película corresponde a un proveedor específico puede adoptarse el código PPSSSS donde PP es el código del proveedor y SSSS es el código de película.

Usaremos una implementación estática para un TDA si se sabe que la cantidad de elementos a almacenar no supera los 100 elementos y una implementación dinámica si se desconoce la cantidad de elementos a almacenar o supera la cantidad de 100 elementos.

En caso de que para algunos métodos se requiera una implementación estática de un TDA y en otra una implementación dinámica, se utilizará para todos los métodos la implementación dinámica aunque no lo requiera.

Los costos temporales predominantes de los métodos no pueden superar el orden 3.
