import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProductManage {
    ArrayList<Product> products;
    Scanner scanner;

    public ProductManage() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void createProduct() {
        try {
            System.out.println("Mã sản phẩm: ");
            String id = scanner.nextLine();
            while (checkID(id)) {
                System.out.println("Mã sản phẩm đã tồn tại!");
                System.out.println("Nhập lại mã sản phẩm: ");
                id = scanner.nextLine();
            }
            System.out.println("Tên: ");
            String name = scanner.nextLine();
            System.out.println("Giá: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.println("Số lượng: ");
            int amount = Integer.parseInt(scanner.nextLine());
            System.out.println("Mô tả sản phẩm: ");
            String description = scanner.nextLine();
            Product product = new Product(id, name, price, amount, description);
            products.add(product);
            System.out.println("Thêm sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println("Dữ liệu nhập vào không hợp lệ!");
            System.out.println("Hãy nhập lại.");
            createProduct();
        }
    }

    public void updateProduct(Product product) {
        try {
            System.out.println("Mã sản phẩm mới: ");
            String id = scanner.nextLine();
            while (checkID(id)) {
                System.out.println("Mã sản phẩm đã tồn tại!");
                System.out.println("Nhập lại mã sản phẩm: ");
                id = scanner.nextLine();
            }
            System.out.println("Tên mới: ");
            String name = scanner.nextLine();
            System.out.println("Giá mới: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.println("Số lượng mới: ");
            int amount = Integer.parseInt(scanner.nextLine());
            System.out.println("Mô tả sản phẩm mới: ");
            String description = scanner.nextLine();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setAmount(amount);
            product.setDescribe(description);
            System.out.println("Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println("Dữ liệu nhập vào không hợp lệ!");
            System.out.println("Hãy nhập lại.");
            updateProduct(product);
        }
    }

    public void deleteProduct(Product product) {
        System.out.println("Bạn có muốn xóa sản phẩm này không?");
        System.out.println("Y: Đồng ý xóa!");
        System.out.println("Nhập kí tự bất kì để thoát!");
        String choice = scanner.nextLine();
        if (choice.equals("Y")) {
            products.remove(product);
            System.out.println("Xóa sản phẩm thành công!");
        }
    }
    public ArrayList<Product> sortByIncrementalPrice() {
        Collections.sort(products);
        return products;
    }

    public ArrayList<Product> sortByDescendPrice() {
        ArrayList<Product> newProducts = sortByIncrementalPrice();
        Collections.reverse(newProducts);
        return newProducts;
    }

    public Product searchById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product searchMaxPrice() {
        ArrayList<Product> productArrayList = sortByIncrementalPrice();
        return productArrayList.get((productArrayList.size() - 1));
    }
    public void displayAll() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void displayProductArrayList(ArrayList<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }


    public boolean checkID(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public void writeFileProduct() {
        try {
            File file = new File("products.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false));
            for (Product product : products) {
                bufferedWriter.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getAmount() + "," + product.getDescribe()  +"\n");
            }
            bufferedWriter.close();
            System.out.println("Ghi file thành công!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public  ArrayList<Product> readFileProductList() {

        try {
            File file = new File("products.csv");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] strings = value.split(",");
                Product product = new Product(strings[0], strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), strings[4]);
                products.add(product);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
}
