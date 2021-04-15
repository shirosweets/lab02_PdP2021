## Lab 2 - Programación Orientada a Objetos 2021

### TrendingTopics

## Configuración inicial

* Instalar Java 8.
  * Comprobar si lo tienen instalado y si es la versión correcta `$ java -v`
  * En ubuntu, se instala sólo con `sudo apt install openjdk-8-jdk`
  * Comprobar la variable de entorno `$JAVA_HOME`. Instrucciones [acá](https://docs.opsgenie.com/docs/setting-java_home)
* Instalar **Miniconda**
  * Instrucciones en el [sitio oficial](https://docs.conda.io/projects/conda/en/latest/user-guide/install/linux.html)
  * Configurar el entorno virtual e instalar jupyter lab
```bash
$ conda create -n paradigmas21 python=3.6
$ conda activate paradigmas21
$ conda install -c conda-forge jupyterlab
```
  * Comprobar que funciona ejecutando `jupyter-lab`
* Instalar almond **con scala 2.11.12**. [Sitio oficial](https://almond.sh/docs/quick-start-install)

```bash
$ curl -Lo coursier https://git.io/coursier-cli
$ chmod +x coursier
$ ./coursier launch --fork almond --scala 2.11.12 -- --install
$ rm -f coursier
```

* [Optional] Instalar Spark
  * Descargar Spark 2.4.7 def [sitio oficial](https://spark.apache.org/downloads.html)
  * Descomprimir en una carpeta, por ejemplo `/opt/spark`
  * Configurar `$SPARK_HOME` con el path a la carpeta de instalación

## Notebooks

En la notebook `pipeline_ner.ipynb` hay una prueba de concepto con un código
prototipo de código de una tubería que:
* Lee un feed RSS desde una URL
* Aplica un modelo NER a todos los artículos leídos
* Cuenta la frecuencia de las entidades con nombre detectadas y las ordena.

### Ejecución

Ejecutar los siguiente comandos:
```
$ conda activate paradigmas21
$ jupyter-lab
```

Debería abrirse una nueva pestaña en tu navegador. Abran la notebook y
traten de ejecutar la primera celda. Si obtienen un error de falta de kernel,
seleccionen el kernel Scala haciendo clic en la esquina superior derecha.

## Servicios standalone

### trendingner_standalone

Service autónomo que cuenta NERs con un modelo heurístico sencillo.
La entrada del programa es un archivo json con suscripciones con el siguiente formato:

```json
[
    {
        "url": "URL_TEMPLATE",
        "urlParams": ["PARAM1", "PARAM2"],
        "urlType": "rss or reddit"
    }
]
```
El programa imprime las 20 entidades nombradas con mayor frecuencia.

#### Ejecución

El programa toma un único argumento opcional con el nombre del archivo json con
las suscripciones. Para ejecutarlo, utilicen uno de los siguientes comandos:

```bash
$ sbt run
$ sbt "run <json_filename>"
```

### trendingner_standalone_spark

Service autónomo que cuenta NERs con un modelo neuronal pre-entrenado de Spark.
Tiene las mismas funcionalidades que  `trendingner_standalone`.

Para ejecutarlo, asegúrate de tener:
* Una buena conexión wifi
* Al menos 8GB de memoria RAM
* Un cpu multinúcleo razonablemente moderno

#### Ejecución

Antes de ejecutar el servicio por **primera vez** es necesario
configurar la biblioteca log4j si no lo has hecho antes:

```bash
cd $SPARK_HOME/conf # Folder where you installed SPARK, maybe /opt/spark
cp log4j.properties.template log4j.properties
```

Para compilar y ejecutar el servicio, ejecute:

```bash
sbt assembly
$SPARK_HOME/bin/spark-submit --class "edu.paradigmas.TrendingNer" --master local[2] --num-executors 2 --executor-memory 1g --driver-memory 1g target/scala-2.11/TrendingNER-assembly-0.1.0-SNAPSHOT.jar
```
