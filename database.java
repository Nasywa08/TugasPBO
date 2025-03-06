
import javax.sound.sampled.Line;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class database {

    private ArrayList <mahasiswa> data = new ArrayList<>();
    private String filename = "src/book2.csv";
    private Path path = Path.of(filename);

    public database() {
        open();
    }

    public ArrayList<mahasiswa> getData() {
        return data;
    }

    public void open(){
        try {
            List<String> Lines = Files.readAllLines(path);
            data = new ArrayList<>();
            for (int i = 1; i < Lines.size(); i++) {
                String line = Lines.get(i);
                String[] elemen = line.split(",");
                String nim = elemen[0];
                String nama = elemen[1];
                String alamat = elemen[2];
                int semester = Integer.parseInt(elemen[3]);
                int sks = Integer.parseInt(elemen[4]);
                double ipk = Double.parseDouble(elemen[5]);
                mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.add(mhs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(){
        StringBuilder sb = new StringBuilder();
        sb.append("NIM, NAMA, ALAMAT(KOTA), SEMESTER, SKS, IPK\n ");
        if (!data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                mahasiswa mhs = data.get(i);
                String line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," + mhs.getSemester() + "," + mhs.getSks() + "," + mhs.getIpk() + "\n";
                sb.append(line);
            }
        }
        try {
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void view(){
        System.out.println("=======================================================================================");
        System.out.printf("| %-8.8S |","NIM");
        System.out.printf("| %-20.20S |","NAMA");
        System.out.printf("| %-20.20S |","ALAMAT");
        System.out.printf("| %8.8S |","SEMESTER");
        System.out.printf("| %3.3S |","SKS");
        System.out.printf("| %4.4S |%n","IPk");
        System.out.println("=======================================================================================");

        for (mahasiswa mhs : data){
            System.out.printf("| %-8S |",mhs.getNim());
            System.out.printf("| %-20.20S |",mhs.getNama());
            System.out.printf("| %-20.20S |",mhs.getAlamat());
            System.out.printf("| %8.8S |",mhs.getSemester());
            System.out.printf("| %3.3S |",mhs.getSks());
            System.out.printf("| %4.4S |",mhs.getIpk());
            System.out.println();
        }
        System.out.println("=======================================================================================");
    }

    boolean insert(String nim,String nama, String alamat, int semester, int sks,double ipk){
        boolean status = true;
        //cek primary key
        if (!data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)){
                    status = false;
                    break;
                }
            }
        }
        if (status == true){
            mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
            data.add(mhs);
            save();
        }
        return status;
    }

    public int search(String nim){
        int index = -1;
        if (!data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public boolean update(int index,String nim, String nama,String alamat,int semester,int sks, double ipk ){
        boolean status = false;
        if (!data.isEmpty()){
            //update
            if (index >= 0 && index < data.size()){
                mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester,sks, ipk);
                data.set(index,mhs);
                save();
                status = true;

            }
        }
        return status;
    }

    public boolean delete(int index){
        boolean status = false;
        if (!data.isEmpty()){
            data.remove(index);
            save();
            status = true;
        }

        return status;
    }

}
