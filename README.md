# Mutantes

# Requisitos
- Java 11

#Ejecutar

Para compilar el proyecto se debe ejecutar
```
mvn clean package
```
y se ejecuta con 
```
java -DAWS_DYNAMODB_ENDPOINT=http://localstack:5669 -DAWS_ACCESSKEY=id -DAWS_SECRETKEY=key -jar mutantesApi-0.0.1-SNAPSHOT.jar
```
para subir la infraestructura es necesario levantar el docker compose
```
docker-compose up -d
```

## Con docker
contruir la imagen 
```
docker build . -t mutante
```
correr la imagen
```
docker run -p 8080:8080 mutante
```

# Api
para saber si es un mutante hay que hacer una solicitud `POST` al endpoint `/mutant`
con un body
```json
{
"dna": [
        "atGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAATG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
## Aws
El servicio esta expuesto en `http://52.67.30.20:8080/`.
Está deplegado en AWS, a través de un ECS, y para soportar la carga se configuro un ALB y un auto-eslamiento.

## Covertura
Tiene una cuvertura de mas del 95% del código