# --- Giai đoạn 1: Build ứng dụng ---
FROM maven:3.8.8-eclipse-temurin-11 AS build
WORKDIR /app

# Copy file cấu hình Maven trước
COPY pom.xml .

# Ép Maven tải trước toàn bộ dependency về (loại bỏ go-offline tránh lỗi cache lỗi)
RUN mvn dependency:resolve

# Copy toàn bộ mã nguồn vào và build ra file WAR
COPY src ./src
RUN mvn clean package -DskipTests

# --- Giai đoạn 2: Khởi chạy ứng dụng ---
FROM tomcat:10.1-jdk11-temurin-jammy
WORKDIR /usr/local/tomcat

# Xóa các ứng dụng mặc định của Tomcat
RUN rm -rf webapps/*

# Copy file WAR đã được build thành công
COPY --from=build /app/target/DoAnCuoiKi-1.0-SNAPSHOT.war webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]