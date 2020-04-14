# INSTRUCTIONS
 
 install postgresql
 
 create datababe Login/Group Role
- Name:postgres
- Password:postgres

 create database with the user you created
- database name:footballmanager

 insert the initial sql that is located in resources
 - scripts/initial.sql
 
Clone repository with one of the following ways and start coding.

with HTTPS:

```shell
git clone https://github.com/WhatIsCpp/football-manager.git
```

with SSH:

```shell
git clone git@github.com:WhatIsCpp/football-manager.git
```

## Install

For linux go to the folder where you cloned the project and execute the script below
```shell
mvn install
```

For Windows import the project to the IDE you are using
After it is imported use your maven to install

## Start The Project

Execute the below script with correct path
```shell
java -jar /path-to-your-project/target/demo-0.0.1-SNAPSHOT.jar
```

You can check teams, players and see how much you need to pay for a player.
Also you can check teams in specific years to see their team in that year.

## Endpoints
**api/v1/dictionary/countries**
Get the country values in DB

**api/v1/dictionary/currencies**
Get the country values in DB

**api/v1/football-player**
Post save or update a footballPlayer

**api/v1/football-player**
Get All footballPlayer's from DB

**api/v1/football-player**
Delete footballPlayer with given uFPI

**api/v1/football-team**
Post save or update a footballTeam

**api/v1/football-team**
Get All footballTeam's from DB

**api/v1/football-team**
Delete footballTeam with given uFTI

**api/v1/history**
Post save or update a footballPlayerFootballTeamHistory

**api/v1/history/footballPlayer/{footballPlayerUFPI}**
Get the History of given footballPlayeruFPI

**api/v1/history/footballTeam/{footballTeamUFTI}**
Get the Players that played in given team in given date

**api/v1/transfer**
Post Transfer the player with given inputs

**api/v1/transfer/calculateFee**
Get Calculate transferFee with given inputs

 ## Swagger Url For Testing
 
 http://localhost:8080/swagger-ui.html#/


