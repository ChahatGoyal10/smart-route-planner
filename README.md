Smart Route Planner

A full-stack navigation and travel planning web application that simulates a real-world route planning system. The application combines Data Structures & Algorithms with full-stack development to calculate optimal travel routes and manage personalized travel data.

The system models cities as nodes and roads as weighted edges in a graph data structure, and computes the shortest path using Dijkstra’s Algorithm.

Features
Route Planning

Find optimal routes between cities

Shortest path calculation using Dijkstra’s Algorithm

Real road visualization using OpenRouteService API

Interactive map using Leaflet.js and OpenStreetMap

Travel Simulation

Traffic simulation based on departure time

Destination weather information using OpenWeatherMap API

User Features

User authentication (Signup / Login)

Password encryption using BCrypt

Personalized travel data for each user

Travel Management

Save route history

Add places to a Bucket List

Submit Reviews and Ratings

Save and manage Favourite Routes

Communication

Contact form with Email Notifications

Emails sent using Spring Boot Mail (JavaMailSender)

Dashboard

"My Travel" dashboard for managing:

Bucket List

Reviews

Saved Routes

Favourite Routes

Tech Stack
Frontend

HTML

CSS

JavaScript

Leaflet.js

OpenStreetMap

Backend

Java

Spring Boot

Spring Security

JavaMailSender

Database

MySQL

Build Tool

Maven

System Architecture
Client (Browser)
       ↓
Frontend (HTML / CSS / JS)
       ↓
REST API Requests
       ↓
Spring Boot Controllers
       ↓
Services (Business Logic)
       ↓
Repositories (JPA)
       ↓
MySQL Database

The system follows a Spring Boot MVC architecture.

Graph Algorithm

Cities and roads are modeled as a graph.

City → Node
Road → Edge
Distance → Weight

The shortest route between cities is computed using Dijkstra’s Algorithm.

Example:

Delhi → Jaipur → Udaipur

The backend calculates the shortest path and sends it to the frontend for map visualization.
External APIs Used
OpenRouteService API

Used for generating realistic road routes.

https://api.openrouteservice.org
OpenWeatherMap API

Used to display weather information for destination cities.

https://api.openweathermap.org
