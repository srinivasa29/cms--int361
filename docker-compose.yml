services:
  db:
    image: mysql:8.0
    container_name: smartcrm-mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_DATABASE: smartcrm_db
    volumes:
      - db_data:/var/lib/mysql
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    build: .
    container_name: smartcontactcrm
    depends_on:
      db:
        condition: service_healthy
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/smartcrm_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root123
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_JPA_SHOW_SQL: "true"

      SPRING_MAIL_HOST: smtp.gmail.com
      SPRING_MAIL_PORT: 587
      SPRING_MAIL_USERNAME: srinivasamannepula7@gmail.com
      SPRING_MAIL_PASSWORD: "axkf ssmi guka ifol"
      SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH: "true"
      SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE: "true"
      SPRING_MAIL_PROPERTIES_MAIL_SMTP_SSL_TRUST: smtp.gmail.com
      SPRING_MAIL_PROPERTIES_MAIL_DEBUG: "true"

    ports:
      - "8082:8082"
    restart: always

volumes:
  db_data:
