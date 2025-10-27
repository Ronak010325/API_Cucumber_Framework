package POJOClasses.Employees;

public class employeePOJO {
    private String status;
    private employeeData data;
    private String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(employeeData data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public employeeData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
