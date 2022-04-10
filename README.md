# MAT (Meet At The office)
## Getting started
Mat use graddle(7.4.2) to build all project.
If you need to know wich action you can do use gradlew task

You need only a docker installed on your pc

To start launch in terminal : `gradlew initThemAll`

## How code
### Backend 
Use `gradlew backendAttach`, your favorite browser open with http://localhost:8090 (quarkus documentation)
Your terminal will be connected to quarkus instance, you can now edit your code in folder mat-backend all changes will be effective after a http request on the quarkus instance.

### FrontEnd
Use `gradlew openWeb`, you can now edit the web app in folder mat-frontend
## Clean up my project

Use `gradlew killThemAll`