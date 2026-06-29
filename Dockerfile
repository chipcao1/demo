# --- Giai đoạn 1: Build ứng dụng (Nâng cấp lên Java 17) ---
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy file cấu hình Maven trước
COPY pom.xml .

# Ép Maven tải trước toàn bộ dependency về
RUN mvn dependency:resolve

# Copy toàn bộ mã nguồn vào và build ra file WAR
COPY src ./src
RUN mvn clean package -DskipTests

# --- Giai đoạn 2: Khởi chạy ứng dụng với Tomcat hỗ trợ Java 17 ---
FROM tomcat:10.1-jdk17-temurin-jammy
WORKDIR /usr/local/tomcat

# Xóa các ứng dụng mặc định của Tomcat
RUN rm -rf webapps/*

# Copy file WAR đã được build thành công
COPY --from=build /app/target/DoAnCuoiKi-1.0-SNAPSHOT.war webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
