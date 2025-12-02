# 🏨 Hotel API - Gestão de Reservas

Projeto desenvolvido com **Spring Boot 3** e **Java 21**, com foco em demonstrar boas práticas de back-end, organização em camadas e API REST para gerenciamento de **hóspedes, quartos e reservas**.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database (Ambiente dev)**
- **Lombok**

---

## 📦 Funcionalidades Principais

✔️ Cadastro de hóspedes  
✔️ Cadastro de quartos  
✔️ Registro de reservas  
✔️ Regras de validação (datas, disponibilidade)  
✔️ Consulta de reservas e entidades  
✔️ Tratamento básico de erros

---

## 🧱 Estrutura do Projeto

Arquitetura baseada em camadas:

controller/ → camada de acesso HTTP (REST)
service/ → regras de negócio
repository/ → persistência usando JPA
model/ → entidades
dto/ → objetos de transferência
exception/ → tratamento de erros

yaml
Copiar código

Essa separação facilita manutenção, testes e reuso.

---

## 🛠️ Como executar

### Pré-requisitos
- **Java 21**
- **Maven**

### Passos

```bash
git clone https://github.com/<seu-usuario>/<nome-repositorio>.git
cd <nome-repositorio>
mvn spring-boot:run

A API estará disponível em:
http://localhost:8080

💾 Banco de Dados
O projeto utiliza H2 em memória, ideal para desenvolvimento e testes rápidos.


📚 Endpoints Principais
👤 Hóspedes
Método	Endpoint	Descrição
POST	/hospedes	Cadastra hóspede
GET	/hospedes	Lista todos
GET	/hospedes/{id}	Busca por ID
PUT	/hospedes/{id}	Atualiza dados
DELETE	/hospedes/{id}	Remove hóspede

🛏️ Quartos
Método	Endpoint	Descrição
POST	/quartos	Cadastra quarto
GET	/quartos	Lista todos
GET	/quartos/{id}	Busca por ID
PUT	/quartos/{id}	Atualiza dados
DELETE	/quartos/{id}	Remove quarto

📅 Reservas
Método	Endpoint	Descrição
POST	/reservas	Registra reserva
GET	/reservas	Lista todas
GET	/reservas/{id}	Busca por ID
PUT	/reservas/{id}	Atualiza datas
DELETE	/reservas/{id}	Cancela reserva

🛡️ Tratamento de Erros
O projeto possui uma camada de exceções para:

Datas inválidas

Disponibilidade do quarto

Entidades não encontradas

Regras de negócio

As respostas seguem padrões REST com mensagens claras.

📈 Possíveis Melhorias
Autenticação JWT

Controle de perfis (Admin / Funcionário)

Paginação em listagens

Testes unitários e integração (JUnit / Mockito)

Histórico de reservas

Versionamento de API (v1/v2)

