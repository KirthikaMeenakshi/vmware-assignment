Number Generator Application Execution Instruction

1. Make sure java is installed in your system
   > java -version (in cmd prompt)
2. Copy/Download jar file from the Git repo 
3. Click on the jar file for Application to be executed
4. Use Postman/browser to execute the APIs

In Postman:

API 1: http://localhost:8080/api/generate                 
API 2: http://localhost:8080/api/tasks/{UUID}/getNumList - get descending number array for given Goal and step.     
API 3: http://localhost:8080/api/tasks/{UUID}/status - get the status of UUID generation.       
API 4: http://localhost:8080/api/tasks/getFilePath - To see the output files generated.           
API 5: http://localhost:8080/api/tasks/getAllTasks - To get all current tasks with UUID in system.
