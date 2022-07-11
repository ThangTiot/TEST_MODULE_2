import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManage productManage = new ProductManage();
        int choice;
        do {
            System.out.println("------CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM-----");
            System.out.println("Chọn chức năng theo số để tiếp tục:");
            System.out.println("1. Xem danh sách.");
            System.out.println("2. Thêm mới.");
            System.out.println("3. Cập nhật.");
            System.out.println("4. Xóa.");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất.");
            System.out.println("7. Đọc từ file.");
            System.out.println("8. Ghi vào file.");
            System.out.println("9 Thoát.");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productManage.displayAll();
                    break;
                case 2:
                    productManage.createProduct();
                    productManage.displayAll();
                    break;
                case 3:
                    String idUpdate = "Nothing";
                    System.out.println("Nhập vão mã sản phẩm muốn cập nhật: ");
                    idUpdate = scanner.nextLine();
                    if (idUpdate.equals("Nothing")) {
                        break;
                    }
                    while (productManage.searchById(idUpdate) == null) {
                        System.out.println("Không tìm được sản phẩm với mã sản phẩm trên");
                        System.out.println("Nhập lại mã sản phẩm muốn cập nhật: ");
                        idUpdate = scanner.nextLine();
                    }
                    productManage.updateProduct(productManage.searchById(idUpdate));
                    break;
                case 4:
                    String idDelete = "Nothing";
                    System.out.println("Nhập vão mã sản phẩm muốn xóa: ");
                    idDelete = scanner.nextLine();
                    if (idDelete.equals("Nothing")) {
                        break;
                    }
                    while (productManage.searchById(idDelete) == null) {
                        System.out.println("Không tìm được sản phẩm với mã sản phẩm trên");
                        System.out.println("Nhập lại mã sản phẩm muốn xóa: ");
                        idDelete = scanner.nextLine();
                    }
                    productManage.deleteProduct(productManage.searchById(idDelete));
                    break;
                case 5:
                    System.out.println("1. Sắp xếp sản phẩm theo giá tăng dần.");
                    System.out.println("2. Sắp xếp sản phẩm theo giá giảm dần.");
                    System.out.println("3. Thoát.");
                    int choiceSort = Integer.parseInt(scanner.nextLine());
                    switch (choiceSort) {
                        case 1:
                            productManage.displayProductArrayList(productManage.sortByIncrementalPrice());
                            break;
                        case 2:
                            productManage.displayProductArrayList(productManage.sortByDescendPrice());
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 6:
                    System.out.println(productManage.searchMaxPrice());
                    break;
                case 7:
                    productManage.displayProductArrayList(productManage.readFileProductList());
                    break;
                case 8:
                    productManage.writeFileProduct();
                    break;
                case 9:
                    System.exit(9);
                    break;
            }
        } while (choice != 0);

    }
}
