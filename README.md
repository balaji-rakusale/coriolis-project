
# Student Registration

This application will provide facility for students to register for different courses
available at institute. Institute will provide multiple course and every student can
register for different courses of choice.


## API Reference

#### Save Course

```http
  POST /courses/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `RequestBody` | `JSON` | **Required** |

#### Get all Courses

```http
  GET /courses
```

#### Get Course by ID

```http
  GET /course/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of course to fetch |

#### Save Student

```http
  POST /student/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `RequestBody` | `JSON` | **Required** |

#### Get all Student

```http
  GET /students
```

#### Get Student by ID

```http
  GET /student/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of course to fetch |

#### Register Student

```http
  POST ${id}/courses/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id` ,`id`     | `int`,`int`| **Required**. Ids for register  |




## Deployment

It is Spring Boot application which has embedded tomcat server. It will produce executable jar file.

To deploy this project run

```bash
  java -jar jarname
```


## Documentation

Swagger documentation not supporting spring boot 3.0.2 version. So added javadoc in the project itself.

I have created spring boot application with in memory databse (h2 database) provided by spring boot. So if we terminated the application whole data will be gone data is present till application is up.

I have created two tables for course and student where 
and they are in one to many mapping.

We have set of services like we can create student and course separately and register a student for courses.


Also providing all the endpoints which are exposed.

Course 

    /course/add   - Add Course
    /courses      - Get all the courses
    /course/{id}  - Get course by id

Student

    /student/add      - Add Student
    /students         - Get all the students
    /student/{id}     - Get student by id
    {id}/courses/{id} - Register student      

    




## Environment Variables

To run this project, you will need not to add any environment variables to your .env file



## Features

- More Readable 
- Unit Tested
- Scalable
- Platform Independent 


## Feedback

If you have any feedback, please reach out to us at balajirakusale@gmail.com


## Installation

No need of any installtion of third party software just required java and any IDE 
    
## Running Tests

No need to run test cases separately they will automatically runs when you build or run the main application


