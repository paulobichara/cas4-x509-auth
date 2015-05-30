# cas4-x509-auth

SSL Steps

openssl genrsa -out tomcat.key 2048

openssl req -new -key tomcat.key -out tomcat.csr

openssl x509 -req -in tomcat.csr -CA ca.pem -CAkey ca.key -CAcreateserial -out tomcat.crt -days 500

openssl pkcs12 -export -in tomcat.crt -inkey tomcat.key -out tomcat.p12 -name tomcat -CAfile ca.pem -caname root -chain

keytool -importkeystore -srckeystore tomcat.p12 -srcstoretype pkcs12 -srcalias tomcat -destkeystore tomcat.jks -deststoretype jks -deststorepass changeit -destalias tomcat

keytool -import -file ca.pem -alias rootCA -keystore cacerts.jks

openssl genrsa -out browser.key 2048

openssl req -new -key browser.key -out browser.csr

openssl x509 -req -in browser.csr -CA ca.pem -CAkey ca.key -CAcreateserial -out browser.crt -days 500

openssl pkcs12 -export -in browser.crt -inkey browser.key -out browser.p12 -name browser -CAfile ca.pem -caname root -chain


