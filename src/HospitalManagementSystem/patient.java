package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;




public class patient {
    private Connection connection;
    private Scanner scanner;


    public patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.println("Enter patient Name");
        String name = scanner.next();
        System.out.println("Enter patient Age");
        int age = scanner.nextInt();
        System.out.println("Enter patient Gender");
        String gender = scanner.next();


        try {
            String query = "INSERT INTO patient(name,age,gender)VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("patient Added Successfully");
            } else {
                System.out.println("Failed to add Patient");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatient() {
        String query = "select*from patient";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("patients: ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String gender = resultSet.getString("gender");
                System.out.println("ID:" + id + "Name:" + name + " Age: " + age + " Gender:" + gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id) {
        String query = "select*from patient where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Patient found: ");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                return true;
            } else {
                System.out.println("Not Found");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}



