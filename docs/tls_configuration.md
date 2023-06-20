### Generate root CA
```shell
openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout rootCA.key -out rootCA.crt
```

### Generate server cert
1. CSR (Certificate Signing Request)
```shell
openssl req -new -newkey rsa:4096 -keyout localhost.key -out localhost.csr
```

2. EXT file
```shell
echo -e "authorityKeyIdentifier=keyid,issuer\nbasicConstraints=CA:FALSE\nsubjectAltName = @alt_names\n[alt_names]\nDNS.1 = localhost" > localhost.ext
```

3. Sign the CSR with the root CA
```shell
openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in localhost.csr -out localhost.crt -days 365 -CAcreateserial -extfile localhost.ext
```

4. Use PKCS 12 archive to package our server's private key together with the signed certificate
```shell
openssl pkcs12 -export -out localhost.p12 -name "localhost" -inkey localhost.key -in localhost.crt
```

### Generate keystore
1. Create keystore and import the pkcs12
```shell
keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS
```

### Generate trustore and import root CA
The CA imported in the trustore has to be the same that signs the certificate that the client will use to authenticate to the server
```shell
keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file rootCA.crt -keystore truststore.jks
```

### Generate client cert
1. CSR (Certificate Signing Request)
```shell
openssl req -new -newkey rsa:4096 -nodes -keyout clientBob.key -out clientBob.csr
```

2. Sign the CSR with the root CA
```shell
openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in clientBob.csr -out clientBob.crt -days 365 -CAcreateserial
```

3. Use PKCS 12 archive to package our server's private key together with the signed certificate
```shell
openssl pkcs12 -export -out clientBob.p12 -name "clientBob" -inkey clientBob.key -in clientBob.crt
```
We can now use the pkcs12 file to authenticate with the server.