FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./amper build

RUN ./amper run -m init

EXPOSE 8080

CMD ["./amper", "run", "-m", "server"]