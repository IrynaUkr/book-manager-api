# 📖 Minimalist Book Manager API

The functionality of app has methods:
- Get All Books
- Get a Book by ID
- Add a Book
- Update a Book by id
- Delete Book by id

All  methods are called using REST API.

**The controller** handles the user HTTP request, using matching to the endpoint and request mapping.
Controller's  aim is receive the request and call the appropriate the service's method.  
Controller has an _@RestController_ annotation.
It  indicates that the data returned by each method will 
be written straight into the response body instead of rendering a template.

Due to the annotation, this class will be auto-detected through classpath scanning and the methods annotated
with _@RequestMapping_ annotation will be exposed as HTTP endpoints.
When an incoming request matches the requirements specified by the _@RequestMapping_ annotation,
the method will execute to serve the request.
**Service** is responsible for the processing request and accordingly to the request calls 
the appropriate **repository** method.

**Repository** is an Interface, which extends CrudRepository and parametrized with  specific Entity.
CrudRepository is a Spring Data interface for generic CRUD operations on a repository of a specific type.
It provides several methods out of the box for interacting with a database.
Using these methods we can have the high level of abstraction
and access the database layer for retrieving, deleting, creating and updating data.


The Edge cases:
- the id is unique, so if someone wants to add a book with an ID for a book that already exists, 
the method throws BookAlreadyExist Exception.

 - if someone wants to find a book by an ID that doesn't yet exist,
 the method throws BookNoFound Exception.

- if someone wants retrieve or update book which does not exist the BookNotFound Exception will be thrown.

  
