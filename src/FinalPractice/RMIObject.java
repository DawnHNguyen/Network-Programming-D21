package FinalPractice;

import RMI.ObjectService;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIObject {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("203.162.10.109");
            ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

            Employee employee = (Employee) service.requestObject("B21DCCN208", "7ECe4HmP");


//            service.submitObject("B21DCCN208", "7ECe4HmP", );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Employee implements Serializable {
    private static final long serialVersionUID = 20241119L;
    private String id;
    private String name;
    private double baseSalary;
    private int experienceYears;
    private double finalSalary;

    public Employee(String id, String name, double baseSalary, int experienceYears) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.experienceYears = experienceYears;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }
}
