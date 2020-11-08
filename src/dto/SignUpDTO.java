package dto;

public class SignUpDTO {
    private String userId;
    private String userName;
    private String password;
    private int q1;
    private int q2;
    private int q3;
    private String q1S;
    private String q2S;
    private String q3S;
    private String a1;
    private String a2;
    private String a3;

    public SignUpDTO() {
    }

    public SignUpDTO(String userId, String userName, String password, int q1, int q2, int q3, String q1S, String q2S, String q3S, String a1, String a2, String a3) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setPassword(password);
        this.setQ1(q1);
        this.setQ2(q2);
        this.setQ3(q3);
        this.setQ1S(q1S);
        this.setQ2S(q2S);
        this.setQ3S(q3S);
        this.setA1(a1);
        this.setA2(a2);
        this.setA3(a3);
    }

    public SignUpDTO(String userId, String userName, String password) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setPassword(password);
    }

    public SignUpDTO(String userId, int q1, int q2, int q3) {
        this.setUserId(userId);
        this.setQ1(q1);
        this.setQ2(q2);
        this.setQ3(q3);
    }

    public SignUpDTO(String userId, String q1S, String q2S, String q3S) {
        this.setUserId(userId);
        this.setQ1S(q1S);
        this.setQ2S(q2S);
        this.setQ3S(q3S);
    }

    public SignUpDTO(String userId, String password, String q1S, String q2S, String q3S, String a1, String a2, String a3) {
        this.userId = userId;
        this.password = password;
        this.q1S = q1S;
        this.q2S = q2S;
        this.q3S = q3S;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    public SignUpDTO(String userId, int q1, int q2, int q3, String a1, String a2, String a3) {
        this.setUserId(userId);
        this.setQ1(q1);
        this.setQ2(q2);
        this.setQ3(q3);
        this.setA1(a1);
        this.setA2(a2);
        this.setA3(a3);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public String getQ1S() {
        return q1S;
    }

    public void setQ1S(String q1S) {
        this.q1S = q1S;
    }

    public String getQ2S() {
        return q2S;
    }

    public void setQ2S(String q2S) {
        this.q2S = q2S;
    }

    public String getQ3S() {
        return q3S;
    }

    public void setQ3S(String q3S) {
        this.q3S = q3S;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", q1=" + q1 +
                ", q2=" + q2 +
                ", q3=" + q3 +
                ", q1S='" + q1S + '\'' +
                ", q2S='" + q2S + '\'' +
                ", q3S='" + q3S + '\'' +
                ", a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", a3='" + a3 + '\'' +
                '}';
    }
}
