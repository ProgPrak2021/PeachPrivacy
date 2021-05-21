# Welcome to Peachprivacy!

**Peachprivacy** is  a visual  privacy generation Tool in the 
form of a Website.



# Concept   
On a well designed website  you can  intuitive  create  projects  to model   for  your own privacy protection page with templates. These templates  can in  a object-oriented style inherit  from (predefined) templates and can created   by your own. Each template can define abstract fields/values , which should be filled in the configuration .With these templates you can  generate tilt documents , which are available via a public URL.



## Technologies 
**Spring Cloud** to realise a  Cloud-Native Microservice Architektur

**Template-Service** to save/read  the Templates
from  **MongoDB** and and to render the corresponding requests.

**User-Service** to execute user-related actions

**Tilt-Service** to save the meta data of the templates in
**PostgreSQL** and to provide the the public Endpoints for the 
generated tilt documents 

## License 
This project is licensed under the MIT License - see the [LICENSE.text](https://github.com/p4skal/peachprivacy/blob/master/LICENSE.txt) file for details



