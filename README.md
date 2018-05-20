# LayzLiving - A Home Controller Model
----
This Project is a part of IoT Class in Mahidol University International College. The concept is to control the house model via Pub-Sub client using Mosquitto to communicate between Arduino Uno 3 in the model and our website. 

Try the UI at [https://layzliving.nuttapat.me](https://layzliving.nuttapat.me)

UI based on [Material Dashboard React](https://www.creative-tim.com/product/material-dashboard-react)

### Cloning Repository
------
```
> git clone --recursive <link>
```

### Sub folder details
-----


**Back End**

Developped using Spring Framework with RESTful API and deployed using docker-compose, as well as DroneCI for continuous integration

**Front End**

Developed using React JS, using Axios to link between backend and frontend.


### Running
----
```
> docker-compose up -d --build
```

This might take a while since both Maven and Node have to install their dependencies.

### Logging
----
```
> docker-compose logs -f
```