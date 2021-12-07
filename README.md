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

## Citation

```bibtex
@inproceedings{gruenewald2021datensouveränität,
  author    = {Elias Grünewald and Frank Pallas},
  title     = {Datensouveränität für Verbraucher:innen: Technische Ansätze durch KI-basierte Transparenz und Auskunft im Kontext der DSGVO},
  series = {Alexander Boden, Timo Jakobi, Gunnar Stevens, Christian Bala (Hgg.): Verbraucherdatenschutz - Technik und Regulation zur Unterstützung des Individuums},
  isbn      = {978-3-96043-095-7},
  doi       = {10.18418/978-3-96043-095-7\_02},
  url       = {https://nbn-resolving.org/urn:nbn:de:hbz:1044-opus-60219},
  pages     = {1 -- 17},
  year      = {2021},
}
```
See the related publication here (in German): https://pub.h-brs.de/frontdoor/index/index/docId/6021


