# Trello Clone
_A clone of Trello, written in many languages_

## Brief

This is a code playground to facilitate learning new languages with a realistic project.

The backend implementations include:

* Elixir
* Java
* Kotlin
* Node.js
* Python

The UI implementations include:

* React/redux
* Angular 4

## Architecture

The project will include the following components:

* RESTful endpoints for card/list management
* Background processing of asset uploads
* Realtime channel for broadcasting updates
* Authentication and authorisation

The exact implementation will differ per language and environment, in an attempt to remain as idiomatic as possible. For example, the Elixir implementation will make use of the available concurrency primitives and actor model, whereas Python may delegate to a distributed queuing system such as RabbitMQ or using coroutines provided by GEvent. Each implementation has a corresponding README with more detail about the specific implementations.

The UI will be implemented in both React and Angular, making use of the best practices in each ecosystem.
