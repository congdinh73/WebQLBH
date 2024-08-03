Giới thiệu:
- Đây là một ứng dụng web được xây dựng bằng framework Spring Boot, nhằm mục đích học tập.
  
Cài đặt
- git clone https://github.com/congdinh73/WebQLBH.git
- cd [tên thư mục dự án]
- mvn spring-boot:run

Công nghệ sử dụng:
- Spring boot: Framework xây dựng ứng dụng web.
- Spring MVC, Spring Data JPA, Hibernate, SQL Server, Thymeleaf, Spring Security, Bcrypt.

Cấu trúc dự án:
- /asm/controller: Chứa các controller để xử lý các request từ client.
- /asm/service: Chứa các lớp service và implementation để thực hiện các nghiệp vụ của ứng dụng.
- /asm/repository: Chứa các interface để tương tác với cơ sở dữ liệu.
- /asm/model: Chứa các DTO.
- /asm/domain: Chứa các entity đại diện cho các đối tượng trong hệ thống.
- /asm/config: Chứa các lớp xử lý Spring Security.
- /asm/interceptor: Chứa các lớp kiểm tra người dùng.
- /asm/exception: Chứa các lớp xử lý và kiểm tra ngoại lệ.
- uploads/image: Lưu trữ ảnh được upload lên client.
- /static/css: Chứa css cho trang chủ và trang admin.
- /static/images: Chứa logo và banner trang chủ.
- /templates/admin: Chứa templates trang admin.
- /templates/site: Chứa templates trang chủ.
  
Hướng dẫn sử dụng:
- Truy cập ứng dụng: Mở trình duyệt và truy cập vào địa chỉ http://localhost:8080.
  
Contributing:
- Fork repository.
- Tạo một branch mới cho tính năng hoặc sửa lỗi của bạn.
- Push các thay đổi lên branch của bạn.
- Tạo một pull request.

Liên hệ:
-Nếu bạn có bất kỳ câu hỏi hoặc góp ý, vui lòng liên hệ với tôi qua congdinh052@gmail.com.
