# zookeeper-demo
Demo de integração de Spring com Zookeeper.

## Premissas!
É necessário ter um servidor ZK disponivel com as configurações já definidas.
Pode ser utilizado o docker-compose na raíz desse projeto, ele irá subir um container do ZooKeeper, Mongo e Mongo Express.

Executar o docker-compose:
Obs: Utilizar -d para executar em background 

        $ docker-compose up
        $ docker-compose up -d

Após o ZK disponível, é necessário criar as configurações iniciais.
Para isso é necessário conectar-se ao zookeeper no container do ZK e utilizar o zkCli.sh que está em 
./zookeeper-3.4.13/bin/zkCli.sh

Após conectado, executar as seguintes instruções:

        create /config/zookeeper-demo/mongo 192.168.99.100:27017
        create /config/zookeeper-demo/service1 192.168.99.100:9090
        create /config/zookeeper-demo/service2 192.168.99.100:9091
        create /config/zookeeper-demo/service3 192.168.99.100:9092
        create /config/zookeeper-demo/service4 192.168.99.100:9093

**Obs:**
Nesta demo foi utilizado o dockerToolbox para compatibilidade com windows 8. 
Para ambientes Linux e Win 10, o host deve ser substituído de 192.168.99.100 --> localhost

