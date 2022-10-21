FROM openjdk:11.0.16-jre-slim

RUN mkdir /addressor
COPY ./target/addressor-*.jar addressor/addressor.jar

VOLUME /addressor/logs
EXPOSE 2300

ENV PHOTON_IP="localhost"
ENV PHOTON_PORT=2322
ENV LOG_LEVEL=INFO

ENTRYPOINT ["java","-jar","/addressor/addressor.jar"]
CMD [" -Dphoton.ip=$PHOTON_IP -Dphoton.port=$PHOTON_PORT -DLOG_LEVEL=$LOG_LEVEL"]