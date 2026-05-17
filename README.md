## Arquitectura de modernización para procesamiento crediticio desacoplado orientada a eventos, diseñada para integrar plataformas digitales con Core Banking legacy minimizando acoplamiento, mejorando resiliencia operacional y habilitando escalabilidad horizontal

### Apache Kafka + Apache Camel + Spring Boot + Bantotal Integration ###

1. Problema
Los sistemas core bancarios legacy presentan:
- alto acoplamiento
- integraciones síncronas frágiles
- baja escalabilidad
- dificultad para incorporar nuevos canales digitales

2. Propuesta
Implementar una arquitectura event-driven desacoplada basada en Apache Kafka y Apache Camel para separar decisiones de negocio, integración y procesamiento operacional.

3. Beneficios
- Resiliencia ante fallas del Core Banking
- Integración desacoplada
- Escalabilidad independiente
- Reprocesamiento seguro
- Mayor observabilidad
- Evolución incremental
- Menor impacto en sistemas legacy

4. Capacidad futura
La arquitectura permite incorporar nuevos consumidores de eventos sin afectar servicios existentes:
- fraude
- analytics
- auditoría
- machine learning
- omnicanalidad

**Componentes del proyecto:**

**Archivo docker-compose.yml**

**1 kafka-bank:**
image: confluentinc/cp-kafka:7.5.0
Imagen oficial de Docker proporcionada por Confluent que contiene una distribución empaquetada y lista para desplegar de Apache Kafka 

**2 kafdrop-bank:**
Una interfaz web para monitorizar clústeres de Apache Kafka. La herramienta muestra información como brokers, temas, particiones, consumidores (incluido el retardo) y permite ver los mensajes.

**3 camel-credit-demo:**


![Imagen proyecto de arquitectura_bus_evento](./arquitectura_event_driven.png)
