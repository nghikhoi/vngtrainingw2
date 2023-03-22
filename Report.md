# Report: Warm Up

## 1. Basic Knowledge
### 1.1. Source control
**Khác biệt đặc trưng giữa Git và SVN**
|Git|SVN|
|--|--|
|Hệ thống quản lý phân tán|Hệ thống quản lý tập trung|
|Khi cần thay đổi có thể cập nhật bản copy trên máy tính và cập nhật lên repo sau. Hầu hết các thao tác cập nhật không cần mạng.|Các thay đổi được cập nhật thẳng lên repo. Yêu cầu mạng để thực thi các thao tác.
|Git xử lý một khối lượng lớn file nên tốc độ tương đối chậm hơn SVN.|SVN có thể xử lý khối lượng lớn file dễ dàng.

References:
- [Difference Between GIT and SVN - GeeksforGeeks](https://www.geeksforgeeks.org/difference-between-git-and-svn/)
- [medium.com](https://medium.com/@odsc/git-vs-svn-whats-the-difference-2c7072f7679f?source=post_internal_links---------1----------------------------)

**Git Flow - [Vincent Driessen](https://nvie.com/posts/a-successful-git-branching-model/)**
Gitflow Workflow định nghĩa một cấu trúc phân nhánh nghiêm ngặt xung quanh bản release của dự án. Điều này cung cấp thêm một framework mạnh mẽ để quản lý các dự án lớn.
Các nhánh trọng tâm:
- master: chứa mã nguồn khởi tạo và các tag version
- hotfix: dựa trên master, sử dụng trong trường hợp fix các bug xảy ra trên production
- release: nhánh chính được sử dụng để triển khai sản phẩm đưa đến người dùng
- develop: nhánh chính được sử dụng cho quá trình phát triển
- nhóm nhánh feature: các nhánh phụ được sử dụng trong quá trình phát triển. Thông thường mỗi nhánh sẽ đại diện cho một chức năng trong sản phẩm. Khi hoàn thành sẽ được merge vào nhánh develop
Các nhánh phụ:
- pre-pro: sử dụng cho user-test
- QC: sử dụng cho việc test
- BugFix: để cho việc fix các bug thông thường
Lưu ý quan trọng: luôn sử dụng **pull request** nhằm mục đích báo các thay đổi cho người review và cũng để lưu lại rõ ràng lịch sử thay đổi các branch.

References:
- [Cơ bản về Gitflow Workflow (viblo.asia)](https://viblo.asia/p/co-ban-ve-gitflow-workflow-4dbZNn6yZYM)
- [A successful Git branching model » nvie.com](https://nvie.com/posts/a-successful-git-branching-model/)


### 1.2. Linux shell/bash script
- ssh: cung cấp một phương thức kết nối bảo mật đến máy chủ. Kết hợp với các lệnh (ssh-gen, ssh-add để tạo/nhập key bảo mật) 
- scp: sử dụng để chuyển file giữa client và server dựa trên ssh
- mv: sử dụng để chuyển file giữa các thư mục
- rm: sử dụng để xóa file
- cp: sử dụng để copy file
- telnet: là phương thức kết nối đơn giản sử dụng username và password
- netstat: bộ công cụ sử dụng để truy vấn, kiểm tra các tiến trình sử dụng các cổng kết nối mạng, bộ định tuyến, thống kê mạng,...
- kill: sử dụng để ngắt ngang các tiến trình
- process: bộ công cụ sử dụng để truy vấn kiểm tra danh sách các tiến trình

References:
- [ssh command usage, options, and configuration in Linux/Unix.](https://www.ssh.com/academy/ssh/command#ssh-command-in-linux)
- [Làm thế nào sử dụng SCP command để chuyển file (hostinger.vn)](https://www.hostinger.vn/huong-dan/lam-the-nao-su-dung-scp-command-de-chuyen-file)
- [mv command in Linux with examples - GeeksforGeeks](https://www.geeksforgeeks.org/mv-command-linux-examples/)
- [rm command in Linux with examples - GeeksforGeeks](https://www.geeksforgeeks.org/rm-command-linux-examples/)
- [cp command in Linux with examples - GeeksforGeeks](https://www.geeksforgeeks.org/cp-command-linux-examples/)
- [Telnet Command Usage in Linux/Unix | DigitalOcean](https://www.digitalocean.com/community/tutorials/telnet-command-linux-unix)
- [Linux Networking: Sử dụng Netstat quản lý mạng trên Linux (viblo.asia)](https://viblo.asia/p/linux-networking-su-dung-netstat-quan-ly-mang-tren-linux-Az45bL7oZxY)
- [kill command in Linux with Examples - GeeksforGeeks](https://www.geeksforgeeks.org/kill-command-in-linux-with-examples/)
- [Processes in Linux/Unix - GeeksforGeeks](https://www.geeksforgeeks.org/processes-in-linuxunix/)

### 1.3. OOP
4 tính chất đặc trưng:
- Trừu tượng: đưa ra những cái chung,  những cái trừu tượng dựa trên tính chất của các đối tượng
- Kế thừa: các đối tượng con được kế thừa các tính chất từ đối tượng cha
- Đa hình: cùng một hành động nhưng các đối tượng thực thi theo các cách khác nhau
- Đóng gói: các tính chất của đối tượng được gói gọn vào trong đối tượng đó

UML - Unified Modeling Language: được chia thành 2 mục chính là Struture Diagram và Behavior Diagram
![UML 2.5 Diagrams Taxonomy.](https://www.uml-diagrams.org/uml-25-diagrams.png)

References:
- [OOP là gì? 4 đặc tính cơ bản của OOP - ITviec Blog](https://itviec.com/blog/oop-la-gi/)
- [UML 2.5 Diagrams Overview (uml-diagrams.org)](https://www.uml-diagrams.org/uml-25-diagrams.html)
### 1.4. Algorithm
- Linked List: các phần tử được nối với nhau thông qua các node tạo thành danh sách
- Binary Tree: các phần tử được sắp xếp vào các node trong cây. Mỗi node có thể có 2 node con (thường được gọi là node trái và node phải) sao cho node cha có nhiều nhất 1 node con lớn hơn cha và nhiều nhất 1 node con nhỏ hơn cha.
- Binary Search Tree: dựa trên cấu trúc cây nhị phân, sử dụng tính chất giữa node cha và node con để xây dựng thuật toán search có độ phức tạp logn
- Quick Sort: thuật toán sắp xếp dựa trên ý tưởng chính là lấy một phần tử trung gian và tách mảng thành 2 nhóm nhỏ hơn và lớn hơn nằm 2 bên phần tử trung gian đó
- Merge Sort: chia mảng thành các nhóm nhỏ để so sánh sau đó ghép lại

References:
- [Data Structures - GeeksforGeeks](https://www.geeksforgeeks.org/data-structures/)

### 1.5. HTTP 1.0, 1.1, 2.0, 3.0
Các thay đổi trọng tâm qua các phiên bản HTTP:
- Request Header
- Version Field
- Status Codes
- Content Type
- Methods (POST, HEAD)
- Host Header
- Persistant Connections
- Continue Status
- More Methods (PUT; PATCH; DELETE; CONNECT; TRACE; OPTIONS)
- Request Multiplexing
- Request Priorization
- Automatic Compressing
- Connection Reset
- Server Push
- Transport Layer: QUIC
![Versions](https://www.baeldung.com/wp-content/uploads/sites/4/2022/03/Versions.png)

References:
- [HTTP: 1.0 vs. 1.1 vs 2.0 vs. 3.0 | Baeldung on Computer Science](https://www.baeldung.com/cs/http-versions)

### 1.6. Design Patterns
Design pattern là các giải pháp tổng thể đã được tối ưu hóa, được tái sử dụng cho các vấn đề phổ biến trong thiết kế phần mềm mà chúng ta thường gặp phải hàng ngày.

Các Design Pattern thông dụng:
- Singleton: đảm bảo rằng một lớp chỉ có một thể hiện (instance) duy nhất
- Composite: đưa danh sách các đối tượng có cùng một lớp trừu tượng về tổ chức thành một cây quan hệ (Ví dụ: File Explorer)
- Factory: sử dụng để quản lý và cấu trúc các đối tượng được yêu cầu thông qua một lớp trung gian
- Abstract Factory: là Factory nhưng được trừu tượng hóa thành dựa vào các lớp trừu tượng của đối tượng được nhắm đến

References:
- [The Catalog of Design Patterns (refactoring.guru)](https://refactoring.guru/design-patterns/catalog)
## 2. Load Balancer
### 2.1. Khái niệm
Load Balancer (cân bằng tải) đóng vai trò như người điều phối giao thông, sẽ điều hướng các request của người dùng vào các server con. Có tác dụng chia nhỏ khối lượng công việc giúp cho hệ thống chịu tải tốt hơn, tăng trải nghiệm người dùng.
### 2.2. Kiến trúc nginx
NGINX sử dụng mô hình cấu hình process nghĩa là có thể điều chỉnh phụ thuộc vào tài nguyên phần cứng hiện có:
- **Master process** thực thi các hành động như đọc config và cấu hình port sau đó tạo các các process con (theo 3 loại dưới đây)
- **Cache loader process** chạy từ khi hệ thống bắt đầu để tải các dữ liệu từ ổ đĩa vào bộ nhớ sau đó ngắt hoạt động.
- **Cache manager process** chạy định kì và thay đổi các entry từ ổ đĩa để giữ dữ liệu đúng kích thước
- **Worker process** làm hầu hết các công việc. Xử lý các kết nối internet, đọc và ghi dữ liệu vào đĩa, giao tiếp giữa các server upstream

- Hoạt động chính của NGINX là thông qua các worker (worker process)
- Khác với các webserver khác phải xử lý lần lượt từng request một thì NGINX sẽ tiếp nhận tất cả các request và xử lý đồng thời từng request. Để hiểu rõ hơn có thể lấy ví dụ qua trò cờ vua, thông thường khi chơi thì người chơi sẽ đánh lần lượt với từng đối thủ một mới tới một đối thủ khác. Còn có một cách chơi khác là người chơi có thể đấu với nhiều người cùng một lúc. Cái khác biệt ở đây chính là thay vì phải đợi đối thủ hoàn thành nước đi thì trong lúc đợi người chơi có thể di chuyển qua đối thủ khác để đi nước tiếp theo rồi quay lại sau.
- Việc xử lý như vậy giúp cho NGINX tiết kiệm được lượng lớn thời gian cũng như về tài nguyên (có thể xử dụng một thread để xử lý nhiều request) cộng với việc áp dụng mô hình system tuning giúp cho NGINX có thể scale ra xử lý nhiều request hơn.

References:
- [What Is Load Balancing? How Load Balancers Work (nginx.com)](https://www.nginx.com/resources/glossary/load-balancing/)
- [Inside NGINX: How We Designed for Performance & Scale - DZone](https://dzone.com/articles/inside-nginx-how-we-designed)

## 3. Caching
### 3.1. Vai trò của cache, các thuật toán
Cache là một nơi lưu trữ tạm thời, sẽ lưu lại các kết quả đã được xử lý và phản hồi cho các yêu cầu sau, giúp tiết kiệm thời gian và hiệu năng của hệ thống.
Các thuật toán cache cơ bản:
- LRU (Least Recently Used): bỏ đi các item trong cache ít được dùng gần đây nhất
- LFU (Least Frequently Used): bỏ đi các item trong cache có ít lượt dùng nhất

### 3.2. Cách hoạt động của [caffeine](http://highscalability.com/blog/2016/1/25/design-of-a-modern-cache.html)

Hoạt động của caffeine là sự kết hợp của các giải pháp như sau:
- Eviction Policy (lựa chọn các phần tử ít sử dụng để loại bỏ) + cấu trúc ma trận đếm phù hợp (tùy chỉnh theo độ hiệu quả, bộ nhớ)
- Expiration Policy (lựa chọn các phần tử hết hạn để loại bỏ) + đánh dấu hết hạn cứng (fixed duration) cho các phần tử
- Sử dụng tư tưởng commit log (từ các database) tức các thay đổi sẽ được lưu dưới dạng log và sẽ được apply vào map sau
- - Hỗ trợ cho multi-thread giúp cho nhiều luồng có thể đọc/ghi dữ liệu
- - Các cập nhật sẽ được ghi log bằng cách ghi vào các buffer để quản lý
- - Khi đến một thời điểm phù hợp thì các buffer sẽ được ghi vào map

References:
- [LRU Cache Implementation - GeeksforGeeks](https://www.geeksforgeeks.org/lru-cache-implementation/)
- [Least Frequently Used (LFU) Cache Implementation - GeeksforGeeks](https://www.geeksforgeeks.org/least-frequently-used-lfu-cache-implementation/)
- [NGINX Content Caching | NGINX Plus](https://docs.nginx.com/nginx/admin-guide/content-cache/content-caching/)
- [Design of a Modern Cache - High Scalability -](http://highscalability.com/blog/2016/1/25/design-of-a-modern-cache.html)
- [SpringBoot: Implement caching with Caffeine. – My Developer Journal (sunitc.dev)](https://sunitc.dev/2020/08/27/springboot-implement-caffeine-cache/?fbclid=IwAR0LPKGYHlxeOc4d-SG5GLpzeRdYVwuqP-GM6igTe66xZTtbEiBeJVcnz7A)