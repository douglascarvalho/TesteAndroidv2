![Image of Yaktocat](https://github.com/SantanderTecnologia/TesteiOS/blob/new_test/telas.png)

## Arquitetura (overview)

- MVVM
- Android Architecture Components (LiveData, ViewModel e Room)
- Clean
- Repository Pattern
- Feature Module
- Koin (injeção de dependência)

## Principais Bibliotecas

| Plugin | Finalidade |
| ------ | ------ |
| coroutines | Trabalhar com operações assíncronas  |
| retrofit | Comunicação com a Api |
| koin & koin-viewmodel| Injeção de dependência |
| kotter-knife | View binding para Kotlin |
| room | Biblioteca de persistência |
| cwac:saferoom | Encriptar base de dados com Room |
| mockito-kotlin | Mockito com sintaxe sugar para Kotlin  |
| mockwebserver | Servidor de mock para testes instrumentados |

## Módulos

### Feature

- **login** (contém os recursos necessários para apresentar a tela de Login)
- **statement** (contém os recursos necessários para apresentar a tela de Extrato)

### Libraries

- **action** (este módulo tem a responsabilidade de fornecer intents que funcionam como deeplinks para a comunicação entre features)
- **core** (este módulo contém as classes bases e os imports necessários para os módulos de feature)
- **extensions** (este módulo contém utilitários que podem ser utilizado em todo o projeto como ViewBindings e Extension Functions )
- **network** (este módulo contém a interface de comunicação com o backend)
- **style** (este módulo contém o tema principal do app)

## Por que módulos?

- Melhorar tempo de **build**
- Possibilitar **interoperabilidade** de feature / bilbioteca **entre projetos**
- Possibilitar **InstantApps**
- Possibilitar **Dynamic Features**

## O que pode ser aperfeiçoado?

#### cwac:saferoom
Mantive a encriptação desabilitada pois tive que lidar com alguns bugs utilizando o room do androidx. Até encontrar uma solução ou sair uma nova versão da lib achei válido manter a encriptação desabilitada.

#### Testes instrumentados
Lidei com alguns problemas para criar o setup dos testes instrumentados (koin, coroutines, retrofit). Desta maneira os testes instrumentados não estão válidos. Seria necessário mais um tempo para poder ajustar este ponto.

#### Módulo de validações
Hoje existe somente uma classe de validação que se encontra no módulo de Login, mas conforme o projeto fosse tomando tamanho, seria interessante ter um módulo que gerencie as validações para que outras partes do projeto possam utilizar evitando duplicidade de código.


## Como instalar no device

Rode o seguinte comando no diretório **TesteAndroidv2/bank**
```sh
$ ./gradlew installDebug
```
