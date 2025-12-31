package ntu.nghia.project.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.Executors;

// Import các Entity của bạn
import ntu.nghia.project.database.db_gamemode.GameMode;
import ntu.nghia.project.database.db_gamemode.GameModeDao;
import ntu.nghia.project.database.db_question.Question;
import ntu.nghia.project.database.db_question.QuestionDao;
import ntu.nghia.project.database.db_user.User;
import ntu.nghia.project.database.db_user.UserDao;
@Database(entities = {User.class, Question.class, GameMode.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract QuestionDao questionDao();
    public abstract GameModeDao gameModeDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "game_data_full_v1.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                populateData(INSTANCE);
            });
        }
    };

    private static void populateData(AppDatabase db) {
        GameMode modeCoBan = new GameMode("IT Cơ bản", "Phần cứng, Windows, Mạng");
        modeCoBan.id = 1;

        GameMode modeLapTrinh = new GameMode("Coder", "Java, Android, SQL");
        modeLapTrinh.id = 2;

        GameMode modeKho = new GameMode("Senior Architect", "System Design, Security, Pattern");
        modeKho.id = 3;

        db.gameModeDao().insertMode(modeCoBan);
        db.gameModeDao().insertMode(modeLapTrinh);
        db.gameModeDao().insertMode(modeKho);

        QuestionDao questionDao = db.questionDao();

        // ============================================================
        // MODE 1: IT CƠ BẢN (ID = 1) - 30 CÂU
        // ============================================================
        questionDao.insertAll(
                new Question("CPU là viết tắt của?", "Central Processing Unit", "Central Power Unit", "Computer Processing Unit", "Control Process Unit", "A", 1),
                new Question("RAM có đặc điểm gì?", "Mất dữ liệu khi tắt máy", "Lưu dữ liệu vĩnh viễn", "Chỉ đọc không ghi", "Là bộ nhớ ngoài", "A", 1),
                new Question("Phím tắt Ctrl + Z dùng để?", "Hoàn tác (Undo)", "Làm lại (Redo)", "Cắt (Cut)", "Dán (Paste)", "A", 1),
                new Question("1 GB bằng bao nhiêu MB?", "1024", "1000", "1028", "512", "A", 1),
                new Question("Thiết bị nào dùng để kết nối mạng Internet?", "Modem/Router", "Monitor", "Printer", "Scanner", "A", 1),
                new Question("Hệ điều hành nào là mã nguồn mở?", "Linux", "Windows", "macOS", "iOS", "A", 1),
                new Question("Đuôi file .exe thường là?", "File thực thi (Chương trình)", "File văn bản", "File ảnh", "File âm thanh", "A", 1),
                new Question("Phím tắt để chụp màn hình trên Windows?", "Print Screen", "F5", "Ctrl + P", "Alt + F4", "A", 1),
                new Question("Trình duyệt web nào của Google?", "Chrome", "Firefox", "Edge", "Safari", "A", 1),
                new Question("Để in đậm văn bản trong Word, dùng phím?", "Ctrl + B", "Ctrl + I", "Ctrl + U", "Ctrl + P", "A", 1),

                new Question("SSD khác HDD ở điểm nào chính?", "Tốc độ nhanh hơn, bền hơn", "Rẻ hơn HDD", "Dung lượng luôn nhỏ hơn", "Chạy bằng đĩa quay", "A", 1),
                new Question("Cổng HDMI dùng để truyền gì?", "Hình ảnh và âm thanh", "Chỉ hình ảnh", "Chỉ âm thanh", "Mạng Internet", "A", 1),
                new Question("WWW là viết tắt của?", "World Wide Web", "World Web Wide", "Web World Wide", "Wide Web World", "A", 1),
                new Question("Virus máy tính là gì?", "Phần mềm độc hại", "Lỗi phần cứng", "Bụi bẩn trong máy", "Lỗi màn hình", "A", 1),
                new Question("Để khóa nhanh máy tính Windows, dùng phím?", "Windows + L", "Ctrl + L", "Alt + L", "Shift + L", "A", 1),
                new Question("IP Address là gì?", "Địa chỉ định danh thiết bị trên mạng", "Mã số ổ cứng", "Tên người dùng", "Mật khẩu wifi", "A", 1),
                new Question("Đuôi file .jpg là định dạng gì?", "Hình ảnh", "Video", "Âm thanh", "Văn bản", "A", 1),
                new Question("Phần mềm nào dùng để nén file?", "WinRAR", "Photoshop", "Excel", "VLC", "A", 1),
                new Question("Chuột máy tính kết nối qua cổng nào phổ biến nhất?", "USB", "VGA", "HDMI", "LAN", "A", 1),
                new Question("Alt + Tab dùng để làm gì?", "Chuyển đổi giữa các cửa sổ", "Tắt máy", "Mở menu", "Chụp ảnh", "A", 1),

                new Question("BIOS nằm ở đâu?", "Trên bo mạch chủ (Mainboard)", "Trên ổ cứng", "Trong RAM", "Trên màn hình", "A", 1),
                new Question("Phần mềm Excel dùng để làm gì?", "Bảng tính", "Soạn thảo văn bản", "Chỉnh sửa ảnh", "Nghe nhạc", "A", 1),
                new Question("Để tải lại trang web, nhấn phím?", "F5", "F1", "F12", "Esc", "A", 1),
                new Question("Mạng LAN là gì?", "Mạng cục bộ", "Mạng diện rộng", "Mạng toàn cầu", "Mạng không dây", "A", 1),
                new Question("Nút Backspace dùng để?", "Xóa ký tự phía trước con trỏ", "Xóa ký tự phía sau", "Xuống dòng", "Cách dòng", "A", 1),
                new Question("Biểu tượng 'Thùng rác' trên Windows tên là?", "Recycle Bin", "Trash Can", "Garbage", "Bin", "A", 1),
                new Question("Đơn vị Hz của màn hình đo cái gì?", "Tần số quét", "Độ sáng", "Độ phân giải", "Kích thước", "A", 1),
                new Question("File .mp3 là định dạng gì?", "Âm thanh", "Video", "Hình ảnh", "Văn bản", "A", 1),
                new Question("Để bôi đen toàn bộ văn bản, dùng phím?", "Ctrl + A", "Ctrl + S", "Ctrl + C", "Ctrl + V", "A", 1),
                new Question("GPS là hệ thống gì?", "Định vị toàn cầu", "Đo tốc độ mạng", "Quản lý file", "Chống virus", "A", 1)
        );

        // ============================================================
        // MODE 2: CODER (ID = 2) - 30 CÂU
        // ============================================================
        questionDao.insertAll(
                new Question("Biến 'int' trong Java có kích thước?", "4 bytes", "2 bytes", "8 bytes", "1 byte", "A", 2),
                new Question("Để kế thừa lớp trong Java, dùng từ khóa?", "extends", "implements", "instanceof", "super", "A", 2),
                new Question("Vòng lặp nào kiểm tra điều kiện sau cùng?", "do-while", "for", "while", "foreach", "A", 2),
                new Question("Activity trong Android bắt đầu bằng hàm nào?", "onCreate()", "onStart()", "onResume()", "main()", "A", 2),
                new Question("File cấu hình chính của ứng dụng Android là?", "AndroidManifest.xml", "MainActivity.java", "build.gradle", "strings.xml", "A", 2),
                new Question("Lớp cha của tất cả các lớp trong Java là?", "Object", "Class", "System", "Root", "A", 2),
                new Question("ArrayList khác Array thường ở điểm nào?", "Kích thước động", "Kích thước cố định", "Không lưu được số", "Chậm hơn", "A", 2),
                new Question("Để hiển thị thông báo ngắn dưới đáy màn hình Android?", "Toast", "Dialog", "Intent", "Log", "A", 2),
                new Question("Câu lệnh SQL để lấy dữ liệu?", "SELECT", "GET", "FETCH", "TAKE", "A", 2),
                new Question("ID của View được lưu tự động trong file nào?", "R.java", "Res.java", "Id.java", "View.java", "A", 2),

                new Question("Từ khóa 'final' với biến có ý nghĩa gì?", "Hằng số (không thể thay đổi)", "Biến toàn cục", "Biến tạm", "Biến hệ thống", "A", 2),
                new Question("Interface có chứa body của hàm không (Java 7)?", "Không", "Có", "Chỉ hàm private", "Tùy ý", "A", 2),
                new Question("Log.d() trong Android dùng để?", "Ghi log Debug", "Ghi log Lỗi", "Ghi log Thông tin", "Cảnh báo", "A", 2),
                new Question("LinearLayout sắp xếp các view thế nào?", "Ngang hoặc Dọc", "Chồng lên nhau", "Tự do", "Lưới", "A", 2),
                new Question("Git command để lưu thay đổi vào local repo?", "git commit", "git push", "git add", "git pull", "A", 2),
                new Question("Toán tử '==' so sánh gì giữa 2 object?", "Địa chỉ bộ nhớ", "Giá trị bên trong", "Kiểu dữ liệu", "Hashcode", "A", 2),
                new Question("Phương thức toString() trả về kiểu gì?", "String", "int", "void", "Object", "A", 2),
                new Question("Để chuyển đổi giữa các màn hình (Activity), dùng?", "Intent", "Bundle", "Fragment", "Context", "A", 2),
                new Question("Khóa chính (Primary Key) trong SQL yêu cầu?", "Duy nhất và không Null", "Có thể trùng lặp", "Phải là số", "Cho phép Null", "A", 2),
                new Question("JVM là viết tắt của?", "Java Virtual Machine", "Java Visual Machine", "Just Virtual Machine", "Java Variable Machine", "A", 2),

                new Question("NullPointerException xảy ra khi nào?", "Truy cập vào đối tượng null", "Chia cho 0", "Sai kiểu dữ liệu", "Mảng quá giới hạn", "A", 2),
                new Question("Access Modifier 'private' có phạm vi?", "Chỉ trong cùng Class", "Trong cùng Package", "Bất kỳ đâu", "Chỉ Class con", "A", 2),
                new Question("String Pool nằm ở đâu trong bộ nhớ?", "Heap", "Stack", "Metaspace", "Disk", "A", 2),
                new Question("Constructor có kiểu trả về không?", "Không", "Có (void)", "Có (int)", "Có (Object)", "A", 2),
                new Question("Để debug code từng dòng, ta dùng chức năng?", "Breakpoint", "Run", "Build", "Clean", "A", 2),
                new Question("Gradle dùng để làm gì trong Android Studio?", "Build automation tool", "Code editor", "Database", "Emulator", "A", 2),
                new Question("Lệnh SQL để thêm dữ liệu?", "INSERT INTO", "ADD NEW", "UPDATE", "CREATE", "A", 2),
                new Question("Try-Catch dùng để?", "Xử lý ngoại lệ (Exception)", "Tạo vòng lặp", "Khai báo biến", "Kết nối mạng", "A", 2),
                new Question("Overloading (Nạp chồng) là gì?", "Cùng tên hàm, khác tham số", "Khác tên hàm", "Ghi đè hàm cha", "Viết lại hàm", "A", 2),
                new Question("findViewById trả về kiểu dữ liệu gì?", "View", "String", "int", "Boolean", "A", 2)
        );

        // ============================================================
        // MODE 3: SENIOR ARCHITECT (ID = 3) - 30 CÂU
        // ============================================================
        questionDao.insertAll(
                new Question("SQL Injection là lỗi bảo mật do?", "Không kiểm tra đầu vào người dùng", "Server yếu", "Mạng chậm", "Lỗi RAM", "A", 3),
                new Question("Singleton Pattern đảm bảo điều gì?", "Chỉ có 1 instance duy nhất", "Tạo nhiều instance", "Code chạy nhanh hơn", "Bảo mật dữ liệu", "A", 3),
                new Question("CAP Theorem gồm 3 yếu tố nào?", "Consistency, Availability, Partition Tolerance", "Control, Access, Power", "Cloud, API, Platform", "Code, App, Program", "A", 3),
                new Question("Trong HashMap, Hash Collision được xử lý bằng?", "LinkedList hoặc Tree", "Xóa dữ liệu cũ", "Tăng kích thước", "Báo lỗi", "A", 3),
                new Question("Tại sao dùng 'StringBuilder' tốt hơn cộng chuỗi '+' trong vòng lặp?", "Không tạo ra nhiều object rác", "Code ngắn hơn", "Thread-safe", "Dễ đọc hơn", "A", 3),
                new Question("Memory Leak trong Android thường do?", "Giữ tham chiếu Context/Activity quá lâu", "Dùng nhiều biến int", "Code quá dài", "Dùng thư viện ngoài", "A", 3),
                new Question("Deadlock là hiện tượng gì?", "Hai tiến trình chờ nhau mãi mãi", "Chương trình chạy xong", "Hết bộ nhớ", "Mất mạng", "A", 3),
                new Question("Index trong Database giúp gì?", "Tăng tốc độ đọc (SELECT)", "Tăng tốc độ ghi (INSERT)", "Giảm dung lượng", "Mã hóa dữ liệu", "A", 3),
                new Question("HTTPS sử dụng giao thức nào để mã hóa?", "TLS/SSL", "FTP", "SSH", "TCP", "A", 3),
                new Question("ANR trong Android là lỗi gì?", "Application Not Responding", "Android Network Reset", "App Native Run", "Auto New Request", "A", 3),

                new Question("Sự khác biệt giữa Abstract Class và Interface (Java 8)?", "Interface không có state (biến instance)", "Abstract Class không có hàm", "Giống hệt nhau", "Interface chậm hơn", "A", 3),
                new Question("Microservices có ưu điểm gì so với Monolith?", "Dễ scale và deploy độc lập", "Code đơn giản hơn", "Không cần database", "Chạy nhanh hơn", "A", 3),
                new Question("Race Condition xảy ra khi nào?", "Nhiều thread cùng sửa 1 dữ liệu không đồng bộ", "Khi mạng chậm", "Khi ổ cứng đầy", "Khi CPU nóng", "A", 3),
                new Question("Từ khóa 'volatile' đảm bảo điều gì?", "Tính hiển thị (Visibility) giữa các thread", "Tính nguyên tử (Atomicity)", "Khóa luồng", "Chặn ghi dữ liệu", "A", 3),
                new Question("DDOS là hình thức tấn công gì?", "Từ chối dịch vụ phân tán", "Đánh cắp mật khẩu", "Nghe lén", "Thay đổi giao diện", "A", 3),
                new Question("Observer Pattern thường dùng để làm gì?", "Thông báo sự kiện cho nhiều đối tượng", "Tạo đối tượng mới", "Duyệt danh sách", "Kết nối Database", "A", 3),
                new Question("Garbage Collector (GC) hoạt động thế nào?", "Tự động thu hồi vùng nhớ không dùng", "Phải gọi thủ công", "Xóa toàn bộ RAM", "Chạy khi khởi động máy", "A", 3),
                new Question("Load Balancing (Cân bằng tải) giúp gì?", "Phân phối traffic đều giữa các server", "Tăng tốc độ internet", "Lưu dữ liệu", "Bảo mật", "A", 3),
                new Question("ACID trong Database là viết tắt của?", "Atomicity, Consistency, Isolation, Durability", "Access, Control, ID, Data", "Auto, Code, Input, Drive", "All, Clear, In, Out", "A", 3),
                new Question("ViewModel trong MVVM có tác dụng gì?", "Lưu trữ dữ liệu UI sống sót qua xoay màn hình", "Vẽ giao diện", "Gọi API trực tiếp", "Xử lý click", "A", 3),

                new Question("XSS (Cross-Site Scripting) là tấn công vào đâu?", "Trình duyệt người dùng (Client-side)", "Server Database", "Hệ điều hành Server", "Mạng nội bộ", "A", 3),
                new Question("Docker dùng để làm gì?", "Đóng gói ứng dụng vào Container", "Lập trình web", "Quản lý nhân sự", "Vẽ sơ đồ", "A", 3),
                new Question("CI/CD là viết tắt của?", "Continuous Integration / Continuous Delivery", "Code Input / Code Drive", "Cloud In / Cloud Data", "Computer IT / Computer Design", "A", 3),
                new Question("Trong Android, Service chạy ở Thread nào mặc định?", "Main Thread (UI Thread)", "Background Thread", "Worker Thread", "New Process", "A", 3),
                new Question("Soft Reference trong Java khác Strong Reference thế nào?", "Bị GC thu hồi khi thiếu bộ nhớ", "Không bao giờ bị thu hồi", "Bị thu hồi ngay lập tức", "Chỉ dùng cho chuỗi", "A", 3),
                new Question("Horizontal Scaling (Scale Out) là gì?", "Thêm nhiều máy chủ (Server)", "Nâng cấp RAM/CPU cho 1 máy", "Viết lại code", "Xóa dữ liệu cũ", "A", 3),
                new Question("JWT (JSON Web Token) dùng để?", "Xác thực người dùng (Authentication)", "Mã hóa password", "Lưu ảnh", "Gửi email", "A", 3),
                new Question("Lazy Loading là kỹ thuật gì?", "Chỉ tải dữ liệu khi cần thiết", "Tải tất cả ngay từ đầu", "Không tải gì cả", "Tải chậm để giảm tải", "A", 3),
                new Question("Sự khác biệt giữa TCP và UDP?", "TCP tin cậy (có check), UDP nhanh (không check)", "UDP tin cậy hơn", "TCP chỉ dùng cho video", "Không khác nhau", "A", 3),
                new Question("Dependency Injection (DI) giúp code thế nào?", "Giảm sự phụ thuộc (Decoupling), dễ test", "Chạy nhanh hơn", "Bảo mật hơn", "Giao diện đẹp hơn", "A", 3)
        );
    }
}