import java.util.Scanner;

public class UserInterface {

    public static void tampilkanMenu(){
        System.out.println();
        System.out.println("+===================+");
        System.out.println("|     PILIH MENU:   |");
        System.out.println("+-------------------+");
        System.out.println("|     [C] : CREATE  | ");
        System.out.println("|     [R] : READ    |");
        System.out.println("|     [U] : UPDATE  |");
        System.out.println("|     [D] : DELETE  |");
        System.out.println("|     [X] : EXIT    |");
        System.out.println("+===================+");
    }

    public static void main(String[] args) {
        database db = new database();
        System.out.println(" APLIKASI SIMPEL CRUD TEXT DATABASE");
        while(true){
            tampilkanMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("pilih: ");
            String pilihan = sc.nextLine();
            pilihan = pilihan.toUpperCase();

            switch (pilihan){
                case "C" :
                    System.out.println("INFO: Anda memilih menu Create");
                    System.out.println("_____________________________________");
                    System.out.println("Input data baru: ");
                    System.out.print("NIM            : ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA           : ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT         : ");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER       : ");
                    int semester = sc.nextInt();
                    System.out.print("SKS            : ");
                    int sks = sc.nextInt();
                    System.out.print("IPK            : ");
                    double ipk = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("_____________________________________");
                    boolean status = db.insert(nim, nama, alamat,semester, sks, ipk);

                    if (status== true){
                        System.out.println("DATA BARU BERHASIL DI TAMBAHKAN");
                    }else{
                        System.out.println("NIM "+ nim + " sudah ada di dalam database");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    break;

                case "R" :
                    System.out.println("INFO: Anda memilih menu Read");
                    db.view();

                    break;

                case "U" :
                    System.out.println("INFO: Anda memilih menu Update");
                    db.view();
                    System.out.print("Input Key (Nim Mahasiswa yang akan di update): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0){
                        System.out.println("anda akan meng-update data "+ db.getData().get(index));
                        System.out.println("_____________________________________");
                        System.out.println("Input data baru: ");
                        System.out.println("NIM            : ");
                        nim = sc.nextLine();
                        System.out.println("NAMA           : ");
                        nama = sc.nextLine();
                        System.out.println("ALAMAT         : ");
                        alamat = sc.nextLine();
                        System.out.println("SEMESTER       : ");
                        semester = sc.nextInt();
                        System.out.println("SKS            : ");
                        sks = sc.nextInt();
                        System.out.println("IPK            : ");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("_____________________________________");
                        status = db.update(index, nim, nama, alamat, semester, sks, ipk);

                        if (status == true){
                            System.out.println("DATA BERHASIL DI PERBARUI");
                        }else{
                            System.err.println("GAGAL MEMPERBAHARUI DATA");
                        }
                        System.out.println("__________________C" +
                                "X___________________");

            }else{
                System.out.println("mahasiswa dengan NIM: "+key+" tidak ada di database");
            }
            break;

            case "D" :
                System.out.println("INFO: Anda memilih menu Delete");
                db.view();
                System.out.println("Input Key (NIM Mahasiswa yang akan di hapus): ");
                System.out.print("Pilih: ");
                key = sc.nextLine();
                index = db.search(key);
                if (index >= 0){
                    System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA "+ db.getData().get(index)+"? Y/N");
                    System.out.print("Pilih: ");
                    pilihan = sc.nextLine();

                    if (pilihan.equalsIgnoreCase("Y")){
                        status = db.delete(index);

                        if (status == true ){
                            System.out.println("DATA BERHASIL DI HAPUS");
                        }else{
                            System.err.println("GAGAL MENGHAPUS DATA");
                        }
                    }
                }else{
                    System.err.println("Mahasiswa dengan NIM: "+key+ " tidak ada didalam database");
                }
                break;
                case "X" :
                    System.out.println("INFO: Anda memilih menu Exit");
                    System.out.println("APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI? Y/N");
                    System.out.print("pilih: ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")){
                        System.exit(0);
                    }
                    break;


            }
        }

    }
}
