NGINX sử dụng mô hình cấu hình process nghĩa là có thể điều chỉnh phụ thuộc vào tài nguyên phần cứng hiện có:
- Master process thực thi các hành động như đọc config và cấu hình port sau đó tạo các các process con (theo 3 loại dưới đây)
- Cache loader process chạy từ khi hệ thống bắt đầu để tải các dữ liệu từ ổ đĩa vào bộ nhớ sau đó ngắt hoạt động.
- Cache manager process chạy định kì và thay đổi các entry từ ổ đĩa để giữ dữ liệu đúng kích thước
- Worker process làm hầu hết các công việc. Xử lý các kết nối internet, đọc và ghi dữ liệu vào đĩa, giao tiếp giữa các server upstream

Mỗi một worker nên tương ứng với một CPU

Khi server NGINX bắt đầu chạy, chỉ có worker process hoạt động. Các worker xử lý lượng lớn các kết nối trong một thiết kế non-blocking, làm giảm bớt lưu lượng lưu thông.

Mỗi worker được chạy dưới single-threaded và độc lập, kèm theo các kết nối mới và đang xử lý theo chúng. Các worker có thể giao tiếp với nhau thông qua shared memory, sử dụng để chia sẻ cache data, persistance data và các shared resources khác

### Inside the NGINX Worker Process
Mỗi worker được khởi tạo thông qua các configuration của NGINX và được cung cấp kèm với các cổng kết nối socket bởi master process

Worker bắt đầu đợi các events trên các socket (mutex và kernel socket). Các event được khởi tạo bởi các connection mới. Các connection đó được gắn vào một state machine - HTTP state machine là cái thông dụng nhất, nhưng trong NGINX cũng có một state machine của riêng nó để truyền tải (stream) (raw TCP) traffic và dành cho một số giao thức mail (SMTP, IMAP and POP3)

State machine là một cái quan trọng tất yếu trong hệ thống thể hiện rằng cách NGINX xử lý một request. Đa số các web servers thực thi các hàm tương tự như cách NGINX sử dụng state machine - các cách thực thi khác nhau của một implementation

### Scheduling the State Machine
Cứ coi state machine như bộ luật của cờ vua. Mỗi một yêu cầu HTTP là một ván game. Web server đóng vai như một người chơi - một grandmaster người có thể đưa ra lựa chọn rất nhất chóng. Mặt khác đối thủ chính là remote client - web browser truy cập vào hệ thống trên một đường mạng chậm rãi.

Tuy nhiên, luật chơi của ván cờ có vẻ hơi phức tạp. Ví dụ, web server có thể cần phải nói chuyện với các "đồng đội" hoặc giao tiếp với server xác thực. Module bên thứ ba trong web server thậm chí còn có thể làm luật chơi phức tạp hơn.

### A Blocking State Machine
Như đã biết, kiến trúc của NGINX được xây dựng để một hệ điều hanh vẫn có thể sử dụng với một CPU Core. Đại đa số các web server và web application sử dụng process-per-connection hoặc thread-per-connection để chạy. Mỗi process hoặc thread chứa các thông tin để chơi một trò chơi cho đến cùng. Trong khoảng thời gian các process chạy, nó phải dừng và đợi các client hoàn tất bước đi tiếp theo.

1. Web server lắng nghe một connection mới trên listen socket.
2. Khi nhận được trò chơi mới, nó bắt đầu chơi, dừng và đợi từng bước để client trả lời.
3. Khi ván game hoàn thành, web server có thể đợi client có muốn bắt đàu một ván mới hay không. Nếu connection đóng lại, web server trả process về để tiếp tục lắng nghe lời gọi mới.

Điều quan trọng ở đây là với mỗi HTTP connection (chess game) đều yêu cầu các dedicated process hoặc thread (a grandmaster). Kiến túc này đơn giản và đễ dàng triển khai với cả các module bên thứ 3. Nhưng, đó là một sự dư thừa lớn, với các HTTP connection dù nhỏ cỡ nào, đều được quản lý bởi các file descriptor và một khối lượng nhỏ memomry, với việc được gán vào các thread, process tách việt, một object của hệ điều hành rất nặng. Một sự tiện lợi dành cho triển khai, nhưng là một sự dư thừa cực kì lớn về hiệu năng

### NGINX is a True Grandmaster
Có vẻ bạn đã nghe tới simultaneous exhibition, luật mà một grandmaster có thể đấu với nhiều người cùng lúc.

Đó là cách mà worker của NGINX chơi cờ. Mỗi worker là một grandmaster có thể chơi hàng trăm ván cùng một lúc

1. Worker lắng nghe các event từ listen, socket
2. Các event xuất hiện trên socket và worker nắm bắt chúng
	- Một event tương ứng với một ván game mới, khi đó worker sẽ tạo một connection mới
	- Mỗi một event trên một connection socket đại diện cho một client đã thực thi xong một bước đi mới. Khi đó worker sẽ phản hồi ngay lập tức.

Worker sẽ không bị vướng bởi các network traffic, đợi chờ đối thủ để phản hồi. Ngay khi vừa thực hiện một bước đi mới, worker sẽ chuyển sang game khác ngay lập tức nơi mà đang đợi worker phản hồi, hoặc một người chơi mới vào.

### Why is This Faster than Blocking, Multi-Process Architecture
NGINX có khả năng mở rộng hỗ trợ lên đến hàng trăm hàng ngàn connection. Mỗi connection mới sẽ tạo ra một file descriptor mới và sử udnjg mỗi khối lựng nhỏ memory trên worker. Đây là một điểm lợi nhỏ so với per connection. NGINX process có thể được gán vào các CPU nhất định. Các sự thay đổi chuyển đổi dữ liệu ở các phần cứng ít khi xảy ra và chỉ chỉ xảy ra khi các worker không còn việc

Đối với Blocking, sử dụng connection-per-process, mỗi connection yêu cầu một khối lượng lớn tài nguyên và overhead, and context switches xảy ra thường xuyên.

Chi tiết hơn, có thể đọc bài báo ... về NGINX được viết bởi Andrew Alexeev

Với sự tích hợp của system tuning, NGINX có thể mở rộng lên dến hàng trăm, hàng ngàn các HTTP connection đồng thời với mỗi worker, và có thể  xử lý được các lỗi mà vẫn có thể hoạt động bình thường
 