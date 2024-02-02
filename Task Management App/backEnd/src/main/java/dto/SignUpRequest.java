package dto;


public class SignUpRequest {
    private String email;
    private CharSequence password;
    private String name;

    public String getEmail() {
        return email;
    }

    public CharSequence getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
