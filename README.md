# API GATEWAY Authentication

Add an extra layer of security for INTRANET apps to authenticate and consume a service hosted behind an api gateway that uses this service to authenticate its clients. It does so by generating JWTs after successful x509 authentication


## TODO
- afegir opcio de proporcionar sessionID o JWT.
  - Si s'utilitza JWT 
    - els rols del user autenticat son visibles
    - és més ràpid ja que només es valida el JWT i no s'han de fer queries a la BD per a autenticar
  - Si s'utilitza sessionID
    - es fa un fetch dels rols del user per cada request
    - es fa un fetch del sessionID a la BD per cada request
    - possibles CSRF ?
    - més adient per a autenticar external users ja que no exposem rols
  - També podem utilitzar JWT i fer el fetch dels rols a la BBDD enlloc d'utilitzar sessionID

- afegir opcio de utilitzar in-memory users o DB users
  - per a autenticar poques aplicacions d'una intranet podem utilitzar in-memory users llegint un JSON amb tots els users i rols
  - en canvi, si el servei es vol utilitzar amb molts usuaris, tindrem els usuaris en un BBDD
  - per a fer-ho podem proporcionar dos implementacions de AuthorizationService

- per a manejar la auth al userDetailsService
  - podem Utilitzar la implementació de AuthorizationService per a autenticar l'usuari i obtenir els rols
  - després podem passar el username i rols al controller per a que generi el JWT~~~~

<br><br>

# TLS configuration

- The application performs TLS mutual authentication
- The client that wishes to be authenticated must present a valid certificate trusted by the server
- The server then performs checks if the CN present in the client certificate is known by the server

To accomplish this we need to generate a root certificate that will be trusted by the applications that need to be authenticated. The root CA will sign both the client cert and the server cert. The CN specified in the client cert must be registered as a valid CN by the server.

The steps are detailed [here](docs/tls_configuration.md)