package TCP;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 20170711;
    private int id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(int id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public void process() {
//        Process name from nguyen van hai duong to DUONG, Nguyen Van Hai
        String[] name = this.getName().split(" ");
        StringBuilder newName = new StringBuilder(name[name.length - 1].toUpperCase() + ", ");
        for (int i = 0; i < name.length - 1; i++) {
            newName.append(name[i].substring(0, 1).toUpperCase()).append(name[i].substring(1).toLowerCase());
            if (i < name.length - 2) {
                newName.append(" ");
            }
        }
        System.out.println("New name: " + newName);
        this.setName(newName.toString());

//        process dob from mm-dd-yyyy to dd/mm/yyyy
        String[] dob = this.getDayOfBirth().split("-");
        String newDob = dob[1] + "/" + dob[0] + "/" + dob[2];
        System.out.println("New dob: " + newDob);
        this.setDayOfBirth(newDob);

//        Process username using name from nguyen van hai duong to nvhduong
        StringBuilder newUsername = new StringBuilder();
        for (String s : name) {
            newUsername.append(s.charAt(0));
        }
        newUsername.deleteCharAt(newUsername.length() - 1);
        newUsername.append(name[name.length - 1]);
        System.out.println("New username: " + newUsername);
        this.setUserName(newUsername.toString().toLowerCase());
    }


}
