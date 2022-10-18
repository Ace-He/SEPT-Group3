Firstly, I package all the service as jar. like this:

![image-20221018234844924](C:\Users\75058\AppData\Roaming\Typora\typora-user-images\image-20221018234844924.png)

After I got those jars, I used the following command to deploy these jars to docker.



For example, this is to build docker file:

[root@ip-172-31-29-49 PatientMicroService]# cat docker-compose.yml
version: '3.8'
services:
  patientmicroservice:
    image: patientmicroservice:1.0
    restart: always
    ports:

      - '8083:8083'
        command: java -jar app.jar



**docker-compose.yml** is to run the image.

"**docker compose up -d**" is the command to run docker-compose



This is the docker file:

[root@ip-172-31-29-49 PatientMicroService]# cat dockerfile
FROM openjdk:11
ARG JAR_FILE=PatientMicroService-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar



"**docker build -t patientmicroservice:1.0 -f dockerfile  --force-rm --no-cache**"  is the build image command



Then deploy docker on ec2. Then we can access the program by changing localhost to ec2's ip address.

For example:

![image-20221018235824546](C:\Users\75058\AppData\Roaming\Typora\typora-user-images\image-20221018235824546.png)

