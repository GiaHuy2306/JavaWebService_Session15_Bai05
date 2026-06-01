# Phân tích Auth API Đăng ký & Đăng nhập JSON

## 1. Vai trò của @RestController

`@RestController` là sự kết hợp của `@Controller` và `@ResponseBody`, giúp Spring Boot tự động chuyển đổi dữ liệu Java thành JSON để trả về cho client. Đây là annotation phổ biến khi xây dựng REST API.

## 2. Vai trò của @RequestBody

`@RequestBody` dùng để ánh xạ dữ liệu JSON từ request body sang đối tượng Java (DTO). Nhờ đó API có thể nhận dữ liệu từ ứng dụng mobile hoặc frontend một cách thuận tiện.

Ví dụ:

```json
{
  "username": "admin",
  "password": "123456"
}
```

sẽ được ánh xạ vào đối tượng LoginRequest.

## 3. Vai trò của ResponseEntity

`ResponseEntity` cho phép tùy chỉnh HTTP Status Code và dữ liệu trả về.

Ví dụ:

* 201 Created: Đăng ký thành công.
* 200 OK: Đăng nhập thành công.
* 401 Unauthorized: Sai tài khoản hoặc mật khẩu.
* 409 Conflict: Username đã tồn tại.

## 4. Vai trò của PasswordEncoder

`PasswordEncoder` dùng để mã hóa mật khẩu trước khi lưu vào hệ thống và kiểm tra mật khẩu khi đăng nhập. BCryptPasswordEncoder được khuyến nghị vì có cơ chế hash một chiều và hỗ trợ Salt giúp tăng tính bảo mật.
