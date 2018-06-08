# DOTSUB Sample project

The design of the project is a classic Spring Boot web app that allows to upload files and to store metadata information into a database table.
It is just an example that I've build in the spare time at work.

You can find some comment inside, but anyway a possible improvement I think would be  to have the files stored inside the content store with a hash (like SHA-1) and then store that hash into the database together with the filename.

The frontend is a simple HTML page served by the service itself.

Run the app as a spring boot application and then go to: http://localhost:8080/ 