# BREEZ - Projeto Spring

## Pré-requisitos

- Java JDK 17 ou superior
- Maven
- IDE de sua preferência (recomendamos IntelliJ IDEA ou Spring Tool Suite)
- PowerShell (para configuração das variáveis de ambiente)

## Configuração das Variáveis de Ambiente

Antes de executar o projeto, é necessário configurar as variáveis de ambiente com as chaves de API necessárias. Abra o PowerShell e execute os seguintes comandos:

```powershell
$Env:TOM_TOM_API_KEY = "sua_chave_tomtom"
$Env:OPENAI_API_KEY = "sua_chave_openai"
$Env:CLIENTE_SECRET = "sua_chave_google_cloud"
$Env:DB_PASSWORD = "sua_senha_azure"
```

### Configuração Permanente (Opcional)

Para tornar as variáveis de ambiente permanentes, você pode adicionar elas às variáveis de ambiente do Windows:

1. Abra as Configurações do Sistema
2. Clique em "Variáveis de Ambiente"
3. Na seção "Variáveis do Sistema", clique em "Novo"
4. Adicione cada uma das variáveis:
   - TOM_TOM_API_KEY
   - OPENAI_API_KEY
   - CLIENTE_SECRET
   - DB_PASSWORD

## Executando o Projeto

1. Clone o repositório ou abra a pasta BREEZ que enviamos junto com essa pasta:
```bash
git clone [https://github.com/HenriqueFurtado-Dev/BREEZ.git]
```

2. Navegue até a pasta do projeto:
```bash
cd BREEZ
```

3. Compile o projeto com Maven:
```bash
mvn clean install
```

4.1 Executando os testes:

O projeto possui uma suíte completa de testes unitários e de integração. Para executar os testes, você pode usar os seguintes comandos:

```bash
# Executar todos os testes
mvn test
```

Os testes incluem:
- Testes de Controllers (RegisterControllerTest)
- Testes de Serviços (UserServiceTest, EmergencyServiceTest)
- Testes de Integração (BreezApplicationTests)

Durante os testes, é utilizado um banco de dados H2 em memória e configurações específicas para teste (definidas em `application-test.properties`).

4.2 Execute o projeto:
```bash
mvn spring-boot:run
```

Acessar `http://localhost:8080`

## Endpoints Disponíveis

### Páginas Principais
- `/` - Página inicial com mapa (TomTom API)
- `/login` - Página de login
- `/register` - Página de registro de usuário
- `/emergency` - Página de contatos de emergência
- `/stats` - Página de estatísticas

### Base de Conhecimento
- `/conhecimento` - Lista todos os artigos
- `/conhecimento/new` - Formulário para criar novo artigo
- `/conhecimento/{id}` - Visualizar um artigo específico
- `/conhecimento/{id}/edit` - Editar um artigo existente
- `/conhecimento/{id}/delete` - Excluir um artigo

### Monitoramento de Temperatura
- `/temperatura-realtime` - Lista das últimas 100 temperaturas registradas
- `/temperatura-realtime/registrar` - Formulário para registrar nova temperatura
- `/temperatura-realtime/grafico` - Visualização gráfica das temperaturas
- `/temperatura-realtime/api/dados` - API para dados de temperatura (aceita parâmetro `regiao`)

### API de Chat
- `/api/ask` - Endpoint para interagir com o assistente virtual (POST)


