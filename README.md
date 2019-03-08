TSE for TAT-C ML
=======================

This project contains the algorithm to enumerate architectures using either full factorial or intelligent search.


## Installation

To install necessary dependencies for the project, follow the steps below.

```shell

mvn install:install-file -Dfile=./lib/orekit-1.0.jar -DgroupId=seakers -DartifactId=orekit -Dversion=1.0 -Dpackaging=jar

mvn install:install-file -Dfile=./lib/mopAOS-1.0.jar -DgroupId=seakers -DartifactId=mopAOS -Dversion=1.0 -Dpackaging=jar

mvn package

```
where the trailing period (`.`) indicates to install from the current directory.


## Usage

Executable script is provided in the `src/main/java/tatc` directory.

### Run the TSE

Executes a full-factorial tradespace search or intelligent search including enumeration (generating architectures):

```shell
java -jar ./target/tatc-ml-tse.jar
```

Outputs:
```
|-- problems/
    |-- TradespaceSearch.json
    |-- arch-0/
        |-- arch.json
    |-- arch-1/
        |-- arch.json
        |-- ...(outputs)...
    |-- arch-2/
        |-- arch.json
```
