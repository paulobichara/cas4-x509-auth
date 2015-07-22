# cas4-x509-auth

This project provides a simple example of [CAS](https://jasig.github.io/cas/4.0.x/index.html) as SSO provider with alternative SSL client authentication. CAS is an amazing free and open source solution, easy to use, well documented and with a really active community. Also, don't be afraid to debug its source code for better understanding.

UniconLabs [maven overlay project](https://github.com/UniconLabs/simple-cas4-overlay-template) was my start point (and I really recommend you to do the same - it saves some time).

I've decided to make and share this project as reference implementation to myself and possibly others. The objective was to gather all distributed information that I've found in the internet in one place (from SSL CA creation until testing the final result). I should tell you in advance that the documentation presumes that you run a GNU/Linux environment and you have installed JDK7.

[1 Creating your private certification authority (CA)](https://github.com/paulobichara/cas4-x509-auth/wiki/1---Creating-your-private-certification-authority-(CA))

[2 Installing and configuring Apache Tomcat 8](https://github.com/paulobichara/cas4-x509-auth/wiki/2---Installing-and-configuring-Apache-Tomcat-8)

[3 Configuring, building and deploying cas4 x509 auth](https://github.com/paulobichara/cas4-x509-auth/wiki/3---Configuring,-building-and-deploying-cas4-x509-auth)

[4 Generating your client key and certificate](https://github.com/paulobichara/cas4-x509-auth/wiki/4--Generating-your-client-key-and-certificate)

[5 Testing our TLS client authentication (browser)](https://github.com/paulobichara/cas4-x509-auth/wiki/5---Testing-our-TLS-client-authentication-(browser))

