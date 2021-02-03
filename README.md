Chess Site
===============================

![MIT License](https://img.shields.io/github/license/that-apex/ChessSite)
![Travis (.com)](https://img.shields.io/travis/com/that-apex/ChessSite)
![Codecov](https://img.shields.io/codecov/c/github/that-apex/ChessSite?token=7405R79HLZ)

Live Chess website - a simple hobby project. My first attempt at using Spring's WebSockets for real-time web application.

Architecture
-------

### Backend

The backend is written in Spring Boot.

### Frontend

The frontend is written in React. It compiles to static html and communicates with the backend mainly via Rest API and via WebSockets for live games.

### Current goals, in order:

- [ ] Fully fledged chess library, implementing every rule of chess, written in pure Java.
- [ ] Simple account system with an SQL database.
- [ ] Playing simple real-time, live chess games via WebSockets.
- [ ] OAuth2 integration, logging in with a Google Account.
- [ ] Game history, account options, ELO points, tournaments system.
- [ ] Custom chess analysis engine, written from scratch (optional) 
