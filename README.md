# Augen Crypto Currency Testing

## Introduction

For getting the spot price, I chose the option #2: "Poll the Coinbase REST API with a 1 second interval".


Some Key Libraries and Framework are used:
* Spring Boot 2.4 as main web Framework
* Spring Kafka Stream
* Spring cloud FeignClient for rest client
* Lombok
* Builder Pattern

Main folders structure: 
* client: contains Rest client connector for RestAPI call 
* controller: contains web controller API classes
* dto: contains data to object classes
* service: contains service classes
* kafka: contains all kafka components: Producers, Processors, Consumers


Architect: 

     PriceProducer  |
                    | --> MarketProcessor --> MarketConsumer --> Storage <-- CalculatorService <-- RestAPI   
     ProfitProducer |

## How to run:

### Environment setup

1. Install Java : required to run the program
2. Install Maven if you want to build (I already built runnable jar files and let it in /target folder)
3. Install Local kafka server or use your existing kafka server. If so, please change kafka server config in application.yaml file.
4. The server will run at port 1979. If you want to change that please also update the file application.yaml.

### Run the server

Please patiently run services one by one for now, I not yet setup on-click running

1. Start Kafka Server 
* Start zookeeper
* Start Kafka server

2. Start Application: 
* Go to root folder
* Run startup.sh
* If you have problem with running the startup.sh script, please build then run as your basic way: java -jar ./target/exchange-0.0.1-SNAPSHOT.jar
 

### Run test API

Just try accessing the API uri: http://localhost:1979/api/v1/price?amount={wanted ammout}

You should get response lie

  ```
      {
          "spotPrice": 83309.00246016,
          "profitFactor": 0.05,
          "amount": 4.0,
          "totalPrice": 349897.81033267203,
          "id": "1d35a0b8-3ded-43b9-9fdf-4727b5166170"
      }
   ```

### Time break down
* Preparation and Investigation: 1 hour
* Main coding: 7 hours
* Unit and Integration Test: 2 hours
* Document: 30 mins
* Grand Total: 10.5 hours


# Thank you