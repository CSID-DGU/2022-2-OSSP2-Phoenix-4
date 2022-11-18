package phoenix.Mymichef.data.role;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String value;

    Role(String value) {
        this.value = value;
    }
}
