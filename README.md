# Docusearch - Document Metadata extract and search

[Design document](design_document.md)

We need below softwares installed in the target system to run the application properly
```
Java 11+
Apache Maven
Docker 
```
All the dependent items can be started using docker compose. Run below command to run the dependent items using

```
docker-compose -f docker-compose.yaml up -d
```
or
```aidl
bash run.sh
```

If Docker is not installed, then we need below software's:
```aidl
Kafka
Elastic Search
```

and run 
```aidl
bash start.sh
```

once the docker compose starts all the dependency, we can start our web application for development, or we can specify the same in docker compose file itself

For running the test we can run below command
```
mvn test
```

Note: Before running the application code, we need to generate the access token for dropbox account and update in application.yaml file for the property : docusearch.system.dropbox.accessToken .

application.yaml file location:
1. [extractor](extractor/src/main/resources/application.yaml)
2. [api](api/src/main/resources/application.yaml)

* [Spring ](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

