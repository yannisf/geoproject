package gr.fraglab.geoproject.vo;

public class AdminVo {

    private String admin1;
    private String admin2;
    private String admin3;

    public AdminVo() {
    }

    public AdminVo(String admin1, String admin2, String admin3) {
        this.admin1 = admin1;
        this.admin2 = admin2;
        this.admin3 = admin3;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getAdmin3() {
        return admin3;
    }

    public void setAdmin3(String admin3) {
        this.admin3 = admin3;
    }

    @Override
    public String toString() {
        return "AdminVo{" +
                "admin1='" + admin1 + '\'' +
                ", admin2='" + admin2 + '\'' +
                ", admin3='" + admin3 + '\'' +
                '}';
    }
}
